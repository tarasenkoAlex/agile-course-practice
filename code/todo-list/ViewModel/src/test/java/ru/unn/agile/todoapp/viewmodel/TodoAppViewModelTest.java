package ru.unn.agile.todoapp.viewmodel;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TodoAppViewModelTest {
    private TodoAppViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new TodoAppViewModel();
    }

    @Test
    public void byDefaultNewTaskDateIsToday() {
        assertEquals(LocalDate.now(), viewModel.getNewTaskDate());
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
        viewModel.setNewTaskDescription("Wash the car");

        assertTrue(viewModel.isAddNewTaskButtonEnabled());
    }

    @Test
    public void whenDescriptionIsClearedAddNewTaskButtonIsDisabled() {
        viewModel.setNewTaskDescription("Wash the car");
        viewModel.setNewTaskDescription("");

        assertFalse(viewModel.isAddNewTaskButtonEnabled());
    }
}
