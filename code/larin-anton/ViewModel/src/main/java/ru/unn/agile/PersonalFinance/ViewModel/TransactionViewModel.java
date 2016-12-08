package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.*;
import ru.unn.agile.PersonalFinance.Model.Transaction;
import ru.unn.agile.PersonalFinance.ViewModel.utils.SavableObject;

import java.time.LocalDate;

public abstract class TransactionViewModel extends SavableObject {
    private static final int DEFAULT_AMOUNT = 100;

    protected final IntegerProperty amountProperty = new SimpleIntegerProperty();

    protected final BooleanProperty isIncomeProperty = new SimpleBooleanProperty();

    protected final ObjectProperty<LocalDate> dateProperty =
            new SimpleObjectProperty<>();

    protected final StringProperty displayTitleProperty = new SimpleStringProperty();

    protected final StringProperty displayCounterpartyProperty = new SimpleStringProperty();

    protected final BooleanProperty isCounterpartyMarkedAsDeletedProperty =
            new SimpleBooleanProperty();

    public TransactionViewModel() {
        setDate(LocalDate.now());
        setAmount(DEFAULT_AMOUNT);
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

    public final BooleanProperty isIncomeProperty() {
        return this.isIncomeProperty;
    }

    public final boolean getIsIncome() {
        return this.isIncomeProperty.get();
    }

    public final void setIsIncome(final boolean isIncomeProperty) {
        this.isIncomeProperty.set(isIncomeProperty);
    }

    public final ReadOnlyStringProperty displayTitleProperty() {
        return displayTitleProperty;
    }

    public final String getDisplayTitle() {
        return displayTitleProperty.get();
    }

    protected final void setDisplayTitle(final String displayTitle) {
        displayTitleProperty.set(displayTitle);
    }

    public final ReadOnlyStringProperty displayCounterpartyProperty() {
        return displayCounterpartyProperty;
    }

    public final String getDisplayCounterparty() {
        return displayCounterpartyProperty.get();
    }

    protected final void setDisplayCounterparty(final String displayCounterparty) {
        displayCounterpartyProperty.set(displayCounterparty);
    }

    public final ReadOnlyBooleanProperty isCounterpartyMarkedAsDeletedProperty() {
        return isCounterpartyMarkedAsDeletedProperty;
    }

    public final boolean isCounterpartyMarkedAsDeleted() {
        return isCounterpartyMarkedAsDeletedProperty.get();
    }

    protected final void markCounterpartyAsDeleted() {
        isCounterpartyMarkedAsDeletedProperty.set(true);
    }

    // endregion
}
