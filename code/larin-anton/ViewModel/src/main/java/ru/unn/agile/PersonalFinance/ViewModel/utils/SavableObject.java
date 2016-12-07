package ru.unn.agile.PersonalFinance.ViewModel.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public abstract class SavableObject {
    private boolean isSaved;
    private boolean isEditing;

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

    public final boolean isEditing() { return isEditing; }

    public final void save() {
        if (isSaved) {
            updateInternal();
        } else {
            saveInternal();
            markAsSaved();
        }
        isEditing = false;
    }

    public final void startEditing() {
        if (!isSaved) {
            throw new UnsupportedOperationException("Object should be saved before editing");
        }
        saveState();
        isEditing = true;
    }

    public final void cancelEditing() {
        recoverState();
        isEditing = false;
    }

    protected abstract void saveInternal();
    protected abstract void updateInternal();
    protected abstract void saveState();
    protected abstract void recoverState();
}
