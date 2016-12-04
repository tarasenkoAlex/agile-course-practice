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

    private void setValidViewModelInputState()  {
        viewModel.setLeftPoint("-1");
        viewModel.setRightPoint("1");
        viewModel.setAccuracy("0.001");
        viewModel.setFunction("x");
        viewModel.setMaxIterations("10");
    }

    @Test
    public void checkDefaultState() {
        assertEquals("", viewModel.leftPointProperty().get());
        assertEquals("", viewModel.rightPointProperty().get());
        assertEquals("", viewModel.maxIterationsProperty().get());
        assertEquals("", viewModel.accuracyProperty().get());
        assertEquals("", viewModel.functionProperty().get());
        assertEquals(true, viewModel.findRootButtonDisableProperty().get());
        assertEquals("", viewModel.solverReportProperty().get());
    }

    @Test
    public void setBadFormattedIntervalBounds() {
        setValidViewModelInputState();
        viewModel.setRightPoint("-a");
        viewModel.setLeftPoint("b");
        assertEquals(InputStatus.BAD_FORMAT.toString(), viewModel.inputStatusProperty().get());
    }

    @Test
    public void setBadAccuracy() {
        setValidViewModelInputState();
        viewModel.setAccuracy("-a");
        assertEquals(InputStatus.BAD_FORMAT.toString(), viewModel.inputStatusProperty().get());
    }

    @Test
    public void setBadMaxIterations() {
        setValidViewModelInputState();
        viewModel.setMaxIterations("-a");
        assertEquals(InputStatus.BAD_FORMAT.toString(), viewModel.inputStatusProperty().get());
    }
}
