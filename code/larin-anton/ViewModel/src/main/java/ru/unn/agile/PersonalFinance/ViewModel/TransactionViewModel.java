package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.*;
import ru.unn.agile.PersonalFinance.ViewModel.utils.SavableObject;

import java.time.LocalDate;

public abstract class TransactionViewModel extends SavableObject {
    private static final int DEFAULT_AMOUNT = 100;

    private final IntegerProperty amountProperty = new SimpleIntegerProperty();

    private final BooleanProperty isIncomeProperty = new SimpleBooleanProperty();

    private final ObjectProperty<LocalDate> dateProperty =
            new SimpleObjectProperty<>();

    private final StringProperty displayTitleProperty = new SimpleStringProperty();

    private final StringProperty displayCounterpartyProperty = new SimpleStringProperty();

    private final BooleanProperty isCounterpartyMarkedAsDeletedProperty =
            new SimpleBooleanProperty();

    public TransactionViewModel() {
        setDate(LocalDate.now());
        setAmount(DEFAULT_AMOUNT);
    }

    protected StringProperty displayTitleMutableProperty() {
        return displayTitleProperty;
    }

    protected StringProperty displayCounterpartyMutableProperty() {
        return displayCounterpartyProperty;
    }

    protected BooleanProperty isCounterpartyMarkedAsDeletedMutableProperty() {
        return isCounterpartyMarkedAsDeletedProperty;
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
