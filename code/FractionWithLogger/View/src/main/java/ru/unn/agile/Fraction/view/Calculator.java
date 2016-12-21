package ru.unn.agile.Fraction.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.Fraction.infrastructure.FractionLogger;
import ru.unn.agile.Fraction.model.Operation;
import ru.unn.agile.Fraction.viewmodel.ViewModel;

import java.io.IOException;

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
    void initialize() throws IOException {
        viewModel.setLogger(new FractionLogger("./FractionLogger.log"));

        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed (final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                try {
                viewModel.onFocusChanged(oldValue, newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        txtFrac1.textProperty().bindBidirectional(viewModel.frac1Property());
        txtFrac1.focusedProperty().addListener(focusChangeListener);

        txtFrac2.textProperty().bindBidirectional(viewModel.frac2Property());
        txtFrac2.focusedProperty().addListener(focusChangeListener);

        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());
        cbOperation.valueProperty().addListener(new ChangeListener<Operation>() {
            @Override
            public void changed(ObservableValue<? extends Operation> observable,
                                final Operation oldValue,
                                final Operation newValue) {
                try {
                    viewModel.onOperationChanged(oldValue, newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (final ActionEvent event) {
                try {
                viewModel.calculate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
