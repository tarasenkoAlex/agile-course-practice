package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.Transaction;

public class AccountViewModel {
    private static final int DEFAULT_ACCOUNT_BALANCE = 10000;
    private static final String DEFAULT_ACCOUNT_NAME = "New Account";

    private Account internalAccount;

    private final StringProperty nameProperty = new SimpleStringProperty();
    private final IntegerProperty balanceProperty = new SimpleIntegerProperty();
    private final ListProperty<TransactionViewModel> transactionsProperty =
            new SimpleListProperty<>();

    public AccountViewModel() {
        Account account = new Account(DEFAULT_ACCOUNT_BALANCE, DEFAULT_ACCOUNT_NAME);
        initialize(account);
    }

    public AccountViewModel(final Account account) {
        initialize(account);
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

    public final Account getAccount() {
        return internalAccount;
    }

    public void registerTransaction(final TransactionViewModel transactionVM) {
        transactionsProperty.add(transactionVM);
        setBalance(internalAccount.getBalance());
    }

    private void initialize(final Account account) {
        Objects.requireNonNull(account);
        this.internalAccount = account;

        setName(account.getName());
        setBalance(account.getBalance());

        ObservableList<TransactionViewModel> transactions = wrapTransactions(account);
        transactionsProperty.setValue(transactions);

        nameProperty.addListener((observable, oldValue, newValue) -> {
            internalAccount.changeName(newValue);
        });
    }

    private ObservableList<TransactionViewModel> wrapTransactions(final Account account) {
        List<Transaction> transactions = account.getTransactions();
        List<TransactionViewModel> accountModels = transactions.stream()
                .map(transaction -> new TransactionViewModel(transaction))
                .collect(Collectors.toList());
        return FXCollections.observableList(accountModels);
    }
}
