package ru.unn.agile.personalfinance.view.controls;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;

public class AccountListCell extends ListCell<AccountViewModel> {
    public AccountListCell() {
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(final AccountViewModel item, final boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            AccountListCellTemplate view = new AccountListCellTemplate(item);
            setGraphic(view.getRootNode());
        }
    }
}
