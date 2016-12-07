package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.binding.BooleanBinding;
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
        setDisplayTitle("Transfer");
        setUpBindings();
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

    @Override
    protected void saveInternal() {
        parentLedger.registerTransfer(this);
    }

    @Override
    protected void updateInternal() {

    }

    @Override
    protected void deleteInternal() {

    }

    @Override
    protected void saveState() {

    }

    @Override
    protected void recoverState() {
    }

    TransferViewModel copy() {
        TransferViewModel other = new TransferViewModel(parentLedger);
        other.setAmount(getAmount());
        other.setDate(getDate());
        other.setIsIncome(getIsIncome());
        other.setAccountFrom(getAccountFrom());
        other.setAccountTo(getAccountTo());
        return other;
    }

    private void setUpBindings() {
        BooleanBinding accountsNotNull =
                accountFromProperty.isNotNull().and(
                accountToProperty.isNotNull());

        BooleanBinding accountsNotEqual =
                accountFromProperty.isNotEqualTo(
                accountToProperty);

        BooleanBinding isAmountPositive =
                amountProperty.greaterThan(0);

        isAbleToSaveProperty.bind(
                accountsNotNull.and(
                accountsNotEqual).and(
                isAmountPositive));

        isIncomeProperty.addListener((observable, oldValue, newValue) ->
                updateDisplayCounterparty());

        accountToProperty.addListener((observable, oldValue, newValue) ->
                updateDisplayCounterparty());

        accountFromProperty.addListener((observable, oldValue, newValue) ->
                updateDisplayCounterparty());
    }

    private void updateDisplayCounterparty() {
        if (getIsIncome()) {
            setDisplayCounterpartyFromAccount(getAccountFrom());
        } else {
            setDisplayCounterpartyFromAccount(getAccountTo());
        }
    }

    private void setDisplayCounterpartyFromAccount(final AccountViewModel account) {
        displayCounterpartyProperty.unbind();
        isCounterpartyMarkedAsDeletedProperty.unbind();

        if (account == null) {
            setDisplayCounterparty(null);
        } else {
            displayCounterpartyProperty.bind(account.nameProperty());
            isCounterpartyMarkedAsDeletedProperty.bind(account.isDeletedProperty());
        }
    }
}
