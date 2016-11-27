package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.PersonalFinance.Model.Category;

public class CategoryViewModel {
    private final StringProperty nameProperty = new SimpleStringProperty();

    public CategoryViewModel() {
        setName("New category");
    }

    // region Properties for Binding

    public final StringProperty nameProperty() {
        return this.nameProperty;
    }

    public final String getName() {
        return this.nameProperty.get();
    }

    public final void setName(final String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    // endregion

    public Category getCategory() {
        return new Category(getName());
    }
}
