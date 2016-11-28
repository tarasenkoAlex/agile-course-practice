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
    private final StringProperty newTaskDescription =
            new SimpleStringProperty("");
    private final ObjectProperty<LocalDate> newTaskDueDate =
            new SimpleObjectProperty<>(LocalDate.now());
    private final BooleanProperty addNewTaskButtonDisable =
            new SimpleBooleanProperty(true);
    private final TaskList tasks = new TaskList();
    private final ObservableList<TaskListCellViewModel> tasksViewModels =
            FXCollections.observableArrayList();

    public TodoAppViewModel() {
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
        this.tasksViewModels.setAll(this.getTasks());
    }

    public List<TaskListCellViewModel> getTasks() {
        List<Task> rawTasks = tasks.getAll();
        List<TaskListCellViewModel> taskViewModels = new ArrayList<>();
        for (Task rawTask : rawTasks) {
            taskViewModels.add(new TaskListCellViewModel(rawTask));
        }

        return taskViewModels;
    }

    public ObservableList<TaskListCellViewModel> getTasksViewModels() {
        return tasksViewModels;
    }
}
