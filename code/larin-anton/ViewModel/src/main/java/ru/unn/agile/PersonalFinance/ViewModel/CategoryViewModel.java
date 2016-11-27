package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.PersonalFinance.Model.Category;

public class CategoryViewModel {
    private final Category internalCategory;
    private final StringProperty nameProperty = new SimpleStringProperty();

    public CategoryViewModel() {
        this.internalCategory = new Category("New category");
    }

    public CategoryViewModel(final String name) {
        this.internalCategory = new Category(name);
    }

    // region Properties for Binding

    public final StringProperty nameProperty() {
        return this.nameProperty;
    }

    public final String getName() {
        return this.nameProperty.get();
    }

    // endregion

    public Category getCategory() {
        return internalCategory;
    }
}
