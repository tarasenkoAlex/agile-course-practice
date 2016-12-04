package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.PersonalFinance.Model.Category;
import ru.unn.agile.PersonalFinance.ViewModel.utils.SavableObject;

public class CategoryViewModel extends SavableObject {
    private Category internalCategory;
    private final StringProperty nameProperty = new SimpleStringProperty();

    public CategoryViewModel() {
        setName("New category");
        save();
    }

    public CategoryViewModel(final String name) {
        setName(name);
        save();
    }

    // region Properties for Binding

    public final StringProperty nameProperty() {
        return this.nameProperty;
    }

    public final String getName() {
        return this.nameProperty.get();
    }

    public final void setName(final String name) {
        this.nameProperty.set(name);
    }

    // endregion

    public Category getModelCategory() {
        if (internalCategory == null) {
            throw new UnsupportedOperationException("Category should be "
                    + "saved before accessing to the model category");
        }
        return internalCategory;
    }

    @Override
    protected void saveInternal() {
        internalCategory = new Category(getName());
    }
}
