package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public abstract class TransactionViewModel {
    private static final int DEFAULT_AMOUNT = 100;

    private final IntegerProperty amountProperty = new SimpleIntegerProperty();
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
