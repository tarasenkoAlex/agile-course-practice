package ru.unn.agile.personalfinance.view.controls;

import javafx.scene.control.ListCell;
import ru.unn.agile.PersonalFinance.ViewModel.TransactionViewModel;

public class TransactionListCell extends ListCell<TransactionViewModel> {

    @Override
    protected void updateItem(TransactionViewModel item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            // NOTE: Do not create new object here
            TransactionListCellView transactionView = new TransactionListCellView(item);
            setGraphic(transactionView.getRootNode());
        }
    }
}
