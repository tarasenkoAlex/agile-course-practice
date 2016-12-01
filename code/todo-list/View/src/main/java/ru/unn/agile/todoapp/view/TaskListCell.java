package ru.unn.agile.todoapp.view;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import ru.unn.agile.todoapp.viewmodel.TaskListCellViewModel;
import ru.unn.agile.todoapp.viewmodel.TodoAppViewModel;

import java.io.IOException;

public class TaskListCell extends ListCell<TaskListCellViewModel> {
    private static final PseudoClass DONE_TASK = PseudoClass.getPseudoClass("task-done");
    private final TodoAppViewModel masterViewModel;
    private TaskListCellViewModel viewModel;
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
    private Button deleteTaskButton;

    public TaskListCell(final TodoAppViewModel masterViewModel) {
        this.masterViewModel = masterViewModel;
    }

    @Override
    protected void updateItem(final TaskListCellViewModel viewModel, final boolean empty) {
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
            dueDateLabel.setText(viewModel.getDueDateString());
            taskIsDoneCheckbox.setSelected(viewModel.doneCheckboxCheckedProperty().get());
            taskIsDoneCheckbox.setDisable(viewModel.doneCheckboxDisableProperty().get());

            if (viewModel.doneCheckboxCheckedProperty().get()) {
                taskDescriptionLabel.pseudoClassStateChanged(DONE_TASK, true);
                dueDateLabel.pseudoClassStateChanged(DONE_TASK, true);
            }

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

    private void replaceViewModel(final TaskListCellViewModel viewModel) {
        this.viewModel = viewModel;
        taskIsDoneCheckbox.setOnAction(event -> this.viewModel.clickIsDoneCheckBox());
        deleteTaskButton.setOnAction(event -> this.masterViewModel.pressDeleteButton(viewModel));
    }
}
