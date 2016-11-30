package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TransferViewModel extends TransactionViewModel {
    private final LedgerViewModel parentLedger;

    private final ObjectProperty<AccountViewModel> accountFromProperty =
            new SimpleObjectProperty<>();

    private final ObjectProperty<AccountViewModel> accountToProperty =
            new SimpleObjectProperty<>();

    public TransferViewModel(final LedgerViewModel parentLedger) {
        this.parentLedger = parentLedger;
    }

    // region Properties for Binding

    public final ObjectProperty<AccountViewModel> accountFromProperty() {
        return this.accountFromProperty;
    }

    public final AccountViewModel getAccountFrom() {
        return this.accountFromProperty.get();
    }

    public final void setAccountFrom(final AccountViewModel account) {
        this.accountFromProperty.set(account);
    }

    public final ObjectProperty<AccountViewModel> accountToProperty() {
        return this.accountToProperty;
    }

    public final AccountViewModel getAccountTo() {
        return this.accountToProperty.get();
    }

    public final void setAccountTo(final AccountViewModel account) {
        this.accountToProperty.set(account);
    }

    // endregion

    protected void saveInternal() {
        parentLedger.registerTransfer(this);
    }
}
