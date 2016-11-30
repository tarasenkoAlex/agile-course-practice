package ru.unn.agile.personalfinance.view;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransferViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTransferView implements Initializable {
    private final TransferViewModel transfer =
            new TransferViewModel(ViewModelService.getViewModel());

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
    }

    private void setUpBindings(final LedgerViewModel ledgerVM) {
        /* accountFromComboBox.selected -> transfer.accountFrom */
        ReadOnlyObjectProperty<AccountViewModel> selectedAccountFromProperty =
                accountFromComboBox.getSelectionModel().selectedItemProperty();
        transfer.accountFromProperty().bind(selectedAccountFromProperty);

        /* accountFromComboBox.selected -> transfer.accountTo */
        ReadOnlyObjectProperty<AccountViewModel> selectedAccountToProperty =
                accountToComboBox.getSelectionModel().selectedItemProperty();
        transfer.accountToProperty().bind(selectedAccountToProperty);

    }
}
