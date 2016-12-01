package ru.unn.agile.todoapp.viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.todoapp.model.Task;
import ru.unn.agile.todoapp.model.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoAppViewModel {
    private final TaskList tasks;
    private final StringProperty newTaskDescription;
    private final ObjectProperty<LocalDate> newTaskDueDate;
    private final BooleanProperty addNewTaskButtonDisable;
    private final ObservableList<TaskListCellViewModel> tasksViewModels;

    public TodoAppViewModel() {
        tasks = new TaskList();
        newTaskDescription = new SimpleStringProperty("");
        newTaskDueDate = new SimpleObjectProperty<>(LocalDate.now());
        addNewTaskButtonDisable = new SimpleBooleanProperty(true);
        tasksViewModels = FXCollections.observableArrayList(TaskListCellViewModel::extractor);

        newTaskDescription.addListener((observable, oldValue, newValue) ->
                updateAddNewTaskButtonStatus(newValue));
    }

    public ObjectProperty<LocalDate> newTaskDueDateProperty() {
        return newTaskDueDate;
    }

    public StringProperty newTaskDescriptionProperty() {
        return newTaskDescription;
    }

    public BooleanProperty addNewTaskButtonDisableProperty() {
        return addNewTaskButtonDisable;
    }

    public boolean getAddNewTaskButtonDisable() {
        return addNewTaskButtonDisable.get();
    }

    public final void updateAddNewTaskButtonStatus(final String newTaskDescription) {
        this.addNewTaskButtonDisable.setValue(newTaskDescription.isEmpty());
    }

    public void pressAddNewTaskButton() {
        tasks.add(new Task(this.newTaskDescription.getValue(),
                this.newTaskDueDate.getValue()));
        this.newTaskDescription.set("");
        this.newTaskDueDate.set(LocalDate.now());
        this.tasksViewModels.setAll(wrapTasksInListCellViewModels(tasks));
    }

    public ObservableList<TaskListCellViewModel> getTasksViewModels() {
        return tasksViewModels;
    }

    public void pressDeleteButton(TaskListCellViewModel taskListCellViewModel) {
        int deletionIndex = tasksViewModels.indexOf(taskListCellViewModel);
        if (deletionIndex != -1) {
            Task rawTask = tasksViewModels.get(deletionIndex).getTask();
            tasks.remove(rawTask);
            tasksViewModels.setAll(wrapTasksInListCellViewModels(tasks));
        }
    }

    private List<TaskListCellViewModel> wrapTasksInListCellViewModels(TaskList tasks) {
        List<TaskListCellViewModel> tasksViewModels = new ArrayList<>();
        for (Task rawTask : tasks.getAll()) {
            tasksViewModels.add(new TaskListCellViewModel(rawTask));
        }

        return tasksViewModels;
    }
}
