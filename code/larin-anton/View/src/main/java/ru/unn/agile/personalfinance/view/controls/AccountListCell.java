package ru.unn.agile.personalfinance.view.controls;

import com.jfoenix.controls.JFXListCell;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;

public class AccountListCell extends JFXListCell<AccountViewModel> {
    public AccountListCell() {
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    public void updateItem(final AccountViewModel item, final boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            // NOTE: Do not create new object!
            AccountListCellTemplate template = new AccountListCellTemplate(item);
            Node rootNode = template.getRootNode();
            rootNode.setMouseTransparent(true);
            setGraphic(rootNode);
        }
    }
}
