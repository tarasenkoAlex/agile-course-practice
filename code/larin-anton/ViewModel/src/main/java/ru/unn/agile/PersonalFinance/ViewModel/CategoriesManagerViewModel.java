package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.PersonalFinance.ViewModel.utils.StringHelper;

import java.util.ArrayList;

public class CategoriesManagerViewModel {
    private final StringProperty newCategoryNameProperty = new SimpleStringProperty();
    private final BooleanProperty isAbleToAddNewCategoryProperty = new SimpleBooleanProperty();
    private final ListProperty<CategoryViewModel> categoriesProperty =
            new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));

    CategoriesManagerViewModel() {
        categoriesProperty.add(new CategoryViewModel("Category 1"));
        categoriesProperty.add(new CategoryViewModel("Category 2"));
        categoriesProperty.add(new CategoryViewModel("Category 3"));
        categoriesProperty.add(new CategoryViewModel("Category 4"));
        setUpBindings();
    }

    // region Properties for Binding

    public final StringProperty newCategoryNameProperty() {
        return newCategoryNameProperty;
    }

    public final String getNewCategoryName() {
        return newCategoryNameProperty.get();
    }

    public final void setNewCategoryName(final String newCategoryName) {
        newCategoryNameProperty.set(newCategoryName);
    }

    public final ReadOnlyBooleanProperty isAbleToAddNewCategoryProperty() {
        return isAbleToAddNewCategoryProperty;
    }

    public final boolean isAbleToAddNewCategory() {
        return isAbleToAddNewCategoryProperty.get();
    }

    private void setIsAbleToAddNewCategory(final boolean isAbleToAddNewCategory) {
        isAbleToAddNewCategoryProperty.set(isAbleToAddNewCategory);
    }

    public final ReadOnlyListProperty<CategoryViewModel> categoriesProperty() {
        return categoriesProperty;
    }

    public final ObservableList<CategoryViewModel> getCategories() {
        return categoriesProperty.get();
    }

    // endregion

    public void saveCategory() {
        String newCategoryName = getNewCategoryName().trim();
        categoriesProperty.add(new CategoryViewModel(newCategoryName));
        setNewCategoryName(null);
    }

    private void setUpBindings() {
        BooleanBinding isCategoryExists = Bindings.createBooleanBinding(() ->
            hasCategoryWithName(getNewCategoryName()), newCategoryNameProperty);

        BooleanBinding isNewCategoryNameEmpty =
                StringHelper.isNullOrEmpty(newCategoryNameProperty);

        isAbleToAddNewCategoryProperty.bind(Bindings.and(
                isNewCategoryNameEmpty.not(),
                isCategoryExists.not()));
    }

    private boolean hasCategoryWithName(final String categoryName) {
        return categoriesProperty.stream().anyMatch(category ->
                StringHelper.areEqualTrimmed(category.getName(), categoryName));
    }
}
