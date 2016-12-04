package ru.unn.agile.personalfinance.view.controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DataContextController implements Initializable {
    private Object dataContext;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        // Do nothing
    }

    public void setDataContext(final Object dataContext) {
        if (this.dataContext != dataContext) {
            updateDataContext(this.dataContext, dataContext);
            this.dataContext = dataContext;
        }
    }

    protected Object getDataContext() {
        return dataContext;
    }

    protected void updateDataContext(final Object oldDataContext,
                                     final Object newDataContext) {
        if (oldDataContext != null) {
            removeBindings(oldDataContext);
        }

        if (newDataContext != null) {
            addBindings(newDataContext);
        }
    }

    protected void removeBindings(final Object oldDataContext) {
        // Do nothing
    }

    protected void addBindings(final Object newDataContext) {
        // Do nothing
    }
}
