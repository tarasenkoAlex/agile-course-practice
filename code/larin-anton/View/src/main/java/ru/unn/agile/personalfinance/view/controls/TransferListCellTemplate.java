package ru.unn.agile.personalfinance.view.controls;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.converter.CurrencyStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransferViewModel;
import ru.unn.agile.personalfinance.view.utils.FXMLHelper;

class TransferListCellTemplate {
    @FXML
    private Label fromAccountNameLabel;

    @FXML
    private Label toAccountNameLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private GridPane root;

    TransferListCellTemplate(final TransferViewModel transfer) {
        loadFxml(transfer);
        setUpBindings(transfer);
    }

    private void loadFxml(final TransferViewModel transfer) {
        if (transfer.getIsIncome()) {
            FXMLHelper.applyTemplate(this, "transfer-list-cell-income.fxml");
        } else {
            FXMLHelper.applyTemplate(this, "transfer-list-cell-outcome.fxml");
        }
    }

    Node getRootNode() {
        return root;
    }

    private void setUpBindings(final TransferViewModel transfer) {
        /* */
        Bindings.bindBidirectional(
                amountLabel.textProperty(),
                transfer.amountProperty(),
                new CurrencyStringConverter());

        if (fromAccountNameLabel != null) {
            /* */
            AccountViewModel accountFrom = transfer.getAccountFrom();
            fromAccountNameLabel.textProperty().bind(accountFrom.nameProperty());
        }

        if (toAccountNameLabel != null) {
            /* */
            AccountViewModel accountTo = transfer.getAccountTo();
            toAccountNameLabel.textProperty().bind(accountTo.nameProperty());
        }
    }
}
