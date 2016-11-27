package ru.unn.agile.personalfinance.view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.util.converter.CurrencyStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransactionViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTransactionView implements Initializable {
    private final TransactionViewModel transaction = new TransactionViewModel();

    @FXML
    private ToggleGroup incomeOrExpenseGroup;

    @FXML
    private RadioButton incomeRBtn;

    @FXML
    private RadioButton expenseRBtn;

    @FXML
    private TextField transactionAmountField;

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
        /* transactionAmountField.text <-> transaction.amount */
        Bindings.bindBidirectional(
                transactionAmountField.textProperty(),
                transaction.amountProperty(),
                new CurrencyStringConverter());

        /* incomeRBtn.isSelected <-> transaction.isIncome */
        Bindings.bindBidirectional(incomeRBtn.selectedProperty(), transaction.isIncomeProperty());
    }
}
