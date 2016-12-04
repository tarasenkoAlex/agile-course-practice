package ru.unn.agile.personalfinance.view.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.CurrencyStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransferViewModel;
import ru.unn.agile.personalfinance.view.WindowsManager;
import ru.unn.agile.personalfinance.view.controls.StringListCellFactory;
import ru.unn.agile.personalfinance.view.utils.Converters;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTransferController extends DataContextController {
    private final static StringListCellFactory<AccountViewModel> accountListCellFactory =
            new StringListCellFactory<>(account -> account.getName());

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXComboBox<AccountViewModel> accountFromComboBox;

    @FXML
    private JFXComboBox<AccountViewModel> accountToComboBox;

    @FXML
    private TextField amountField;

    @FXML
    public Button addButton;

    @FXML
    protected void handleAddButtonAction(final ActionEvent actionEvent) {
        ((TransferViewModel) getDataContext()).save();
        WindowsManager.getInstance().goBack();
    }

    @FXML
    protected void handleCancelButtonAction(final ActionEvent actionEvent) {
        WindowsManager.getInstance().goBack();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        setUpAccountComboBox(accountFromComboBox);
        setUpAccountComboBox(accountToComboBox);
    }

    @Override
    protected void removeBindings(final Object oldDataContext) {
        final TransferViewModel transfer = (TransferViewModel) oldDataContext;

        Bindings.unbindBidirectional(
                amountField.textProperty(),
                transfer.amountProperty());

        transfer.accountFromProperty().unbind();

        transfer.accountToProperty().unbind();

        Bindings.unbindBidirectional(
                datePicker.valueProperty(),
                transfer.dateProperty());

        addButton.disableProperty().unbind();
    }

    @Override
    protected void addBindings(final Object newDataContext) {
        final TransferViewModel transfer = (TransferViewModel) newDataContext;

        /* amountField.text <-> transfer.amount */
        Bindings.bindBidirectional(
                amountField.textProperty(),
                transfer.amountProperty(),
                new CurrencyStringConverter());

        /* accountFromComboBox.selected -> transfer.accountFrom */
        ReadOnlyObjectProperty<AccountViewModel> selectedAccountFromProperty =
                accountFromComboBox.getSelectionModel().selectedItemProperty();
        transfer.accountFromProperty().bind(selectedAccountFromProperty);

        /* accountFromComboBox.selected -> transfer.accountTo */
        ReadOnlyObjectProperty<AccountViewModel> selectedAccountToProperty =
                accountToComboBox.getSelectionModel().selectedItemProperty();
        transfer.accountToProperty().bind(selectedAccountToProperty);

        /* datePicker.value <-> transfer.date */
        Bindings.bindBidirectional(
                datePicker.valueProperty(),
                transfer.dateProperty());

        /* transfer.isAbleToSave -> addButton.disabled */
        addButton.disableProperty().bind(transfer.isAbleToSaveProperty().not());

        accountFromComboBox.getSelectionModel().select(0);
        accountToComboBox.getSelectionModel().select(1);
    }

    private void setUpAccountComboBox(final ComboBox<AccountViewModel> comboBox) {
        comboBox.setCellFactory(accountListCellFactory);
        comboBox.setConverter(Converters.getAccountToStringConverter());
    }
}
