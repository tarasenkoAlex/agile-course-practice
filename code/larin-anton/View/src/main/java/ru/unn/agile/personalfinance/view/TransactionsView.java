package ru.unn.agile.personalfinance.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class TransactionsView {
    @FXML
    protected void handleAddAccountAction(final ActionEvent event) {
        WindowsManager.getInstance().showAddAccountView(new Stage());
    }
}
