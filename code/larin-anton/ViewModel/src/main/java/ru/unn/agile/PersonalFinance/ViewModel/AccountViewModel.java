package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import ru.unn.agile.PersonalFinance.Model.Account;

import java.util.Objects;

public class AccountViewModel {
    private final StringProperty nameProperty = new SimpleStringProperty();
    private final StringProperty balanceProperty = new SimpleStringProperty();
    private final ListProperty<TransactionViewModel> transactionsProperty = new SimpleListProperty<>();

    public AccountViewModel() {
        setName("New account");
        setBalance("1000");
    }

    public AccountViewModel(final Account account) {
        Objects.requireNonNull(account);
        setName(account.getName());
        setBalance(Integer.toString(account.getBalance()));
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

    public final StringProperty balanceProperty() {
        return balanceProperty;
    }

    public final String getBalance() {
        return balanceProperty.getValue();
    }

    public final void setBalance(final String name) {
        balanceProperty.setValue(name);
    }

    public ListProperty<TransactionViewModel> transactionsProperty() {
        return this.transactionsProperty;
    }

    public ObservableList<TransactionViewModel> getTransactions() {
        return this.transactionsProperty.get();
    }

    public Account getAccount() {
        return new Account(getIntBalance(), getName());
    }

    int getIntBalance() {
        return Integer.parseInt(getBalance());
    }

    public void addTransaction(final TransactionViewModel transactionVM) {
        // TODO
    }
}
