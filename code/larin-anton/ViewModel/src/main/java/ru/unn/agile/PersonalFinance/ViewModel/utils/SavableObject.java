package ru.unn.agile.PersonalFinance.ViewModel.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public abstract class SavableObject {
    private boolean isSaved;
    private boolean isEditing;

    protected BooleanProperty isAbleToSaveProperty = new SimpleBooleanProperty();

    private BooleanProperty isDeletedProperty = new SimpleBooleanProperty();

    // region Properties for Bindings

    public final ReadOnlyBooleanProperty isAbleToSaveProperty() {
        return this.isAbleToSaveProperty;
    }

    public final boolean getIsIsAbleToSave() {
        return this.isAbleToSaveProperty.get();
    }

    protected final void setIsAbleToSave(final boolean isAbleToSave) {
        this.isAbleToSaveProperty.set(isAbleToSave);
    }

    public final ReadOnlyBooleanProperty isDeletedProperty() {
        return isDeletedProperty;
    }

    public final boolean isDeleted() {
        return isDeletedProperty.get();
    }

    protected void markAsDeleted() {
        isDeletedProperty.set(true);
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

        if (!isSaved) {
            throw new UnsupportedOperationException(
                    "Object should be saved before deletion");
        }

        if (isEditing) {
            throw new UnsupportedOperationException(
                    "Object is in modifiable state, save or revert "
                    + "changes before deletion");
        }

        deleteInternal();
        markAsDeleted();
    }

    public final void startChanging() {
        checkForDeletion();
        if (isSaved) {
            saveState();
            isEditing = true;
        }
    }

    public final void revertChanges() {
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
        if (isDeleted()) {
            throw new UnsupportedOperationException("Object was deleted");
        }
    }
}
