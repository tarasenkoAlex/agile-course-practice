package ru.unn.agile.polynomial.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import ru.unn.agile.polynomial.viewmodel.ViewModel;
import ru.unn.agile.polynomial.viewmodel.ViewModel.Operation;

public class Calculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField firstOperandTextField;
    @FXML
    private TextField secondOperandTextField;
    @FXML
    private TextField resultTextField;
    @FXML
    private ChoiceBox<Operation> operationsChoiceBox;
    @FXML
    private Button calculationButton;
    @FXML
    private Label statusLabel;

    @FXML
    void initialize() {
        operationsChoiceBox.setItems(
            FXCollections.observableArrayList(Operation.values()));
        viewModel = new ViewModel();
        bind();

        calculationButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    viewModel.calculate();
                    backbind();
                    bind();
                }
            }
        );

        operationsChoiceBox.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    backbind();
                    bind();
                }
            }
        );

        ChangeListener<String> textFieldsListener = new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov,
                                final String oldStr, final String newStr) {
                backbind();
                bind();
            }
        };

        firstOperandTextField.textProperty().addListener(textFieldsListener);
        secondOperandTextField.textProperty().addListener(textFieldsListener);
    }

    private void bind() {
        calculationButton.setDisable(viewModel.isCalculationDisabled());
        resultTextField.setText(viewModel.getResultString());
        statusLabel.setText(viewModel.getStatusString());
        operationsChoiceBox.setValue(viewModel.getOperation());
    }

    private void backbind() {
        viewModel.setOperation(operationsChoiceBox.getValue());
        viewModel.setFirstOperandString(firstOperandTextField.getText());
        viewModel.setSecondOperandString(secondOperandTextField.getText());
    }
}
