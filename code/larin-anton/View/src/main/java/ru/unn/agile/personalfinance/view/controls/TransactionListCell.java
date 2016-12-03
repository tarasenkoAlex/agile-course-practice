package ru.unn.agile.personalfinance.view.controls;

import com.jfoenix.controls.JFXListCell;
import javafx.scene.Node;
import ru.unn.agile.PersonalFinance.ViewModel.ExternalTransactionViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransactionViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransferViewModel;

public class TransactionListCell extends JFXListCell<TransactionViewModel> {

    @Override
    public void updateItem(final TransactionViewModel item, final boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // NOTE: Do not create new object here
            Node rootNode = null;
            if (item instanceof ExternalTransactionViewModel) {
                ExternalTransactionViewModel transaction = (ExternalTransactionViewModel) item;
                ExternalTransactionListCellTemplate transactionTemplate =
                        new ExternalTransactionListCellTemplate(transaction);
                rootNode = transactionTemplate.getRootNode();
            } else if (item instanceof TransferViewModel) {
                TransferViewModel transfer = (TransferViewModel) item;
                TransferListCellTemplate transferTemplate =
                        new TransferListCellTemplate(transfer);
                rootNode = transferTemplate.getRootNode();
            } else {
                throw new RuntimeException("We can't find template for object of type"
                        + item.getClass().getName());
            }

            setText(null);
            rootNode.setMouseTransparent(true);
            setGraphic(rootNode);
        }
    }
}
