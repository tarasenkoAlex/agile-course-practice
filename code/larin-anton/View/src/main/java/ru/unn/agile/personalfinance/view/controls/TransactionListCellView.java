package ru.unn.agile.personalfinance.view.controls;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.converter.CurrencyStringConverter;

import ru.unn.agile.PersonalFinance.ViewModel.TransactionViewModel;

class TransactionListCellView {
    @FXML
    private Label counterpartyLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private GridPane root;

    TransactionListCellView(final TransactionViewModel transaction) {
        loadFxml(transaction);
        setUpBindings(transaction);
    }

    private void loadFxml(final TransactionViewModel transaction) {
        if (transaction.getIsIncome()) {
            FXMLHelper.load(this, "transaction-list-cell-income.fxml");
        } else {
            FXMLHelper.load(this, "transaction-list-cell-expense.fxml");
        }
    }

    Node getRootNode() {
        return root;
    }

    private void setUpBindings(final TransactionViewModel transaction) {
        /* */
        Bindings.bindBidirectional(
                amountLabel.textProperty(),
                transaction.amountProperty(),
                new CurrencyStringConverter());

        /* */
        Bindings.bindBidirectional(
                counterpartyLabel.textProperty(),
                transaction.counterpartyProperty());
    }
}
