package ru.unn.agile.polynomial.view;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ru.unn.agile.polynomial.infrastructure.TxtLogger;
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
    private ChoiceBox<Operation> operationsChoiceBox;
    @FXML
    private Button calculationButton;

    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./TxtLogger-lab3.log"));
        calculationButton.setOnAction(
                event -> viewModel.calculate()
        );

        final ChangeListener<Boolean> focusChangeListener = (observable, oldValue, newValue) ->
                viewModel.onFocusChanged(oldValue, newValue);

        firstOperandTextField.textProperty()
                .bindBidirectional(viewModel.firstOperandStringProperty());
        firstOperandTextField.focusedProperty().addListener(focusChangeListener);
        secondOperandTextField.textProperty()
                .bindBidirectional(viewModel.secondOperandStringProperty());
        secondOperandTextField.focusedProperty().addListener(focusChangeListener);
        operationsChoiceBox.valueProperty()
                .bindBidirectional(viewModel.operationProperty());
    }
}
