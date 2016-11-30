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

    private final ListProperty<CategoryViewModel> categoriesProperty =
            new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));

    private final ObjectProperty<AccountViewModel> selectedAccountProperty =
            new SimpleObjectProperty<>();

    public LedgerViewModel() {
        initializeCategories();
        setUpBindings();
    }

    // region Properties for Binding

    public final ListProperty<AccountViewModel> accountsProperty() {
        return this.accountsProperty;
    }

    public final ObservableList<AccountViewModel> getAccounts() {
        return this.accountsProperty.get();
    }

    public final ListProperty<CategoryViewModel> categoriesProperty() {
        return this.categoriesProperty;
    }

    public final ObservableList<CategoryViewModel> getCategories() {
        return this.categoriesProperty.get();
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

    void registerTransfer(final TransferViewModel transferViewModel) {
        // TODO
    }

    private void setUpBindings() {
        // ReadOnlyIntegerProperty accountsSizeProperties = accountsProperty.sizeProperty();
        // canAddTransactionProperty.bind(accountsSizeProperties.greaterThan(0));

        canAddTransactionProperty.bind(selectedAccountProperty().isNotNull());
    }

    private void initializeCategories() {
        categoriesProperty.add(new CategoryViewModel("Category 1"));
        categoriesProperty.add(new CategoryViewModel("Category 2"));
        categoriesProperty.add(new CategoryViewModel("Category 3"));
        categoriesProperty.add(new CategoryViewModel("Category 4"));
        categoriesProperty.add(new CategoryViewModel("Category 5"));
    }
}
