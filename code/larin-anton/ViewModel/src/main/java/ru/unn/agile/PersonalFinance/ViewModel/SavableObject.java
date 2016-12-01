package ru.unn.agile.PersonalFinance.ViewModel;

public abstract class SavableObject {
    private boolean isSaved;

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
