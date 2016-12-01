package ru.unn.agile.personalfinance.view.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.CurrencyStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransferViewModel;
import ru.unn.agile.personalfinance.view.ViewModelService;
import ru.unn.agile.personalfinance.view.WindowsManager;
import ru.unn.agile.personalfinance.view.controls.StringListCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTransferController implements Initializable {
    private final TransferViewModel transfer =
            new TransferViewModel(ViewModelService.getViewModel());

    private final static StringListCellFactory<AccountViewModel> accountListCellFactory =
            new StringListCellFactory<>(account -> account.getName());

    @FXML
    private ComboBox<AccountViewModel> accountFromComboBox;

    @FXML
    private ComboBox<AccountViewModel> accountToComboBox;

    @FXML
    private TextField amountField;

    @FXML
    protected void handleAddButtonAction(final ActionEvent actionEvent) {
        transfer.save();
        WindowsManager.getInstance().goBack();
    }

    @FXML
    protected void handleCancelButtonAction(final ActionEvent actionEvent) {
        WindowsManager.getInstance().goBack();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        setUpBindings(ViewModelService.getViewModel());
        setUpAccountComboBox(accountFromComboBox, 0);
        setUpAccountComboBox(accountToComboBox, 1);
    }

    private void setUpBindings(final LedgerViewModel ledgerVM) {
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
    }

    private void setUpAccountComboBox(final ComboBox<AccountViewModel> comboBox,
                                      final int selectedIndex) {
        comboBox.setCellFactory(accountListCellFactory);
        comboBox.setButtonCell(accountListCellFactory.call(null));
        comboBox.getSelectionModel().select(selectedIndex);
    }
}
