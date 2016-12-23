package ru.unn.agile.Credit.viewmodel;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ViewModelLogTests {
    private ViewModel viewModel;
    private ILogger logger;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
        logger = new FakeLogger();
        viewModel.setLogger(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    void setInputData() {
        viewModel.sumProperty().set("10");
        viewModel.monthsProperty().set("5");
        viewModel.percentProperty().set("15");
    }


    @Test
    public void isLoggedWhenPressedClear() {
        setInputData();
        viewModel.clear();
        String message = logger.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.CLEAR_WAS_PRESSED + ".*"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void cannotSetNullLogger() {
        viewModel.setLogger(null);
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = logger.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterCalculation() {
        setInputData();
        viewModel.calculate();
        String message = logger.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setInputData();

        viewModel.calculate();

        String message = logger.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.sumProperty().get()
                + ".*" + viewModel.monthsProperty().get()
                + ".*" + viewModel.percentProperty().get() + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() {
        setInputData();

        viewModel.calculate();

        String message = logger.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": Sum = " + viewModel.sumProperty().get()
                + "; Months = " + viewModel.monthsProperty().get()
                + "; Percent = " + viewModel.percentProperty().get() + ".*"));
    }



    @Test
    public void canPutSeveralLogMessages() {
        setInputData();

        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(3, logger.getLog().size());
    }




    @Test
    public void argumentsAreCorrectlyLogged() {
        setInputData();

        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = logger.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.sumProperty().get() + "; "
                + viewModel.monthsProperty().get() + "; "
                + viewModel.percentProperty().get() + "\\]"));
    }

    @Test
    public void calculateIsNotCalledWhenButtonIsDisabled() {
        viewModel.calculate();
        assertTrue(logger.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.sumProperty().set("12");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.sumProperty().set("12");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, logger.getLog().size());
    }

    public void setViewModelWithLogger(final ViewModel viewModel, final ILogger realLogger) {
        this.viewModel = viewModel;
        this.logger = realLogger;
    }

    @Test
    public void canGetLogs() {
        assertNotNull(viewModel.getLogs());
    }

    @Test
    public void canLogsProperty() {
        assertNotNull(viewModel.logsProperty());
    }

    @Test
    public void canFocusChanged() {
        viewModel.onFocusChanged(false, true);
    }
}
