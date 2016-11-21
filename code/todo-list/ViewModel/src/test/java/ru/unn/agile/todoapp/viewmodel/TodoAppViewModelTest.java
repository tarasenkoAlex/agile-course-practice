package ru.unn.agile.todoapp.viewmodel;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals(false, viewModel.isAddNewTaskButtonEnabled());
    }
}
