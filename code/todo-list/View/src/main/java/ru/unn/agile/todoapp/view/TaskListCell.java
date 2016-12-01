package ru.unn.agile.todoapp.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import ru.unn.agile.todoapp.viewmodel.TaskListCellViewModel;

import java.io.IOException;

public class TaskListCell extends ListCell<TaskListCellViewModel> {
    private FXMLLoader fxmlLoader;
    @FXML
    private Label taskDescriptionLabel;
    @FXML
    private Label dueDateLabel;
    @FXML
    private CheckBox taskIsDoneCheckbox;
    @FXML
    private GridPane pane;
    @FXML
    private TaskListCellViewModel viewModel;

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

            if (this.viewModel != viewModel) {
                replaceViewModel(viewModel);
            }

            taskDescriptionLabel.setText(viewModel.getDescription());
            dueDateLabel.setText(viewModel.getDueDate().toString());
            taskIsDoneCheckbox.setSelected(viewModel.doneCheckboxCheckedProperty().get());
            taskIsDoneCheckbox.setDisable(viewModel.doneCheckboxDisableProperty().get());

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

    private void replaceViewModel(TaskListCellViewModel viewModel) {
        this.viewModel = viewModel;
        taskIsDoneCheckbox.setOnAction(event -> this.viewModel.clickIsDoneCheckBox());
    }
}
