package ru.unn.agile.todoapp.viewmodel;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TaskListCellViewModelTest {
    @Test
    public void byDefaultDoneCheckboxIsUnchecked() {
        TaskListCellViewModel viewModel = new TaskListCellViewModel();

        assertFalse(viewModel.isDoneCheckboxChecked());
    }

    @Test
    public void whenDoneCheckboxIsClickedTaskIsMarkedAsDone() {
        TaskListCellViewModel viewModel = new TaskListCellViewModel();

        viewModel.clickIsDoneCheckBox();

        assertTrue(viewModel.isDoneCheckboxChecked());
    }
}
