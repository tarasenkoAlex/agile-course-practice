package ru.unn.agile.personalfinance.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.CurrencyStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.CategoryViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.TransactionViewModel;

public class AddTransactionView implements Initializable {
    private final TransactionViewModel transaction = new TransactionViewModel();

    @FXML
    public TextArea descriptionTextArea;

    @FXML
    public ComboBox<CategoryViewModel> categoryComboBox;

    @FXML
    private RadioButton incomeRBtn;

    @FXML
    private TextField transactionAmountField;

    @FXML
    protected void handleAddButtonAction(final ActionEvent actionEvent) {
        LedgerViewModel viewModel = ViewModelService.getViewModel();

        AccountViewModel selectedAccount = viewModel.getSelectedAccount();
        transaction.setParentAccount(selectedAccount);
        transaction.execute();

        WindowsManager.getInstance().goBack();
    }

    @FXML
    protected void handleCancelButtonAction(final ActionEvent actionEvent) {
        WindowsManager.getInstance().goBack();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        LedgerViewModel ledgerVM = ViewModelService.getViewModel();

        /* transactionAmountField.text <-> transaction.amount */
        Bindings.bindBidirectional(
                transactionAmountField.textProperty(),
                transaction.amountProperty(),
                new CurrencyStringConverter());

        /* incomeRBtn.isSelected <-> transaction.isIncome */
        Bindings.bindBidirectional(incomeRBtn.selectedProperty(), transaction.isIncomeProperty());

        /* descriptionTextArea.text <-> transaction.description */
        Bindings.bindBidirectional(descriptionTextArea.textProperty(), transaction.descriptionProperty());

        /* categoryComboBox.selectedItem -> transaction.category */
        ObjectProperty<CategoryViewModel> categoryProperty = transaction.categoryProperty();
        categoryProperty.bind(categoryComboBox.getSelectionModel().selectedItemProperty());

        /* ledgerVM.categories -> categoryComboBox.items */
        categoryComboBox.itemsProperty().bind(ledgerVM.categoriesProperty());

        categoryComboBox.getSelectionModel().selectFirst();
    }
}
