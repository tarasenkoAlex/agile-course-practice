package ru.unn.agile.todoapp.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import ru.unn.agile.todoapp.model.Task;

import java.time.LocalDate;

public class TaskListCellViewModel {
    private final Task task;
    private BooleanProperty doneCheckboxChecked = new SimpleBooleanProperty(false);

    public TaskListCellViewModel(Task task) {
        this.task = task;
    }

    public void clickIsDoneCheckBox() {
        this.doneCheckboxChecked.set(true);
    }

    public BooleanProperty doneCheckboxCheckedProperty() {
        return doneCheckboxChecked;
    }

    public String getDescription() {
        return task.getDescription();
    }

    public LocalDate getDueDate() {
        return task.getDueDate();
    }
}
