package ru.unn.agile.personalfinance.view.controls;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.converter.CurrencyStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;

class AccountListCellTemplate {
    @FXML
    private Label nameLabel;

    @FXML
    private Label balanceLabel;

    @FXML
    private GridPane root;

    AccountListCellTemplate(final AccountViewModel account) {
        FXMLHelper.load(this, "account-list-cell.fxml");
        setUpBindings(account);
    }

    Node getRootNode() {
        return root;
    }

    private void setUpBindings(final AccountViewModel account) {
        /* */
        Bindings.bindBidirectional(nameLabel.textProperty(), account.nameProperty());

        /* */
        Bindings.bindBidirectional(
                balanceLabel.textProperty(),
                account.balanceProperty(),
                new CurrencyStringConverter());
    }
}
