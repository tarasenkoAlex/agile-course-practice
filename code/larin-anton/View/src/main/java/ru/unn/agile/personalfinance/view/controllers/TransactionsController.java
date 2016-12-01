package ru.unn.agile.personalfinance.view.controllers;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransactionViewModel;
import ru.unn.agile.personalfinance.view.ViewModelService;
import ru.unn.agile.personalfinance.view.WindowsManager;
import ru.unn.agile.personalfinance.view.controls.AccountListCell;
import ru.unn.agile.personalfinance.view.controls.TransactionListCell;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    @FXML
    private ListView<TransactionViewModel> transactionsList;

    @FXML
    private ListView<AccountViewModel> accountsList;

    @FXML
    private Button addTransferButton;

    @FXML
    private Button addTransactionButton;

    @FXML
    protected void handleAddAccountAction(final ActionEvent event) {
        WindowsManager.getInstance().showAddAccountView(new Stage());
    }

    @FXML
    protected void handleAddTransactionAction(final ActionEvent actionEvent) {
        WindowsManager.getInstance().showAddExternalTransactionView(new Stage());
    }

    @FXML
    protected void handleAddTransferAction(final ActionEvent actionEvent) {
        WindowsManager.getInstance().showAddTransferView(new Stage());
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        setUpBindings(ViewModelService.getViewModel());

        accountsList.setCellFactory(listView -> new AccountListCell());
        transactionsList.setCellFactory(listView -> new TransactionListCell());
    }

    private void setUpBindings(final LedgerViewModel ledgerVM) {
        /* accountsList.selectedItem -> ledgerVM.selectedAccount */
        ReadOnlyObjectProperty<AccountViewModel> selectedItemProperty =
                accountsList.getSelectionModel().selectedItemProperty();
        ledgerVM.selectedAccountProperty().bind(selectedItemProperty);

        /* ledgerVM.canAddTransaction -> addTransactionButton.disabled */
        addTransactionButton.disableProperty().bind(ledgerVM.canAddTransactionProperty().not());

        /* ledgerVM.canAddTransfer-> addTransferButton.disabled */
        addTransferButton.disableProperty().bind(ledgerVM.canAddTransferProperty().not());
    }
}
