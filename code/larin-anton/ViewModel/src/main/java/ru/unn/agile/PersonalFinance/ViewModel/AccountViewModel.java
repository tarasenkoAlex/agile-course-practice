package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.ViewModel.utils.SavableObject;
import ru.unn.agile.PersonalFinance.ViewModel.utils.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountViewModel extends SavableObject {
    private static final int DEFAULT_ACCOUNT_BALANCE = 10000;
    private static final String DEFAULT_ACCOUNT_NAME = "New Account";

    private final StringProperty nameProperty = new SimpleStringProperty();
    private final IntegerProperty balanceProperty = new SimpleIntegerProperty();
    private final ListProperty<TransactionViewModel> transactionsProperty =
            new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));

    private Account modelAccount;
    private Account savedState;
    private final LedgerViewModel parentLedger;

    public AccountViewModel(final LedgerViewModel parentLedger) {
        Objects.requireNonNull(parentLedger);
        this.parentLedger = parentLedger;
        setUpBindings();
        setName(DEFAULT_ACCOUNT_NAME);
        setBalance(DEFAULT_ACCOUNT_BALANCE);
    }

    // region Properties for Binding

    public final StringProperty nameProperty() {
        return nameProperty;
    }

    public final String getName() {
        return nameProperty.getValue();
    }

    public final void setName(final String name) {
        nameProperty.setValue(name);
    }

    public final IntegerProperty balanceProperty() {
        return balanceProperty;
    }

    public final int getBalance() {
        return balanceProperty.getValue();
    }

    public final void setBalance(final int balance) {
        balanceProperty.setValue(balance);
    }

    public final ListProperty<TransactionViewModel> transactionsProperty() {
        return this.transactionsProperty;
    }

    public final ObservableList<TransactionViewModel> getTransactions() {
        return this.transactionsProperty.get();
    }

    // endregion

    Account getModelAccount() {
        if (modelAccount == null) {
            throw new UnsupportedOperationException("Account should be "
                    + "saved before getting model account");
        }
        return modelAccount;
    }

    @Override
    protected void saveInternal() {
        modelAccount = new Account(getBalance(), getName());
        parentLedger.addAccount(this);
    }

    @Override
    protected void updateInternal() {
        modelAccount.changeName(getName());
    }

    @Override
    protected void deleteInternal() {
        parentLedger.deleteAccount(this);
    }

    @Override
    protected void saveState() {
        savedState = new Account(getBalance(), getName());
    }

    @Override
    protected void recoverState() {
        setName(savedState.getName());
        setBalance(savedState.getBalance());
    }

    void registerTransaction(final TransactionViewModel transaction) {
        getTransactions().add(transaction);
        updateBalance();
    }

    void unregisterTransaction(final TransactionViewModel transaction) {
        getTransactions().remove(transaction);
        updateBalance();
    }

    private void registerTransferAsIncoming(final TransferViewModel transfer) {
        transfer.setIsIncome(true);
        registerTransaction(transfer);
    }

    private void registerTransferAsOutcoming(final TransferViewModel transfer) {
        transfer.setIsIncome(false);
        registerTransaction(transfer);
    }

    private void setUpBindings() {
        BooleanBinding isAccountNameExists = Bindings.createBooleanBinding(() ->
                hasAccountWithName(getName()), parentLedger.accountsProperty(), nameProperty);

        isAbleToSaveMutableProperty().bind(isAccountNameExists.not());
    }

    private void updateBalance() {
        setBalance(modelAccount.getBalance());
    }

    private boolean hasAccountWithName(final String accountName) {
        List<AccountViewModel> accounts = parentLedger.getAccounts();
        return accounts.stream().anyMatch((account) -> {
            boolean namesEqual = StringHelper.areEqualTrimmed(
                    account.getName(), accountName);
            return account != this && namesEqual;
        });
    }
}
