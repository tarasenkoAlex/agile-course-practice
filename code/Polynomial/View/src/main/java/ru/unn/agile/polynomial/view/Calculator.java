package ru.unn.agile.polynomial.view;

import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        calculationButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    viewModel.calculate();
                }
            }
        );

        firstOperandTextField.textProperty()
            .bindBidirectional(viewModel.firstOperandStringProperty());
        secondOperandTextField.textProperty()
            .bindBidirectional(viewModel.secondOperandStringProperty());
        operationsChoiceBox.valueProperty()
            .bindBidirectional(viewModel.operationProperty());
    }
}
