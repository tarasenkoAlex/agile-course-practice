package ru.unn.agile.SolvingQuadraticEquation.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.SolvingQuadraticEquation.viewmodel.ViewModel;

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

        txtAcoef.textProperty().bindBidirectional(viewModel.aCoefProperty());
        txtBcoef.textProperty().bindBidirectional(viewModel.bCoefProperty());
        txtCcoef.textProperty().bindBidirectional(viewModel.cCoefProperty());

        btnSolve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.solve();
            }
        });
    }
}

