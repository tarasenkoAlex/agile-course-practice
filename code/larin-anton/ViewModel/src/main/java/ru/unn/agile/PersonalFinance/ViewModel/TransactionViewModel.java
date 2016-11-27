package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.*;
import ru.unn.agile.PersonalFinance.Model.Category;
import ru.unn.agile.PersonalFinance.Model.ExternalTransaction;
import ru.unn.agile.PersonalFinance.Model.Transaction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public class TransactionViewModel {
    private final IntegerProperty amountProperty = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>();
    private final StringProperty descriptionProperty = new SimpleStringProperty();
    private final StringProperty counterpartyProperty = new SimpleStringProperty();
    private final BooleanProperty isInternalTransactionProperty = new SimpleBooleanProperty();
    private final ObjectProperty<CategoryViewModel> categoryProperty = new SimpleObjectProperty<>();
    private final BooleanProperty isIncomeProperty = new SimpleBooleanProperty();

    public TransactionViewModel() {
        setDate(LocalDate.now());
        setCategory(new CategoryViewModel());
    }

    public TransactionViewModel(final Transaction transaction) {
        // TODO
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

    public final BooleanProperty isInternalTransactionProperty() {
        return this.isInternalTransactionProperty;
    }

    public final boolean getIsInternalTransaction() {
        return this.isInternalTransactionProperty.get();
    }

    public final void setIsInternalTransaction(final boolean isInternalTransaction) {
        this.isInternalTransactionProperty.set(isInternalTransaction);
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

    public final boolean setIsIncome() {
        return this.isIncomeProperty.get();
    }

    public final void setIsIncome(boolean isIncomeProperty) {
        this.isIncomeProperty.set(isIncomeProperty);
    }

    // endregion

    public ExternalTransaction getExternal() {
        if (getIsInternalTransaction()) {
            throw new RuntimeException("Can't create external "
                    + "transaction because transaction marked as internal");
        }

        CategoryViewModel categoryViewModel = getCategory();
        LocalDate localDate = getDate();

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        GregorianCalendar calendar = GregorianCalendar.from(zonedDateTime);
        Category category = categoryViewModel.getCategory();

        return new ExternalTransaction(getAmount(), getDescription(),
                category, calendar, getCounterparty());
    }

}
