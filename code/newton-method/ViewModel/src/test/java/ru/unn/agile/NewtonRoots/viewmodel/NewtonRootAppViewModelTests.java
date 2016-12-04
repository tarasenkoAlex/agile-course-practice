package ru.unn.agile.NewtonRoots.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewtonRootAppViewModelTests {
    private NewtonRootAppViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new NewtonRootAppViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    private void setValidViewModelInputState() {
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
    public void setBadFormattedAccuracy() {
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

    @Test
    public void setBadFormattedFunction() {
        setValidViewModelInputState();
        viewModel.setFunction("a");
        assertEquals(InputStatus.BAD_FORMAT.toString(), viewModel.inputStatusProperty().get());
    }

    @Test
    public void setNonMonotonicFiction() {
        setValidViewModelInputState();
        viewModel.setFunction("x^2");
        assertEquals(InputStatus.NON_MONOTONIC_FUNCTION.toString(),
                viewModel.inputStatusProperty().get());
    }

    @Test
    public void setBadInterval() {
        setValidViewModelInputState();
        viewModel.setLeftPoint("-1");
        viewModel.setRightPoint("-2");
        assertEquals(InputStatus.BAD_PARAMETERS.toString(),
                viewModel.inputStatusProperty().get());
    }

    @Test
    public void setBadAccuracy() {
        setValidViewModelInputState();
        viewModel.setAccuracy("0");
        assertEquals(InputStatus.BAD_PARAMETERS.toString(),
                viewModel.inputStatusProperty().get());
    }

    @Test
    public void waitingStatusWhenOneFieldIsEmpty() {
        setValidViewModelInputState();
        viewModel.setAccuracy("");
        assertEquals(InputStatus.WAITING.toString(),
                viewModel.inputStatusProperty().get());
    }

    @Test
    public void readyStatusWhenParametersIsOK() {
        setValidViewModelInputState();
        assertEquals(InputStatus.READY.toString(),
                viewModel.inputStatusProperty().get());
    }

    @Test
    public void successStatusWhenRootFound() {
        setValidViewModelInputState();
        viewModel.findRoot();
        assertEquals(InputStatus.SUCCESS.toString(),
                viewModel.inputStatusProperty().get());
    }

    @Test
    public void failedStatusWhenRootNotFound() {
        setValidViewModelInputState();
        viewModel.setFunction("x+100");
        viewModel.findRoot();
        assertEquals(InputStatus.FAILED.toString(),
                viewModel.inputStatusProperty().get());
    }
}
