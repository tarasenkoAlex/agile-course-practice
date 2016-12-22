package ru.unn.agile.SolvingQuadraticEquation.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.SolvingQuadraticEquation.viewmodel.ViewModel;
import unn.agile.SolvingQuadraticEquation.infrastructure.TxtLogger;

public class Solver {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtAcoef;
    @FXML
    private TextField txtBcoef;
    @FXML
    private TextField txtCcoef;
    @FXML
    private Button btnSolve;

    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./TxtLogger-lab3.log"));

        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChanged(oldValue, newValue);
            }
        };

        txtAcoef.textProperty().bindBidirectional(viewModel.aCoefProperty());
        txtAcoef.focusedProperty().addListener(focusChangeListener);
        txtBcoef.textProperty().bindBidirectional(viewModel.bCoefProperty());
        txtBcoef.focusedProperty().addListener(focusChangeListener);
        txtCcoef.textProperty().bindBidirectional(viewModel.cCoefProperty());
        txtCcoef.focusedProperty().addListener(focusChangeListener);
        btnSolve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.solve();
            }
        });
    }
}

