package ru.unn.agile.PersonalFinance.ViewModel.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public abstract class SavableObject {
    private boolean isSaved;
    private boolean isEditing;
    private boolean isDeleted;

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

    public final boolean isDeleted() {
        return isDeleted;
    }

    public final void save() {
        checkForDeletion();
        if (isSaved) {
            updateInternal();
        } else {
            saveInternal();
            markAsSaved();
        }
        isEditing = false;
    }

    public final void delete() {
        checkForDeletion();
        deleteInternal();
        isDeleted = true;
    }

    public final void startEditing() {
        checkForDeletion();
        if (isSaved) {
            saveState();
            isEditing = true;
        }
    }

    public final void cancelEditing() {
        checkForDeletion();
        if (isEditing) {
            recoverState();
            isEditing = false;
        }
    }

    protected abstract void saveInternal();
    protected abstract void updateInternal();
    protected abstract void deleteInternal();
    protected abstract void saveState();
    protected abstract void recoverState();

    private void checkForDeletion() {
        if (isDeleted) {
            throw new UnsupportedOperationException("Object was deleted");
        }
    }
}
