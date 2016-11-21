package ru.unn.agile.todoapp.viewmodel;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TodoAppViewModelTest {
    private TodoAppViewModel viewModel;
    private final LocalDate TODAY = LocalDate.now();
    private final LocalDate DAY_AFTER_TOMORROW = TODAY.plusDays(2);
    private static final String NEW_TASK_DESCRIPTION = "Wash the car";

    @Before
    public void setUp() {
        viewModel = new TodoAppViewModel();
    }

    @Test
    public void byDefaultNewTaskDateIsToday() {
        assertEquals(TODAY, viewModel.getNewTaskDueDate());
    }

    @Test
    public void byDefaultNewTaskDescriptionIsEmpty() {
        assertEquals("", viewModel.getNewTaskDescription());
    }

    @Test
    public void byDefaultAddNewTaskButtonIsDisabled() {
        assertFalse(viewModel.isAddNewTaskButtonEnabled());
    }

    @Test
    public void whenDescriptionIsNonEmptyAddNewTaskButtonIsEnabled() {
        viewModel.setNewTaskDescription(NEW_TASK_DESCRIPTION);

        assertTrue(viewModel.isAddNewTaskButtonEnabled());
    }

    @Test
    public void whenDescriptionIsClearedAddNewTaskButtonIsDisabled() {
        viewModel.setNewTaskDescription(NEW_TASK_DESCRIPTION);

        viewModel.setNewTaskDescription("");

        assertFalse(viewModel.isAddNewTaskButtonEnabled());
    }

    @Test
    public void whenAddingNewTaskDescriptionFieldShouldClear() {
        viewModel.setNewTaskDescription(NEW_TASK_DESCRIPTION);

        viewModel.pressAddNewTaskButton();

        assertEquals("", viewModel.getNewTaskDescription());
    }

    @Test
    public void whenAddingNewTaskDueDateShouldJumpBackToToday() {
        viewModel.setNewTaskDescription(NEW_TASK_DESCRIPTION);
        viewModel.setDueDate(DAY_AFTER_TOMORROW);

        viewModel.pressAddNewTaskButton();

        assertEquals(TODAY, viewModel.getNewTaskDueDate());
    }
}
