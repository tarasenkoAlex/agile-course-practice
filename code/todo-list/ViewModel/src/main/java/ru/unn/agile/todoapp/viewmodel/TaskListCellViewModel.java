package ru.unn.agile.todoapp.viewmodel;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import ru.unn.agile.todoapp.model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskListCellViewModel {
    static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final Task task;
    private final BooleanProperty doneCheckboxChecked;
    private final BooleanProperty doneCheckboxDisable;

    public TaskListCellViewModel(final Task task) {
        this.task = task;
        this.doneCheckboxChecked = new SimpleBooleanProperty(task.isDone());
        this.doneCheckboxDisable = new SimpleBooleanProperty(task.isDone());
    }

    public static Observable[] extractor(final TaskListCellViewModel viewModel) {
        return new Observable[]{
                viewModel.doneCheckboxCheckedProperty(),
                viewModel.doneCheckboxDisableProperty()
        };
    }

    public static int comparator(final TaskListCellViewModel tvm1,
                                 final TaskListCellViewModel tvm2) {
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
    }

    public void clickIsDoneCheckBox() {
        this.task.markAsDone();
        this.doneCheckboxChecked.set(true);
        this.doneCheckboxDisable.set(true);
    }

    public BooleanProperty doneCheckboxCheckedProperty() {
        return doneCheckboxChecked;
    }

    public String getDescription() {
        return task.getDescription();
    }

    public String getDueDateString() {
        return task.getDueDate().format(DATE_FORMATTER);
    }

    public BooleanProperty doneCheckboxDisableProperty() {
        return doneCheckboxDisable;
    }

    public Task getTask() {
        return task;
    }
}
