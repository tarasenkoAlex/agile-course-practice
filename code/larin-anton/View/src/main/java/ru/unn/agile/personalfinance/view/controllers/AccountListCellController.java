package ru.unn.agile.personalfinance.view.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import javafx.util.converter.CurrencyStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;

public class AccountListCellController extends DataContextController {
    public GridPane root;
    @FXML
    private Label nameLabel;

    @FXML
    private Label balanceLabel;

    @FXML
    private ContextMenu contextMenu;

    @Override
    protected void addBindings(final Object newDataContext) {
        AccountViewModel account = (AccountViewModel) newDataContext;

        /* */
        nameLabel.textProperty().bind(account.nameProperty());

        /* */
        Bindings.bindBidirectional(
                balanceLabel.textProperty(),
                account.balanceProperty(),
                new CurrencyStringConverter());
    }

    @Override
    protected void removeBindings(final Object oldDataContext) {
        AccountViewModel account = (AccountViewModel) oldDataContext;

        nameLabel.textProperty().unbind();

        Bindings.unbindBidirectional(
                balanceLabel.textProperty(),
                account.balanceProperty());
    }

    @FXML
    private void showContextMenu(final ContextMenuEvent contextMenuEvent) {
        contextMenu.show(root, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
    }
}
