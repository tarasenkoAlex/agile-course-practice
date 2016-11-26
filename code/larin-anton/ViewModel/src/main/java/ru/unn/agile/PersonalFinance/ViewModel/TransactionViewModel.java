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

    public TransactionViewModel() {
        setDate(LocalDate.now());
        setCategory(new CategoryViewModel());
    }

    public TransactionViewModel(final Transaction transaction) {
        // TODO
    }

    public IntegerProperty amountProperty() {
        return amountProperty;
    }

    public int getAmmount() {
        return this.amountProperty.get();
    }

    public void setAmount(final int amount) {
        this.amountProperty.set(amount);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return this.dateProperty;
    }

    public LocalDate getDate() {
        return this.dateProperty.get();
    }

    public void setDate(final LocalDate date) {
        this.dateProperty.set(date);
    }

    public String getDescription() {
        return this.descriptionProperty.get();
    }

    public StringProperty descriptionProperty() {
        return this.descriptionProperty;
    }

    public void setDescription(final String description) {
        this.descriptionProperty.set(description);
    }

    public StringProperty counterpartyProperty() {
        return this.counterpartyProperty;
    }

    public String getCounterparty() {
        return this.counterpartyProperty.get();
    }

    public void setCounterparty(final String counterparty) {
        this.counterpartyProperty.set(counterparty);
    }

    public BooleanProperty isInternalTransactionProperty() {
        return this.isInternalTransactionProperty;
    }

    public boolean getIsInternalTransaction() {
        return this.isInternalTransactionProperty.get();
    }

    public void setIsInternalTransaction(final boolean isInternalTransaction) {
        this.isInternalTransactionProperty.set(isInternalTransaction);
    }

    public ObjectProperty<CategoryViewModel> categoryProperty() {
        return this.categoryProperty;
    }

    public CategoryViewModel getCategory() {
        return this.categoryProperty.get();
    }

    public void setCategory(final CategoryViewModel category) {
        this.categoryProperty.set(category);
    }

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

        return new ExternalTransaction(getAmmount(), getDescription(),
                category, calendar, getCounterparty());
    }
}
