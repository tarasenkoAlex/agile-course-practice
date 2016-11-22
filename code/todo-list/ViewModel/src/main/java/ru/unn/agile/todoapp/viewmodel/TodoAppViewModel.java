package ru.unn.agile.todoapp.viewmodel;

import javafx.beans.property.*;
import ru.unn.agile.todoapp.model.Task;
import ru.unn.agile.todoapp.model.TaskList;

import java.time.LocalDate;
import java.util.List;

public class TodoAppViewModel {
    private ObjectProperty<LocalDate> newTaskDueDate = new SimpleObjectProperty<>(LocalDate.now());
    private StringProperty newTaskDescription = new SimpleStringProperty("");
    private BooleanProperty addNewTaskButtonDisable = new SimpleBooleanProperty(true);
    private TaskList tasks = new TaskList();

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

    public void updateAddNewTaskButtonStatus(String newTaskDescription) {
        this.addNewTaskButtonDisable.setValue(newTaskDescription.isEmpty());
    }

    public void pressAddNewTaskButton() {
        tasks.add(new Task(this.newTaskDescription.getValue(),
                this.newTaskDueDate.getValue()));
        this.newTaskDescription.set("");
        this.newTaskDueDate.set(LocalDate.now());
    }

    public List<Task> getTasks() {
        return tasks.getAll();
    }
}
