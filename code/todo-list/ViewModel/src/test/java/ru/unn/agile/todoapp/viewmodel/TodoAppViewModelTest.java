package ru.unn.agile.todoapp.viewmodel;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.todoapp.model.Task;

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
        assertEquals(TODAY, viewModel.newTaskDueDateProperty().getValue());
    }

    @Test
    public void byDefaultNewTaskDescriptionIsEmpty() {
        assertEquals("", viewModel.newTaskDescriptionProperty().getValue());
    }

    @Test
    public void byDefaultAddNewTaskButtonIsDisabled() {
        assertTrue(viewModel.addNewTaskButtonDisableProperty().get());
    }

    @Test
    public void whenDescriptionIsNonEmptyAddNewTaskButtonIsEnabled() {
        viewModel.newTaskDescriptionProperty().set(NEW_TASK_DESCRIPTION);

        assertFalse(viewModel.addNewTaskButtonDisableProperty().get());
    }

    @Test
    public void whenDescriptionIsClearedAddNewTaskButtonIsDisabled() {
        viewModel.newTaskDescriptionProperty().set(NEW_TASK_DESCRIPTION);

        viewModel.newTaskDescriptionProperty().set("");

        assertTrue(viewModel.addNewTaskButtonDisableProperty().get());
    }

    @Test
    public void whenAddingNewTaskDescriptionFieldShouldClear() {
        viewModel.newTaskDescriptionProperty().set(NEW_TASK_DESCRIPTION);

        viewModel.pressAddNewTaskButton();

        assertEquals("", viewModel.newTaskDescriptionProperty().get());
    }

    @Test
    public void whenAddingNewTaskDueDateShouldJumpBackToToday() {
        viewModel.newTaskDescriptionProperty().set(NEW_TASK_DESCRIPTION);
        viewModel.newTaskDueDateProperty().set(DAY_AFTER_TOMORROW);

        viewModel.pressAddNewTaskButton();

        assertEquals(TODAY, viewModel.newTaskDueDateProperty().get());
    }

    @Test
    public void whenAddingNewTaskItAppearsInTheList() {
        viewModel.newTaskDescriptionProperty().set(NEW_TASK_DESCRIPTION);
        viewModel.newTaskDueDateProperty().set(DAY_AFTER_TOMORROW);

        viewModel.pressAddNewTaskButton();
        Task lastTask = viewModel.getTasks().get(0);

        assertEquals(NEW_TASK_DESCRIPTION, lastTask.getDescription());
        assertEquals(DAY_AFTER_TOMORROW, lastTask.getDueDate());
        assertFalse(lastTask.isDone());
    }
}
