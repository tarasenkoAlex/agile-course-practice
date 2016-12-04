package ru.unn.agile.PersonalFinance.ViewModel.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public abstract class SavableObject {
    private boolean isSaved;
    protected BooleanProperty isAbleToSaveProperty = new SimpleBooleanProperty();

    // region Properties for Bindings

    public final BooleanProperty isAbleToSaveProperty() {
        return this.isAbleToSaveProperty;
    }

    public final boolean getIsIsAbleToSave() {
        return this.isAbleToSaveProperty.get();
    }

    protected void setIsAbleToSave(final boolean isAbleToSave) {
        this.isAbleToSaveProperty.set(isAbleToSave);
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
            throw new UnsupportedOperationException("Object has been already saved");
        }
        saveInternal();
        markAsSaved();
    }

    protected abstract void saveInternal();
}
