package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.Ledger;

import java.util.List;
import java.util.stream.Collectors;

public class LedgerViewModel {
    private final Ledger ledgerModel = new Ledger();
    private final ListProperty<AccountViewModel> accountsProperty;

    public LedgerViewModel() {
        this.accountsProperty = new SimpleListProperty<>(getAccountViewModels());
    }

    public ListProperty<AccountViewModel> accountsProperty() {
        return this.accountsProperty;
    }

    public ObservableList<AccountViewModel> getAccounts() {
        return this.accountsProperty.get();
    }

    public void addAccount(AccountViewModel accountVM) {
        // TODO
    }

    private ObservableList<AccountViewModel> getAccountViewModels() {
        List<Account> accounts = this.ledgerModel.getAccounts();
        List<AccountViewModel> accountModels = accounts.stream()
                .map(account -> new AccountViewModel(account))
                .collect(Collectors.toList());
        return FXCollections.observableList(accountModels);
    }
}
