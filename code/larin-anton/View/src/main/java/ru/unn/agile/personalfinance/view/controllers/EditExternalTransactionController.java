package ru.unn.agile.personalfinance.view.controllers;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.CurrencyStringConverter;
import ru.unn.agile.PersonalFinance.Model.ExternalTransaction;
import ru.unn.agile.PersonalFinance.ViewModel.CategoryViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.ExternalTransactionViewModel;
import ru.unn.agile.personalfinance.view.ViewModelService;
import ru.unn.agile.personalfinance.view.WindowsManager;
import ru.unn.agile.personalfinance.view.controls.StringListCellFactory;
import ru.unn.agile.personalfinance.view.utils.Converters;

import java.net.URL;
import java.util.ResourceBundle;

public class EditExternalTransactionController extends DataContextController {
    private final static StringListCellFactory<CategoryViewModel> categoryListCellFactory =
            new StringListCellFactory<>(category -> category.getName());

    @FXML
    private JFXDatePicker datePicker;

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
        ((ExternalTransactionViewModel) getDataContext()).save();
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
    }

    @Override
    protected void removeBindings(final Object oldDataContext) {
        final ExternalTransactionViewModel transaction =
                (ExternalTransactionViewModel) oldDataContext;

        Bindings.unbindBidirectional(
                transactionAmountField.textProperty(),
                transaction.amountProperty());

        Bindings.unbindBidirectional(
                incomeRBtn.selectedProperty(),
                transaction.isIncomeProperty());

        Bindings.unbindBidirectional(
                descriptionTextArea.textProperty(),
                transaction.descriptionProperty());

        transaction.categoryProperty().unbind();

        Bindings.unbindBidirectional(
                counterpartyField.textProperty(),
                transaction.counterpartyProperty());

        addButton.disableProperty().unbind();
    }

    @Override
    protected void addBindings(final Object newDataContext) {
        final ExternalTransactionViewModel transaction =
                (ExternalTransactionViewModel) newDataContext;

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

        categoryComboBox.getSelectionModel().selectFirst();
    }
}
