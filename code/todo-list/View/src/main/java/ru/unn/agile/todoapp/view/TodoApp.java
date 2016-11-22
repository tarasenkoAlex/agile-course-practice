package ru.unn.agile.todoapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ru.unn.agile.todoapp.viewmodel.TodoAppViewModel;

public class TodoApp {
    @FXML
    private TodoAppViewModel viewModel;
    @FXML
    private TextField taskDescriptionTextField;
    @FXML
    private DatePicker taskDueDatePicker;
    @FXML
    private Button addTaskButton;

    @FXML
    private void initialize() {
        taskDescriptionTextField.textProperty().bindBidirectional(
                viewModel.newTaskDescriptionProperty());
        taskDueDatePicker.valueProperty().bindBidirectional(viewModel.newTaskDueDateProperty());
    }
}
