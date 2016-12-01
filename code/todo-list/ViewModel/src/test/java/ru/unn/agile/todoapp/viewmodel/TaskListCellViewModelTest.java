package ru.unn.agile.todoapp.viewmodel;

import org.junit.Test;
import ru.unn.agile.todoapp.model.Task;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TaskListCellViewModelTest {
    private final Task task = new Task("Wash the car", LocalDate.now());
    private final TaskListCellViewModel viewModel = new TaskListCellViewModel(task);

    @Test
    public void byDefaultDoneCheckboxIsUnchecked() {
        assertFalse(viewModel.doneCheckboxCheckedProperty().get());
    }

    @Test
    public void whenDoneCheckboxIsClickedTaskIsMarkedAsDone() {
        viewModel.clickIsDoneCheckBox();

        assertTrue(viewModel.doneCheckboxCheckedProperty().get());
    }

    @Test
    public void whenDoneCheckboxIsClickedItIsDisabled() {
        viewModel.clickIsDoneCheckBox();

        assertTrue(viewModel.doneCheckboxDisableProperty().get());
    }
}
