package ru.unn.agile.Fraction.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.Fraction.model.Operation;
import ru.unn.agile.Fraction.viewmodel.ViewModel;

public class Calculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtFrac1;
    @FXML
    private TextField txtFrac2;
    @FXML
    private ComboBox<Operation> cbOperation;
    @FXML
    private Button btnCalc;

    @FXML
    void initialize() {
        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        txtFrac1.textProperty().bindBidirectional(viewModel.frac1Property());
        txtFrac2.textProperty().bindBidirectional(viewModel.frac2Property());
        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());

        btnCalc.setOnAction(event -> viewModel.calculate());
    }
}
