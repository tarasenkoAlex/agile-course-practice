package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.PersonalFinance.Model.Category;

public class CategoryViewModel {
    private final StringProperty nameProperty = new SimpleStringProperty();

    public CategoryViewModel() {
        setName("New category");
    }

    public StringProperty nameProperty() {
        return this.nameProperty;
    }

    public String getName() {
        return this.nameProperty.get();
    }

    public void setName(final String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public Category getCategory() {
        return new Category(getName());
    }
}
