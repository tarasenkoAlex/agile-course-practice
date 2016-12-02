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
    private final ObservableList<TaskViewModel> tasksViewModels;
    private final SortedList<TaskViewModel> sortedTasksViewModels;

    public TodoAppViewModel() {
        tasks = new TaskList();
        newTaskDescription = new SimpleStringProperty("");
        newTaskDueDate = new SimpleObjectProperty<>(LocalDate.now());
        addNewTaskButtonDisable = new SimpleBooleanProperty(true);
        tasksViewModels = FXCollections.observableArrayList(TaskViewModel::extractor);
        sortedTasksViewModels = new SortedList<>(tasksViewModels,
                TaskViewModel::comparator);

        addNewTaskButtonDisable.bind(newTaskDescription.isEmpty());
    }

    private static TaskViewModel wrapTaskInListCellViewModel(final Task task) {
        return new TaskViewModel(task);
    }

    public ObjectProperty<LocalDate> newTaskDueDateProperty() {
        return newTaskDueDate;
    }

    public LocalDate getNewTaskDueDate() {
        return newTaskDueDate.get();
    }

    public void setNewTaskDueDate(final LocalDate dueDate) {
        newTaskDueDate.set(dueDate);
    }

    public StringProperty newTaskDescriptionProperty() {
        return newTaskDescription;
    }

    public String getNewTaskDescription() {
        return newTaskDescription.get();
    }

    public void setNewTaskDescription(final String description) {
        newTaskDescription.set(description);
    }

    public BooleanProperty addNewTaskButtonDisableProperty() {
        return addNewTaskButtonDisable;
    }

    public boolean getAddNewTaskButtonDisable() {
        return addNewTaskButtonDisable.get();
    }

    public SortedList<TaskViewModel> getSortedTasksViewModels() {
        return sortedTasksViewModels;
    }

    List<TaskViewModel> getTasksViewModels() {
        return tasksViewModels;
    }

    public void pressAddNewTaskButton() {
        Task newTask = new Task(newTaskDescription.getValue(), newTaskDueDate.getValue());
        tasks.add(newTask);
        tasksViewModels.add(wrapTaskInListCellViewModel(newTask));

        newTaskDescription.set("");
        newTaskDueDate.set(LocalDate.now());
    }

    public void pressDeleteButton(final TaskViewModel taskViewModel) {
        tasks.remove(taskViewModel.getTask());
        tasksViewModels.remove(taskViewModel);
    }
}
