package ru.unn.agile.MultisystemCalculator.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.MultisystemCalculator.viewmodel.CalculatorViewModel;
import ru.unn.agile.MultisystemCalculator.Model.Format;
import ru.unn.agile.MultisystemCalculator.viewmodel.Operation;

/**
 * Created by Дарья on 28.11.2016.
 */
public class CalculatorView {
    @FXML
    private CalculatorViewModel viewModel;
    @FXML
    private TextField firstArg;
    @FXML
    private TextField secondArg;
    @FXML
    private ComboBox<Operation> cbOperations;
    @FXML
    private ComboBox<Format> cbFormats;
    @FXML
    private Button computeBtn;

    @FXML
    void initialize() {

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        firstArg.textProperty().bindBidirectional(viewModel.firstArgProperty());
        secondArg.textProperty().bindBidirectional(viewModel.secondArgProperty());
        cbOperations.valueProperty().bindBidirectional(viewModel.selectedOperationProperty());
        cbFormats.valueProperty().bindBidirectional(viewModel.selectedFormatProperty());
        computeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
