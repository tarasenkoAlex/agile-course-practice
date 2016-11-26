package ru.unn.agile.personalfinance.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransactionViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTransactionView implements Initializable {
    private final TransactionViewModel transaction = new TransactionViewModel();

    @FXML
    protected void handleAddButtonAction(final ActionEvent actionEvent) {
        LedgerViewModel viewModel = ViewModelService.getViewModel();
        AccountViewModel selectedAccount = viewModel.getSelectedAccount();
        selectedAccount.addTransaction(transaction);
        WindowsManager.getInstance().goBack();
    }

    @FXML
    protected void handleCancelButtonAction(final ActionEvent actionEvent) {
        WindowsManager.getInstance().goBack();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        // TODO
    }
}
