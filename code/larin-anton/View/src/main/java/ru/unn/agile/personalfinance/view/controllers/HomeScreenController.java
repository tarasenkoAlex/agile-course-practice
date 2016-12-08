package ru.unn.agile.personalfinance.view.controllers;

import com.jfoenix.controls.JFXListView;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.unn.agile.PersonalFinance.ViewModel.*;
import ru.unn.agile.personalfinance.view.ViewModelService;
import ru.unn.agile.personalfinance.view.WindowsManager;
import ru.unn.agile.personalfinance.view.controls.DataContextListCell;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController extends DataContextController {
    private final LedgerViewModel ledger = ViewModelService.getViewModel();

    @FXML
    private JFXListView<TransactionViewModel> transactionsList;

    @FXML
    private JFXListView<AccountViewModel> accountsList;

    @FXML
    private Button addTransferButton;

    @FXML
    private Button addTransactionButton;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        setUpBindings(ViewModelService.getViewModel());

        accountsList.setCellFactory(listView ->
                new DataContextListCell<>("account-list-cell.fxml"));
        transactionsList.setCellFactory(listView ->
                new DataContextListCell<>("transaction-list-cell.fxml"));
    }

    private void setUpBindings(final LedgerViewModel ledger) {
        /* accountsList.selectedItem -> ledger.selectedAccount */
        ReadOnlyObjectProperty<AccountViewModel> selectedItemProperty =
                accountsList.getSelectionModel().selectedItemProperty();
        ledger.selectedAccountProperty().bind(selectedItemProperty);

        /* ledger.canAddTransaction -> addTransactionButton.disabled */
        addTransactionButton.disableProperty().bind(ledger.canAddTransactionProperty().not());

        /* ledger.canAddTransfer-> addTransferButton.disabled */
        addTransferButton.disableProperty().bind(ledger.canAddTransferProperty().not());

        /* ledger.accounts -> accountsList.items */
        accountsList.itemsProperty().bind(ledger.accountsProperty());

        /* ledger.selected.transactions -> transactionsList.items */
        ledger.selectedAccountProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                transactionsList.itemsProperty().unbind();
                transactionsList.setItems(null);
            } else {
                transactionsList.itemsProperty().bind(newValue.transactionsProperty());
            }
        });
    }

    @FXML
    protected void handleAddAccountAction(final ActionEvent event) {
        AccountViewModel newAccount = new AccountViewModel(ledger);
        WindowsManager.getInstance().showEditAccountView(newAccount);
    }

    @FXML
    protected void handleAddTransactionAction(final ActionEvent actionEvent) {
        ExternalTransactionViewModel transaction =
                new ExternalTransactionViewModel(getSelectedAccount());
        WindowsManager.getInstance().showEditExternalTransactionView(transaction);
    }

    @FXML
    protected void handleAddTransferAction(final ActionEvent actionEvent) {
        TransferViewModel newTransfer = new TransferViewModel();
        WindowsManager.getInstance().showAddTransferView(newTransfer);
    }


    @FXML
    private void handleDeleteAccountAction(final ActionEvent actionEvent) {
        getSelectedAccount().delete();
    }

    @FXML
    private void handleEditAccountAction(final ActionEvent actionEvent) {
        WindowsManager.getInstance().showEditAccountView(getSelectedAccount());
    }

    @FXML
    private void handleDeleteTransactionAction(final ActionEvent actionEvent) {
        getSelectedTransaction().delete();
    }

    private AccountViewModel getSelectedAccount() {
        return accountsList.getSelectionModel().getSelectedItem();
    }

    private TransactionViewModel getSelectedTransaction() {
        return transactionsList.getSelectionModel().getSelectedItem();
    }
}
