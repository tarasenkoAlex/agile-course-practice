package ru.unn.agile.personalfinance.view.controllers;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.CurrencyStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.personalfinance.view.WindowsManager;

public class AddAccountController extends DataContextController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField balanceField;

    @FXML
    private Button addButton;

    public void handleAddButton(final ActionEvent actionEvent) {
        ((AccountViewModel) getDataContext()).save();
        WindowsManager.getInstance().goBack();
    }

    public void handleCancelButton(final ActionEvent actionEvent) {
        WindowsManager.getInstance().goBack();
    }


    @Override
    protected void removeBindings(final Object oldDataContext) {
        final AccountViewModel oldAccount = (AccountViewModel) oldDataContext;
        Bindings.unbindBidirectional(nameField.textProperty(), oldAccount.nameProperty());
        Bindings.unbindBidirectional(balanceField.textProperty(), oldAccount.balanceProperty());
        addButton.disableProperty().unbind();
    }

    @Override
    protected void addBindings(final Object newDataContext) {
        final AccountViewModel newAccount = (AccountViewModel) newDataContext;

        /* nameField.text <-> account.name */
        Bindings.bindBidirectional(nameField.textProperty(), newAccount.nameProperty());

            /* balanceField.text <-> account.balance */
        Bindings.bindBidirectional(
                balanceField.textProperty(),
                newAccount.balanceProperty(),
                new CurrencyStringConverter());

            /* account.isAbleToSave -> addButton.disabled */
        addButton.disableProperty().bind(newAccount.isAbleToSaveProperty().not());
    }
}
