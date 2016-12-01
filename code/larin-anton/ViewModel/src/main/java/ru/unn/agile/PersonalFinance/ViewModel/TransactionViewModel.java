package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.*;

import java.time.LocalDate;

public abstract class TransactionViewModel {
    private static final int DEFAULT_AMOUNT = 100;

    private final IntegerProperty amountProperty = new SimpleIntegerProperty();
    private final BooleanProperty isIncomeProperty = new SimpleBooleanProperty();
    private final ObjectProperty<LocalDate> dateProperty =
            new SimpleObjectProperty<>();

    private boolean isSaved;

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

    // endregion

    protected final void markAsSaved() {
        isSaved = true;
    }

    public final boolean isSaved() {
        return isSaved;
    }

    public final void save() {
        if (isSaved) {
            throw new UnsupportedOperationException("Transaction has been already executed");
        }
        saveInternal();
        markAsSaved();
    }

    protected abstract void saveInternal();
}
