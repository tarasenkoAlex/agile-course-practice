package ru.unn.agile.todoapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import ru.unn.agile.todoapp.viewmodel.TaskListCellViewModel;
import ru.unn.agile.todoapp.viewmodel.TodoAppViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private ListView<TaskListCellViewModel> taskListView;

    @FXML
    private void initialize() {
        taskDueDatePicker.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate date) {
                return date.format(dateFormatter);
            }

            @Override
            public LocalDate fromString(String dateString) {
                return LocalDate.parse(dateString, dateFormatter);
            }
        });

        taskDescriptionTextField.textProperty().bindBidirectional(
                viewModel.newTaskDescriptionProperty());
        taskDueDatePicker.valueProperty().bindBidirectional(viewModel.newTaskDueDateProperty());
        addTaskButton.setOnAction(value -> viewModel.pressAddNewTaskButton());

        taskListView.setItems(viewModel.getSortedTasksViewModels());
        taskListView.setCellFactory(taskListView -> new TaskListCell(viewModel));
    }
}
