package ru.unn.agile.personalfinance.view.controllers;

import com.cathive.fonts.fontawesome.FontAwesomeIcon;
import com.cathive.fonts.fontawesome.FontAwesomeIconView;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.converter.CurrencyStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.ExternalTransactionViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransactionViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransferViewModel;

public class TransactionListCellController extends DataContextController {
    @FXML
    public FontAwesomeIconView directionIcon;

    @FXML
    private Label counterpartyLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private GridPane root;

    @Override
    protected void addBindings(final Object newDataContext) {
        TransactionViewModel transaction = (TransactionViewModel) newDataContext;
        addCommonBindings(transaction);

        if (transaction.getIsIncome()) {
            root.getStyleClass().add("income");
            directionIcon.setIcon(FontAwesomeIcon.ICON_ARROW_LEFT);
        } else {
            root.getStyleClass().add("expense");
            directionIcon.setIcon(FontAwesomeIcon.ICON_ARROW_RIGHT);
        }

        if (transaction instanceof ExternalTransactionViewModel) {
            addExternalTransactionBindings((ExternalTransactionViewModel) newDataContext);
        } else if (transaction instanceof TransferViewModel) {
            addTransferBindings((TransferViewModel) newDataContext);
        }
    }

    @Override
    protected void removeBindings(final Object oldDataContext) {
        TransactionViewModel transaction = (TransactionViewModel) oldDataContext;

        root.getStyleClass().clear();

        removeCommonBindings(transaction);
    }

    private void addCommonBindings(final TransactionViewModel transaction) {
        /* amountLabel.text <-> transaction.amount */
        Bindings.bindBidirectional(
                amountLabel.textProperty(),
                transaction.amountProperty(),
                new CurrencyStringConverter());

        /* transaction.date <-> dateLabel.text */
        Bindings.bindBidirectional(
                dateLabel.textProperty(),
                transaction.dateProperty(),
                new LocalDateStringConverter());
    }

    private void removeCommonBindings(final TransactionViewModel transaction) {
        descriptionLabel.textProperty().unbind();
        counterpartyLabel.textProperty().unbind();

        Bindings.unbindBidirectional(
                amountLabel.textProperty(),
                transaction.amountProperty());

        Bindings.unbindBidirectional(
                dateLabel.textProperty(),
                transaction.dateProperty());
    }

    private void addExternalTransactionBindings(final ExternalTransactionViewModel transaction) {
        /* transaction.counterparty -> counterpartyLabel.text */
        counterpartyLabel.textProperty().bind(transaction.counterpartyProperty());

        /* transaction.description -> descriptionLabel.text */
        descriptionLabel.textProperty().bind(transaction.descriptionProperty());
    }

    private void addTransferBindings(final TransferViewModel transfer) {
        if (transfer.getIsIncome()) {
            counterpartyLabel.textProperty().bind(
                    transfer.getAccountFrom().nameProperty());
        } else {
            counterpartyLabel.textProperty().bind(
                    transfer.getAccountTo().nameProperty());
        }

        descriptionLabel.setText("Transfer");
    }
}
