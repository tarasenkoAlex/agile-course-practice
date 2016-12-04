package ru.unn.agile.NewtonRoots.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class NewtonRootAppViewModelTests  {
    private NewtonRootAppViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new NewtonRootAppViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void checkDefaultState() {
        assertEquals("", viewModel.leftPointProperty().get());
        assertEquals("", viewModel.rightPointProperty().get());
        assertEquals("", viewModel.maxIterationsProperty().get());
        assertEquals("", viewModel.accuracyProperty().get());
        assertEquals("", viewModel.functionProperty().get());
        assertEquals(true, viewModel.findRootButtonDisableProperty.get());
        assertEquals("", viewModel.solverReportProperty.get());
    }

}
