package ru.unn.agile.personalfinance.view.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;

public class EditCategoriesController extends DataContextController {

    @FXML
    private JFXButton addCategoryButton;

    @FXML
    private JFXListView categoriesList;

    @Override
    protected void addBindings(final Object newDataContext) {
        super.addBindings(newDataContext);
    }

    @Override
    protected void removeBindings(final Object oldDataContext) {
        super.removeBindings(oldDataContext);
    }


}
