package ru.unn.agile.personalfinance.view;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsView implements Initializable {

    @FXML
    private ViewModelSource vmSource;

    @FXML
    private ListView<AccountViewModel> accountList;

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
        LedgerViewModel ledgerVM = vmSource.getModel();

        ReadOnlyObjectProperty<AccountViewModel> selectedItemProperty =
                accountList.getSelectionModel().selectedItemProperty();
        ledgerVM.selectedAccountProperty().bind(selectedItemProperty);
    }
}
