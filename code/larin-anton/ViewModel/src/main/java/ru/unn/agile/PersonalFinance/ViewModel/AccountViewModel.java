package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.ExternalTransaction;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Objects;

public class AccountViewModel {
    private static final int DEFAULT_ACCOUNT_BALANCE = 10000;
    private static final String DEFAULT_ACCOUNT_NAME = "New Account";

    private final StringProperty nameProperty = new SimpleStringProperty();
    private final IntegerProperty balanceProperty = new SimpleIntegerProperty();
    private final ListProperty<TransactionViewModel> transactionsProperty =
            new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));

    private boolean isSaved;
    private Account modelAccount;
    private final LedgerViewModel parentLedger;

    public AccountViewModel(final LedgerViewModel parentLedger) {
        Objects.requireNonNull(parentLedger);
        this.parentLedger = parentLedger;
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

    public void save() {
        if (isSaved) {
            throw new UnsupportedOperationException("Account has been already saved");
        }
        modelAccount = new Account(getBalance(), getName());
        parentLedger.addAccount(this);
        isSaved = true;
    }

    void addExternalTransaction(final ExternalTransactionViewModel transaction) {
        if (!isSaved) {
            throw new UnsupportedOperationException("Account should be "
                    + "saved before adding new transaction");
        }

        ExternalTransaction modelTransaction = transaction.getModelExternalTransaction();
        modelAccount.addExternalTransaction(modelTransaction);
        registerTransaction(transaction);
    }

    void addTransfer(final TransferViewModel transfer) {
        if (transfer.getAccountFrom() != this) {
            throw new UnsupportedOperationException("Transfer source should be equal "
                    + "to the account where transfer will be added");
        }

        AccountViewModel accountTo = transfer.getAccountTo();
        Account modelAccountFrom = getModelAccount();
        Account modelAccountTo = accountTo.getModelAccount();

        GregorianCalendar transferDate =
                GregorianCalendarHelper.convertFromLocalDate(transfer.getDate());
        modelAccountFrom.transferTo(modelAccountTo, transfer.getAmount(), transferDate);

        registerTransaction(transfer);
        accountTo.registerTransaction(transfer);
    }

    private void registerTransaction(final TransactionViewModel transactionVM) {
        getTransactions().add(transactionVM);
        setBalance(modelAccount.getBalance());
    }
}
