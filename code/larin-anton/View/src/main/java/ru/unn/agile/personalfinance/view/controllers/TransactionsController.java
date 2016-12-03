package ru.unn.agile.personalfinance.view.controllers;

import com.jfoenix.controls.JFXListView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private JFXListView<TransactionViewModel> transactionsList;

    @FXML
    private JFXListView<AccountViewModel> accountsList;

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
            if (newValue != null) {
                transactionsList.itemsProperty().bind(newValue.transactionsProperty());
            } else {
                transactionsList.itemsProperty().unbind();
            }
        });
    }
}
