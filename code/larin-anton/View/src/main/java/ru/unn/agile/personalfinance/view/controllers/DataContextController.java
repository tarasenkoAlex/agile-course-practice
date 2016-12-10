package ru.unn.agile.personalfinance.view.controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DataContextController<T> implements Initializable {
    private T dataContext;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        // Do nothing
    }

    public void setDataContext(final T dataContext) {
        if (this.dataContext != dataContext) {
            T oldDataContext = this.dataContext;
            this.dataContext = dataContext;
            updateDataContext(oldDataContext, dataContext);
        }
    }

    protected T getDataContext() {
        return dataContext;
    }

    protected void updateDataContext(final T oldDataContext,
                                     final T newDataContext) {
        if (oldDataContext != null) {
            removeBindings(oldDataContext);
        }

        if (newDataContext != null) {
            addBindings(newDataContext);
        }
    }

    protected void removeBindings(final T oldDataContext) {
        // Do nothing
    }

    protected void addBindings(final T newDataContext) {
        // Do nothing
    }
}
