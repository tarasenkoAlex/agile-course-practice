package ru.unn.agile.todoapp.viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import ru.unn.agile.todoapp.model.Task;
import ru.unn.agile.todoapp.model.TaskList;

import java.time.LocalDate;

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
        sortedTasksViewModels = new SortedList<>(
                tasksViewModels,
                (tvm1, tvm2) -> {
                    LocalDate dueDate1 = tvm1.getTask().getDueDate();
                    LocalDate dueDate2 = tvm2.getTask().getDueDate();
                    boolean taskDone1 = tvm1.getTask().isDone();
                    boolean taskDone2 = tvm2.getTask().isDone();
                    int dueDateComparison = dueDate1.compareTo(dueDate2);

                    if (taskDone1 && taskDone2) {
                        return -dueDateComparison;
                    } else if (taskDone1 && !taskDone2) {
                        return 1;
                    } else if (!taskDone1 && taskDone2) {
                        return -1;
                    } else {
                        return dueDateComparison;
                    }
        });

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
        Task newTask = new Task(newTaskDescription.getValue(), newTaskDueDate.getValue());
        tasks.add(newTask);
        tasksViewModels.add(wrapTaskInListCellViewModel(newTask));

        newTaskDescription.set("");
        newTaskDueDate.set(LocalDate.now());
    }

    public SortedList<TaskListCellViewModel> getSortedTasksViewModels() {
        return sortedTasksViewModels;
    }

    public void pressDeleteButton(TaskListCellViewModel taskListCellViewModel) {
        int deletionIndex = tasksViewModels.indexOf(taskListCellViewModel);
        if (deletionIndex != -1) {
            Task rawTask = tasksViewModels.get(deletionIndex).getTask();
            tasks.remove(rawTask);
            tasksViewModels.remove(deletionIndex);
        }
    }

    private TaskListCellViewModel wrapTaskInListCellViewModel(Task task) {
        return new TaskListCellViewModel(task);
    }
}
