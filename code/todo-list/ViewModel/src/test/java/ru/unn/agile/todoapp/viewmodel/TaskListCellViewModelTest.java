package ru.unn.agile.todoapp.viewmodel;

import org.junit.Test;
import ru.unn.agile.todoapp.model.Task;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TaskListCellViewModelTest {
    @Test
    public void byDefaultDoneCheckboxIsUnchecked() {
        Task task = new Task("Wash the car", LocalDate.now());
        TaskListCellViewModel viewModel = new TaskListCellViewModel(task);

        assertFalse(viewModel.doneCheckboxCheckedProperty().get());
    }

    @Test
    public void whenDoneCheckboxIsClickedTaskIsMarkedAsDone() {
        Task task = new Task("Wash the car", LocalDate.now());
        TaskListCellViewModel viewModel = new TaskListCellViewModel(task);

        viewModel.clickIsDoneCheckBox();

        assertTrue(viewModel.doneCheckboxCheckedProperty().get());
    }
}
