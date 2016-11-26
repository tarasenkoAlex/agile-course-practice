package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.PersonalFinance.Model.Account;

public class AccountViewModel {
    private final StringProperty nameProperty = new SimpleStringProperty();
    private final StringProperty balanceProperty = new SimpleStringProperty();

    public AccountViewModel() { }

    public AccountViewModel(final Account account) {
        // TODO
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

    public Account getAccount() {
        // TODO
        return null;
    }

    int getIntBalance() {
        return 0;
    }
}
