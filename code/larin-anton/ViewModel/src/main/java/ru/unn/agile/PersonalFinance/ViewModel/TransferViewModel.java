package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.Transfer;
import ru.unn.agile.PersonalFinance.ViewModel.utils.GregorianCalendarHelper;

import java.util.GregorianCalendar;

public class TransferViewModel extends TransactionViewModel {
    private final ObjectProperty<AccountViewModel> accountFromProperty =
            new SimpleObjectProperty<>();

    private final ObjectProperty<AccountViewModel> accountToProperty =
            new SimpleObjectProperty<>();

    private Transfer modelTransfer;
    private AccountViewModel associatedAccount;
    private TransferViewModel linkedTransfer;
    private TransferViewModelState savedState;

    private boolean isInProcessOfDeletion;

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

    Transfer getModelTransfer() {
        return modelTransfer;
    }

    @Override
    protected void saveInternal() {
        modelTransfer = buildModelTransfer();
        getAccountFrom().registerTransaction(this.asOutcoming());
        getAccountTo().registerTransaction(this.duplicate().asIncoming());
    }

    @Override
    protected void updateInternal() {
        deleteModelTransfer();
        modelTransfer = buildModelTransfer();
        getAccountFrom().forceUpdateBalance();
        getAccountTo().forceUpdateBalance();
        synchronizeStateWithLinkedTransfer();
    }

    @Override
    protected void deleteInternal() {
        startProcessOfDeletion();
        if (!linkedTransfer.isInProcessOfDeletion()) {
            deleteModelTransfer();
            linkedTransfer.delete();
        }
        associatedAccount.unregisterTransaction(this);
        endProcessOfDeletion();
    }

    @Override
    protected void saveState() {
        savedState = TransferViewModelState.save(this);
    }

    @Override
    protected void recoverState() {
        savedState.recover(this);
    }

    private TransferViewModel duplicate() {
        TransferViewModel other = new TransferViewModel();
        other.setAmount(getAmount());
        other.setIsIncome(getIsIncome());
        other.setDate(getDate());
        other.setAccountFrom(getAccountFrom());
        other.setAccountTo(getAccountTo());
        other.modelTransfer = modelTransfer;
        other.linkedTransfer = this;
        linkedTransfer = other;
        other.markAsSaved();
        return other;
    }

    private void synchronizeStateWithLinkedTransfer() {
        linkedTransfer.setAmount(getAmount());
        linkedTransfer.setDate(getDate());
        linkedTransfer.modelTransfer = modelTransfer;
    }

    private Transfer buildModelTransfer() {

        AccountViewModel accountFrom = getAccountFrom();
        AccountViewModel accountTo = getAccountTo();
        Account modelAccountFrom = accountFrom.getModelAccount();
        Account modelAccountTo = accountTo.getModelAccount();

        GregorianCalendar transferDate =
                GregorianCalendarHelper.convertFromLocalDate(getDate());
        return modelAccountFrom.transferTo(
                modelAccountTo, getAmount(), transferDate);
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

        ableToSaveMutableProperty().bind(
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
            isCounterpartyMarkedAsDeletedMutableProperty().bind(account.deletedProperty());
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

    private void deleteModelTransfer() {
        Account modelAccountFrom = getAccountFrom().getModelAccount();
        modelAccountFrom.deleteTransaction(modelTransfer);
    }

    private void startProcessOfDeletion() {
        isInProcessOfDeletion = true;
    }
    private boolean isInProcessOfDeletion() {
        return isInProcessOfDeletion;
    }
    private void endProcessOfDeletion() {
        isInProcessOfDeletion = false;
    }
}
