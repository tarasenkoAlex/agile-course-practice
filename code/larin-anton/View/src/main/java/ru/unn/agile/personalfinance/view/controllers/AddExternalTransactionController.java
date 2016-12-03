package ru.unn.agile.personalfinance.view.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.CurrencyStringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.CategoryViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.ExternalTransactionViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.LedgerViewModel;
import ru.unn.agile.personalfinance.view.ViewModelService;
import ru.unn.agile.personalfinance.view.WindowsManager;
import ru.unn.agile.personalfinance.view.controls.StringListCellFactory;
import ru.unn.agile.personalfinance.view.utils.Converters;

import java.net.URL;
import java.util.ResourceBundle;

public class AddExternalTransactionController implements Initializable {
    private final ExternalTransactionViewModel transaction =
            new ExternalTransactionViewModel(ViewModelService.getViewModel());

    private final static StringListCellFactory<CategoryViewModel> categoryListCellFactory =
            new StringListCellFactory<>(category -> category.getName());

    @FXML
    private Button addButton;

    @FXML
    private TextField counterpartyField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private ComboBox<CategoryViewModel> categoryComboBox;

    @FXML
    private RadioButton incomeRBtn;

    @FXML
    private TextField transactionAmountField;

    @FXML
    protected void handleAddButtonAction(final ActionEvent actionEvent) {
        transaction.save();
        WindowsManager.getInstance().goBack();
    }

    @FXML
    protected void handleCancelButtonAction(final ActionEvent actionEvent) {
        WindowsManager.getInstance().goBack();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        categoryComboBox.setCellFactory(categoryListCellFactory);
        categoryComboBox.setConverter(Converters.getCategoryToStringConverter());
        categoryComboBox.getSelectionModel().selectFirst();

        setUpBindings(ViewModelService.getViewModel());
    }

    private void setUpBindings(final LedgerViewModel ledgerVM) {
        /* transactionAmountField.text <-> transaction.amount */
        Bindings.bindBidirectional(
                transactionAmountField.textProperty(),
                transaction.amountProperty(),
                new CurrencyStringConverter());

        /* incomeRBtn.isSelected <-> transaction.isIncome */
        Bindings.bindBidirectional(
                incomeRBtn.selectedProperty(),
                transaction.isIncomeProperty());

        /* descriptionTextArea.text <-> transaction.description */
        Bindings.bindBidirectional(
                descriptionTextArea.textProperty(),
                transaction.descriptionProperty());

        /* categoryComboBox.selectedItem -> transaction.category */
        ObjectProperty<CategoryViewModel> categoryProperty = transaction.categoryProperty();
        categoryProperty.bind(categoryComboBox.getSelectionModel().selectedItemProperty());

        /* counterpartyField.text <-> transaction.counterparty */
        Bindings.bindBidirectional(
                counterpartyField.textProperty(),
                transaction.counterpartyProperty());

        /* !transaction.isAbleToSave -> addButton.disabled  */
        addButton.disableProperty().bind(transaction.isAbleToSaveProperty().not());
    }
}
