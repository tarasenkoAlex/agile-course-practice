package ru.unn.agile.personalfinance.view.controls;

import javafx.scene.control.ListCell;
import ru.unn.agile.PersonalFinance.ViewModel.ExternalTransactionViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransactionViewModel;

public class TransactionListCell extends ListCell<TransactionViewModel> {

    @Override
    protected void updateItem(final TransactionViewModel item, final boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            // NOTE: Do not create new object here
            if (item instanceof ExternalTransactionViewModel) {
                ExternalTransactionViewModel transaction = (ExternalTransactionViewModel) item;
                ExternalTransactionListCellTemplate transactionView =
                        new ExternalTransactionListCellTemplate(transaction);
                setGraphic(transactionView.getRootNode());
            }
        }
    }
}
