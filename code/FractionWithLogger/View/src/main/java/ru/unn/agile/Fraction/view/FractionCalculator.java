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
import ru.unn.agile.Fraction.viewmodel.FractionViewModel;

import java.io.IOException;

public class FractionCalculator {
    @FXML
    private FractionViewModel fractionViewModel;
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
        fractionViewModel.setLogger(new FractionLogger("./FractionLogger.log"));

        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                try {
                fractionViewModel.onFocusChanged(oldValue, newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        txtFrac1.textProperty().bindBidirectional(fractionViewModel.frac1Property());
        txtFrac1.focusedProperty().addListener(focusChangeListener);

        txtFrac2.textProperty().bindBidirectional(fractionViewModel.frac2Property());
        txtFrac2.focusedProperty().addListener(focusChangeListener);

        cbOperation.valueProperty().bindBidirectional(fractionViewModel.operationProperty());
        cbOperation.valueProperty().addListener(new ChangeListener<Operation>() {
            @Override
            public void changed(final ObservableValue<? extends Operation> observable,
                                final Operation oldValue,
                                final Operation newValue) {
                try {
                    fractionViewModel.onOperationChanged(oldValue, newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                try {
                fractionViewModel.calculate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
