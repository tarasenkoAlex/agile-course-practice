package ru.unn.agile.personalfinance.view;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransactionViewModel;
import ru.unn.agile.personalfinance.view.controls.AccountListCell;
import ru.unn.agile.personalfinance.view.controls.TransactionListCell;

public class TransactionsView implements Initializable {
    @FXML
    private ListView<TransactionViewModel> transactionsList;

    @FXML
    private ListView<AccountViewModel> accountsList;

    @FXML
    protected void handleAddAccountAction(final ActionEvent event) {
        WindowsManager.getInstance().showAddAccountView(new Stage());
    }

    @FXML
    protected void handleAddTransactionAction(final ActionEvent actionEvent) {
        WindowsManager.getInstance().showAddTransactionView(new Stage());
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
    }
}
