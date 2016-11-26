package ru.unn.agile.personalfinance.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.util.converter.NumberStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAccountView implements Initializable {
    private final AccountViewModel account = new AccountViewModel();

    @FXML
    TextField nameField;

    @FXML
    TextField balanceField;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        /* nameField.text <-> account.name */
        Bindings.bindBidirectional(nameField.textProperty(), account.nameProperty());

        /* balanceField.text <-> account.balance */
        Bindings.bindBidirectional(balanceField.textProperty(), account.balanceProperty());
    }

    public void handleAddButton(final ActionEvent actionEvent) {
        ViewModelService.getViewModel().addAccount(account);
        WindowsManager.getInstance().goBack();
    }

    public void handleCancelButton(final ActionEvent actionEvent) {
        WindowsManager.getInstance().goBack();
    }
}
