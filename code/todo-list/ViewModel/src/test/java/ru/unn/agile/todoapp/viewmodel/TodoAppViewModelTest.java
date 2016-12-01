package ru.unn.agile.todoapp.viewmodel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TodoAppViewModelTest {
    private TodoAppViewModel viewModel;
    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate TOMORROW = TODAY.plusDays(1);
    private static final LocalDate DAY_AFTER_TOMORROW = TODAY.plusDays(2);
    private static final String DAY_AFTER_TOMORROW_STRING = DAY_AFTER_TOMORROW.format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private static final String NEW_TASK_DESCRIPTION = "Wash the car";
    private static final String ANOTHER_TASK_DESCRIPTION = "Water the plants";

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
        addTask(viewModel, NEW_TASK_DESCRIPTION, DAY_AFTER_TOMORROW);

        assertEquals("", viewModel.newTaskDescriptionProperty().get());
    }

    @Test
    public void whenAddingNewTaskDueDateShouldJumpBackToToday() {
        addTask(viewModel, NEW_TASK_DESCRIPTION, DAY_AFTER_TOMORROW);

        assertEquals(TODAY, viewModel.newTaskDueDateProperty().get());
    }

    @Test
    public void whenAddingNewTaskItAppearsInTheList() {
        addTask(viewModel, NEW_TASK_DESCRIPTION, DAY_AFTER_TOMORROW);
        TaskListCellViewModel lastTask = viewModel.getSortedTasksViewModels().get(0);

        assertEquals(NEW_TASK_DESCRIPTION, lastTask.getDescription());
        assertEquals(DAY_AFTER_TOMORROW_STRING, lastTask.getDueDateString());
        assertFalse(lastTask.doneCheckboxCheckedProperty().get());
    }

    @Test
    public void whenDeleteButtonIsPressedTaskIsDeletedFromTheList() {
        addTask(viewModel, NEW_TASK_DESCRIPTION, DAY_AFTER_TOMORROW);

        TaskListCellViewModel deletedTask = viewModel.getSortedTasksViewModels().get(0);
        viewModel.pressDeleteButton(deletedTask);

        assertTrue(viewModel.getSortedTasksViewModels().isEmpty());
    }

    @Test
    public void undoneTasksAreSortedByDueDate() {
        addTask(viewModel, NEW_TASK_DESCRIPTION, DAY_AFTER_TOMORROW);
        addTask(viewModel, ANOTHER_TASK_DESCRIPTION, TOMORROW);

        assertEquals(ANOTHER_TASK_DESCRIPTION,
                viewModel.getSortedTasksViewModels().get(0).getDescription());
        assertEquals(NEW_TASK_DESCRIPTION,
                viewModel.getSortedTasksViewModels().get(1).getDescription());
    }

    private void addTask(TodoAppViewModel viewModel, String description, LocalDate dueDate) {
        viewModel.newTaskDescriptionProperty().set(description);
        viewModel.newTaskDueDateProperty().set(dueDate);
        viewModel.pressAddNewTaskButton();
    }
}
