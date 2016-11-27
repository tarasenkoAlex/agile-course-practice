package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.*;
import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.Category;
import ru.unn.agile.PersonalFinance.Model.ExternalTransaction;
import ru.unn.agile.PersonalFinance.Model.Transaction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public class TransactionViewModel {
    private AccountViewModel parentAccount;
    private Transaction internalTransaction;

    private final IntegerProperty amountProperty = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>();
    private final StringProperty descriptionProperty = new SimpleStringProperty();
    private final StringProperty counterpartyProperty = new SimpleStringProperty();
    private final BooleanProperty isTransferProperty = new SimpleBooleanProperty();
    private final ObjectProperty<CategoryViewModel> categoryProperty = new SimpleObjectProperty<>();
    private final BooleanProperty isIncomeProperty = new SimpleBooleanProperty();
    private final ObjectProperty<AccountViewModel> accountFromProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<AccountViewModel> accountToProperty = new SimpleObjectProperty<>();

    public TransactionViewModel() {
        setDate(LocalDate.now());
        setCategory(new CategoryViewModel());
    }

    public TransactionViewModel(final AccountViewModel parentAccountVM,
                                final Transaction transaction) {
        this.parentAccount = parentAccountVM;
        this.internalTransaction = transaction;
    }

    // region Properties for Binding

    public final IntegerProperty amountProperty() {
        return amountProperty;
    }

    public final int getAmount() {
        return this.amountProperty.get();
    }

    public final void setAmount(final int amount) {
        this.amountProperty.set(amount);
    }

    public final ObjectProperty<LocalDate> dateProperty() {
        return this.dateProperty;
    }

    public final LocalDate getDate() {
        return this.dateProperty.get();
    }

    public final void setDate(final LocalDate date) {
        this.dateProperty.set(date);
    }

    public final String getDescription() {
        return this.descriptionProperty.get();
    }

    public final StringProperty descriptionProperty() {
        return this.descriptionProperty;
    }

    public final void setDescription(final String description) {
        this.descriptionProperty.set(description);
    }

    public final StringProperty counterpartyProperty() {
        return this.counterpartyProperty;
    }

    public final String getCounterparty() {
        return this.counterpartyProperty.get();
    }

    public final void setCounterparty(final String counterparty) {
        this.counterpartyProperty.set(counterparty);
    }

    public final BooleanProperty isTransferProperty() {
        return this.isTransferProperty;
    }

    public final boolean getIsTransfer() {
        return this.isTransferProperty.get();
    }

    public final void setIsTransfer(final boolean isInternalTransaction) {
        this.isTransferProperty.set(isInternalTransaction);
    }

    public final ObjectProperty<CategoryViewModel> categoryProperty() {
        return this.categoryProperty;
    }

    public final CategoryViewModel getCategory() {
        return this.categoryProperty.get();
    }

    public final void setCategory(final CategoryViewModel category) {
        this.categoryProperty.set(category);
    }

    public final BooleanProperty isIncomeProperty() {
        return this.isIncomeProperty;
    }

    public final boolean getIsIncome() {
        return this.isIncomeProperty.get();
    }

    public final void setIsIncome(final boolean isIncomeProperty) {
        this.isIncomeProperty.set(isIncomeProperty);
    }

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

    public final void setParentAccount(final AccountViewModel parentAccountVM) {
        this.parentAccount = parentAccountVM;
    }

    public void execute() {
        if (getIsTransfer()) {
            executeAsTransfer();
        } else {
            executeAsExternalTransaction();
        }
    }

    private void executeAsTransfer() {
        Account accountTo = getAccountTo().getAccount();
        Account accountFrom = getAccountFrom().getAccount();

        accountFrom.transferTo(accountTo, getAmount(), getModelDate());
        getAccountTo().registerTransaction(this);
        getAccountFrom().registerTransaction(this);
    }

    private void executeAsExternalTransaction() {
        Account modelAccount = parentAccount.getAccount();
        ExternalTransaction externalTransaction = buildExternalTransaction();
        modelAccount.addExternalTransaction(externalTransaction);
        parentAccount.registerTransaction(this);
    }

    private ExternalTransaction buildExternalTransaction() {
        if (getIsTransfer()) {
            throw new RuntimeException("Can't create external "
                    + "transaction because transaction marked as transfer");
        }

        ExternalTransaction.Builder transactionBuilder = getIsIncome()
                ? ExternalTransaction.incomeBuilder(getAmount())
                : ExternalTransaction.expenseBuilder(getAmount());

        return transactionBuilder
                .date(getModelDate())
                .category(getModelCategory())
                .description(getDescription())
                .counterparty(getCounterparty())
                .build();
    }

    private GregorianCalendar getModelDate() {
        LocalDate localDate = getDate();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return GregorianCalendar.from(zonedDateTime);
    }

    private Category getModelCategory() {
        CategoryViewModel categoryViewModel = getCategory();
        return categoryViewModel.getCategory();
    }
}
