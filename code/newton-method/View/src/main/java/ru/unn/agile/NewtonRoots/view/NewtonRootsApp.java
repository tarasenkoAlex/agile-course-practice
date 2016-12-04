package ru.unn.agile.NewtonRoots.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.unn.agile.NewtonRoots.viewmodel.NewtonRootAppViewModel;

public class NewtonRootsApp  {
    @FXML
    private NewtonRootAppViewModel viewModel;
    @FXML
    private TextField leftPointText;
    @FXML
    private TextField rightPointText;
    @FXML
    private TextField maxIterationsText;
    @FXML
    private TextField accuracyText;
    @FXML
    private TextField functionText;
    @FXML
    private TextArea reportTextField;
    @FXML
    private Button findRootButton;

    @FXML
    private void initialize() {
        leftPointText.textProperty().bindBidirectional(viewModel.leftPointProperty());
        rightPointText.textProperty().bindBidirectional(viewModel.rightPointProperty());
        maxIterationsText.textProperty().bindBidirectional(viewModel.maxIterationsProperty());
        accuracyText.textProperty().bindBidirectional(viewModel.accuracyProperty());
        functionText.textProperty().bindBidirectional(viewModel.accuracyProperty());
        reportTextField.textProperty().bindBidirectional(viewModel.solverReportProperty());

        findRootButton.setOnAction(value -> viewModel.findRoot());
    }
}
