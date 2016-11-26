package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LedgerViewModel {
    private final ObjectProperty<AccountViewModel> newAccount = new SimpleObjectProperty<>();

    public LedgerViewModel() {
    }

    public ObjectProperty<AccountViewModel> getNewAccountProperty() {
        return newAccount;
    }

    public AccountViewModel getNewAccount() {
        return newAccount.get();
    }

    public void createNewAccount() {
        // TODO
    }

    public void saveNewAccount() {
        // TODO
    }
}
