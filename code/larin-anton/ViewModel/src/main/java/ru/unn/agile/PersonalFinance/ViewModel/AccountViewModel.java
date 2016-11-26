package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.Transaction;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountViewModel {
    private static final int STARTING_ACCOUNT_BALANCE = 10000;

    private final Account internalAccount;
    private final boolean isAccountCreatedExternally;

    private final StringProperty nameProperty = new SimpleStringProperty();
    private final IntegerProperty balanceProperty = new SimpleIntegerProperty();
    private final ListProperty<TransactionViewModel> transactionsProperty =
            new SimpleListProperty<>();

    public AccountViewModel() {
        internalAccount = new Account(STARTING_ACCOUNT_BALANCE, "New account");
        isAccountCreatedExternally = false;
        copyFieldsValueFromAccount(internalAccount);
        addListenersToProperties();
    }

    public AccountViewModel(final Account account) {
        Objects.requireNonNull(account);
        internalAccount = account;
        isAccountCreatedExternally = true;
        copyFieldsValueFromAccount(internalAccount);
        addListenersToProperties();
    }

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

    public ListProperty<TransactionViewModel> transactionsProperty() {
        return this.transactionsProperty;
    }

    public ObservableList<TransactionViewModel> getTransactions() {
        return this.transactionsProperty.get();
    }

    public Account getAccount() {
        if (isAccountCreatedExternally) {
            return internalAccount;
        } else {
            return new Account(getBalance(), getName());
        }
    }

    public void addTransaction(final TransactionViewModel transactionVM) {
        transactionsProperty.add(transactionVM);
        internalAccount.addExternalTransaction(transactionVM.getExternal());
        setBalance(internalAccount.getBalance());
    }

    private void copyFieldsValueFromAccount(final Account account) {
        setName(account.getName());
        setBalance(account.getBalance());

        ObservableList<TransactionViewModel> transactions = getTransactionViewModels(account);
        transactionsProperty.setValue(transactions);
    }

    private void addListenersToProperties() {
        nameProperty.addListener((observable, oldValue, newValue) -> {
            internalAccount.changeName(newValue);
        });
    }

    private ObservableList<TransactionViewModel> getTransactionViewModels(final Account account) {
        List<Transaction> transactions = account.getTransactions();
        List<TransactionViewModel> accountModels = transactions.stream()
                .map(transaction -> new TransactionViewModel(transaction))
                .collect(Collectors.toList());
        return FXCollections.observableList(accountModels);
    }
}
