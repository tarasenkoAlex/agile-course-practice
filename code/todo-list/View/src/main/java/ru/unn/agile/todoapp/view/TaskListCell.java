package ru.unn.agile.todoapp.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import ru.unn.agile.todoapp.viewmodel.TaskListCellViewModel;

import java.io.IOException;

public class TaskListCell extends ListCell<TaskListCellViewModel> {
    private FXMLLoader fxmlLoader;
    @FXML
    private Label taskDescriptionLabel;
    @FXML
    private CheckBox taskIsDoneCheckbox;
    @FXML
    private BorderPane pane;

    @Override
    protected void updateItem(TaskListCellViewModel viewModel, boolean empty) {
        super.updateItem(viewModel, empty);

        if (empty || viewModel == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                tryLoadFxml();
            }

            taskDescriptionLabel.setText(viewModel.getDescription());
            taskIsDoneCheckbox.setSelected(viewModel.doneCheckboxCheckedProperty().getValue());

            setText(null);
            setGraphic(pane);
        }
    }

    private void tryLoadFxml() {
        fxmlLoader = new FXMLLoader(getClass().getResource("TaskListCell.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
