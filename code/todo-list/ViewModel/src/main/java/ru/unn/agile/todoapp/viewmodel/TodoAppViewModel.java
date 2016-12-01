package ru.unn.agile.todoapp.viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import ru.unn.agile.todoapp.model.Task;
import ru.unn.agile.todoapp.model.TaskList;

import java.time.LocalDate;
import java.util.List;

public class TodoAppViewModel {
    private final TaskList tasks;
    private final StringProperty newTaskDescription;
    private final ObjectProperty<LocalDate> newTaskDueDate;
    private final BooleanProperty addNewTaskButtonDisable;
    private final ObservableList<TaskListCellViewModel> tasksViewModels;
    private final SortedList<TaskListCellViewModel> sortedTasksViewModels;

    public TodoAppViewModel() {
        tasks = new TaskList();
        newTaskDescription = new SimpleStringProperty("");
        newTaskDueDate = new SimpleObjectProperty<>(LocalDate.now());
        addNewTaskButtonDisable = new SimpleBooleanProperty(true);
        tasksViewModels = FXCollections.observableArrayList(TaskListCellViewModel::extractor);
        sortedTasksViewModels = new SortedList<>(tasksViewModels,
                TaskListCellViewModel::comparator);

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

    public SortedList<TaskListCellViewModel> getSortedTasksViewModels() {
        return sortedTasksViewModels;
    }

    List<TaskListCellViewModel> getTasksViewModels() {
        return tasksViewModels;
    }

    public final void updateAddNewTaskButtonStatus(final String newTaskDescription) {
        this.addNewTaskButtonDisable.setValue(newTaskDescription.isEmpty());
    }

    public void pressAddNewTaskButton() {
        Task newTask = new Task(newTaskDescription.getValue(), newTaskDueDate.getValue());
        tasks.add(newTask);
        tasksViewModels.add(wrapTaskInListCellViewModel(newTask));

        newTaskDescription.set("");
        newTaskDueDate.set(LocalDate.now());
    }

    public void pressDeleteButton(final TaskListCellViewModel taskListCellViewModel) {
        int deletionIndex = tasksViewModels.indexOf(taskListCellViewModel);
        if (deletionIndex != -1) {
            Task rawTask = tasksViewModels.get(deletionIndex).getTask();
            tasks.remove(rawTask);
            tasksViewModels.remove(deletionIndex);
        }
    }

    private TaskListCellViewModel wrapTaskInListCellViewModel(final Task task) {
        return new TaskListCellViewModel(task);
    }
}
