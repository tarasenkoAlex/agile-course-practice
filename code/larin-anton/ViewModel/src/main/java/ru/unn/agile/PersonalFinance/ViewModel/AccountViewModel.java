package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AccountViewModel {
    private final StringProperty nameProperty = new SimpleStringProperty();
    private final IntegerProperty balanceProperty = new SimpleIntegerProperty();

    public final StringProperty getNameProperty() {
        return nameProperty;
    }

    public final String getName() {
        return nameProperty.getValue();
    }

    public final void setName(final String name) {
        nameProperty.setValue(name);
    }

    public final IntegerProperty getBalanceProperty() {
        return balanceProperty;
    }

    public final Integer getBalance() {
        return balanceProperty.getValue();
    }

    public final void setBalance(final Integer name) {
        balanceProperty.setValue(name);
    }
}
