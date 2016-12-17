package ru.unn.agile.MultisystemCalculator.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.MultisystemCalculator.infrastucture.TxtLogger;
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
        viewModel.setLogger(new TxtLogger("./TxtLogger-lab3.log"));
        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChanged(oldValue, newValue);
            }
        };
        firstArg.textProperty().bindBidirectional(viewModel.firstArgProperty());
        firstArg.focusedProperty().addListener(focusChangeListener);
        secondArg.textProperty().bindBidirectional(viewModel.secondArgProperty());
        secondArg.focusedProperty().addListener(focusChangeListener);
        cbOperations.valueProperty().bindBidirectional(viewModel.selectedOperationProperty());
        cbOperations.valueProperty().addListener(new ChangeListener<Operation>() {
            @Override
            public void changed(final ObservableValue<? extends Operation> observable,
                                final Operation oldValue,
                                final Operation newValue) {
                viewModel.onOperationChanged(oldValue, newValue);
            }
        });
        computeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
