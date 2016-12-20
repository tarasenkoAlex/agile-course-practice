package ru.unn.agile.vector3d.view;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ru.unn.agile.vector3d.infrastructure.FileLogger;
import ru.unn.agile.vector3d.viewmodel.ViewModel;

public class Controller {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TabPane opsTabPane;
    @FXML
    private TextField vectorTextField;
    @FXML
    private TextField dotProductOperandTextField;
    @FXML
    private TextField crossProductOperandTextField;
    @FXML
    private Button calculateButton;
    @FXML
    private ListView<String> logsList;

    @FXML
    void initialize() {
        viewModel.setLogger(new FileLogger("./vector3d.log"));
        calculateButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent event) {
                        viewModel.calculate();
                    }
                }
        );

        viewModel.activeTabIndexProperty()
            .bind(opsTabPane.getSelectionModel().selectedIndexProperty());
        viewModel.activeTabIndexProperty()
            .addListener((obs, oldValue, newValue) ->
                opsTabPane.getSelectionModel().clearAndSelect(newValue.intValue()));

        vectorTextField.textProperty()
            .bindBidirectional(viewModel.vectorTextProperty());
        dotProductOperandTextField.textProperty()
            .bindBidirectional(viewModel.dotProductOperandTextProperty());
        crossProductOperandTextField.textProperty()
            .bindBidirectional(viewModel.crossProductOperandTextProperty());
        logsList.setItems(viewModel.logsItems());
    }
}
