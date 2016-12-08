package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.Transfer;
import ru.unn.agile.PersonalFinance.ViewModel.utils.GregorianCalendarHelper;

import java.util.GregorianCalendar;

public class TransferViewModel extends TransactionViewModel {
    private TransferViewModelSharedState sharedState;
    private AccountViewModel associatedAccount;
    private TransferViewModel linkedTransfer;
    private boolean isDeleting;

    private final ObjectProperty<AccountViewModel> accountFromProperty =
            new SimpleObjectProperty<>();

    private final ObjectProperty<AccountViewModel> accountToProperty =
            new SimpleObjectProperty<>();

    public TransferViewModel() {
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
        AccountViewModel accountFrom = getAccountFrom();
        AccountViewModel accountTo = getAccountTo();
        Account modelAccountFrom = accountFrom.getModelAccount();
        Account modelAccountTo = accountTo.getModelAccount();

        GregorianCalendar transferDate =
                GregorianCalendarHelper.convertFromLocalDate(getDate());
        Transfer modelTransfer = modelAccountFrom.transferTo(
                modelAccountTo, getAmount(), transferDate);

        sharedState = new TransferViewModelSharedState(modelTransfer, modelAccountFrom);

        accountFrom.registerTransaction(this.asOutcoming());
        accountTo.registerTransaction(this.duplicate().asIncoming());
    }

    @Override
    protected void updateInternal() {
        // TODO
    }

    @Override
    protected void deleteInternal() {
        startDeletion();
        sharedState.delete();
        if (!linkedTransfer.isDeleting()) {
            linkedTransfer.delete();
        }
        associatedAccount.unregisterTransaction(this);
        endDeletion();
    }

    @Override
    protected void saveState() {
        // TODO
    }

    @Override
    protected void recoverState() {
        // TODO
    }

    TransferViewModel duplicate() {
        TransferViewModel other = new TransferViewModel();
        other.setAmount(getAmount());
        other.setDate(getDate());
        other.setIsIncome(getIsIncome());
        other.setAccountFrom(getAccountFrom());
        other.setAccountTo(getAccountTo());
        other.sharedState = sharedState;
        other.linkedTransfer = this;
        linkedTransfer = other;
        other.markAsSaved();
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
                amountProperty().greaterThan(0);

        isAbleToSaveMutableProperty().bind(
                accountsNotNull.and(
                accountsNotEqual).and(
                isAmountPositive));

        isIncomeProperty().addListener((observable, oldValue, newValue) ->
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
        displayCounterpartyMutableProperty().unbind();
        isCounterpartyMarkedAsDeletedMutableProperty().unbind();

        if (account == null) {
            setDisplayCounterparty(null);
        } else {
            displayCounterpartyMutableProperty().bind(account.nameProperty());
            isCounterpartyMarkedAsDeletedMutableProperty().bind(account.isDeletedProperty());
        }
    }

    private TransferViewModel asIncoming() {
        associatedAccount = getAccountTo();
        this.setIsIncome(true);
        return this;
    }

    private TransferViewModel asOutcoming() {
        associatedAccount = getAccountFrom();
        this.setIsIncome(false);
        return this;
    }

    private void startDeletion() {
        isDeleting = true;
    }

    private boolean isDeleting() {
        return isDeleting;
    }

    private void endDeletion() {
        isDeleting = false;
    }
}
