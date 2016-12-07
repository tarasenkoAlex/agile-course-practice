package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.PersonalFinance.Model.Ledger;

import java.util.ArrayList;

public class LedgerViewModel {
    private final Ledger modelLedger = new Ledger();

    private final BooleanProperty canAddTransactionProperty = new SimpleBooleanProperty();
    private final BooleanProperty canAddTransferProperty = new SimpleBooleanProperty();

    private final ListProperty<AccountViewModel> accountsProperty =
            new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));

    private final ObjectProperty<AccountViewModel> selectedAccountProperty =
            new SimpleObjectProperty<>();

    private final ObjectProperty<CategoriesManagerViewModel> categoriesManagerProperty =
            new SimpleObjectProperty<>(new CategoriesManagerViewModel());

    public LedgerViewModel() {
        setUpBindings();
    }

    // region Properties for Binding

    public final ListProperty<AccountViewModel> accountsProperty() {
        return this.accountsProperty;
    }

    public final ObservableList<AccountViewModel> getAccounts() {
        return this.accountsProperty.get();
    }

    public final ReadOnlyObjectProperty<CategoriesManagerViewModel> categoriesManagerProperty() {
        return this.categoriesManagerProperty;
    }

    public final CategoriesManagerViewModel getCategoriesManager() {
        return categoriesManagerProperty.get();
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

    public final BooleanProperty canAddTransactionProperty() {
        return this.canAddTransactionProperty;
    }

    public final boolean getCanAddTransaction() {
        return this.canAddTransactionProperty.get();
    }

    public final BooleanProperty canAddTransferProperty() {
        return this.canAddTransferProperty;
    }

    public final boolean getCanAddTransfer() {
        return this.canAddTransferProperty.get();
    }

    // endregion

    void addAccount(final AccountViewModel account) {
        modelLedger.addAccount(account.getModelAccount());
        accountsProperty.add(account);
    }

    void deleteAccount(final AccountViewModel account) {
        modelLedger.deleteAccount(account.getModelAccount());
        accountsProperty.remove(account);
    }

    void registerTransfer(final TransferViewModel transferViewModel) {
        AccountViewModel accountFrom = transferViewModel.getAccountFrom();
        accountFrom.addTransfer(transferViewModel);
    }

    private void setUpBindings() {
        ReadOnlyIntegerProperty accountsSizeProperties = accountsProperty.sizeProperty();
        canAddTransferProperty.bind(accountsSizeProperties.greaterThan(1));

        canAddTransactionProperty.bind(selectedAccountProperty().isNotNull());
    }
}
