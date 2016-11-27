package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.Ledger;

public class LedgerViewModel {
    private final Ledger ledgerModel = new Ledger();

    private final ListProperty<AccountViewModel> accountsProperty;
    private final ObjectProperty<AccountViewModel> selectedAccountProperty;

    public LedgerViewModel() {
        this.accountsProperty = new SimpleListProperty<>(wrapAccounts());
        this.selectedAccountProperty = new SimpleObjectProperty<>();
    }

    // region Properties for Binding

    public final ListProperty<AccountViewModel> accountsProperty() {
        return this.accountsProperty;
    }

    public final ObservableList<AccountViewModel> getAccounts() {
        return this.accountsProperty.get();
    }

    public final ObjectProperty<AccountViewModel> selectedAccountProperty() {
        return this.selectedAccountProperty;
    }

    public final AccountViewModel getSelectedAccount() {
        return this.selectedAccountProperty.get();
    }

    public final void setSelectedAccount(final AccountViewModel selectedAccountProperty) {
        this.selectedAccountProperty.set(selectedAccountProperty);
    }

    // endregion

    public void addAccount(final AccountViewModel accountVM) {
        ledgerModel.addAccount(accountVM.getAccount());
        accountsProperty.add(accountVM);
        accountVM.setParentLedger(this);
    }

    private ObservableList<AccountViewModel> wrapAccounts() {
        List<Account> accounts = this.ledgerModel.getAccounts();
        List<AccountViewModel> accountModels = accounts.stream()
                .map(account -> new AccountViewModel(this, account))
                .collect(Collectors.toList());
        return FXCollections.observableList(accountModels);
    }

    public void forceUpdateAccounts(final AccountViewModel accountVM) {
        // TODO
    }
}
