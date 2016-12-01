package ru.unn.agile.ComplexNumberCalculator.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.ComplexNumberCalculator.viewmodel.ComplexNumberCalculatorViewModel;
import ru.unn.agile.ComplexNumberCalculator.viewmodel.Operations;

/**
 * Created by Alexander on 28.11.2016.
 */
public class ComplexNumberCalculatorView {
    @FXML
    private ComplexNumberCalculatorViewModel viewModel;
    @FXML
    private TextField firstArgReal;
    @FXML
    private TextField firstArgIm;
    @FXML
    private TextField secondArgReal;
    @FXML
    private TextField secondArgIm;
    @FXML
    private ComboBox<Operations> cbOperations;

    @FXML
    private Button computeBtn;

    @FXML
    void initialize() {

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        firstArgReal.textProperty().bindBidirectional(viewModel.firstArgRealProperty());
        firstArgIm.textProperty().bindBidirectional(viewModel.firstArgImProperty());
        secondArgReal.textProperty().bindBidirectional(viewModel.secondArgRealProperty());
        secondArgIm.textProperty().bindBidirectional(viewModel.secondArgImProperty());
        cbOperations.valueProperty().bindBidirectional(viewModel.selectedOperationProperty());
        computeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
