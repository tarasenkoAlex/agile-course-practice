package ru.unn.agile.Stack.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class LoggerTests {
    private ViewModel viewModel;
    private FakeLogger logger;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
        logger = new FakeLogger();
        viewModel.setLogger(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
        logger = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLoggerThrowsExceptionWithNullLogger() {
        viewModel.setLogger(null);
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        String log = logger.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void canLogPush() {
        viewModel.txtinputProperty().set("123");

        viewModel.push();

        String message = logger.getLog();
        assertTrue(message.matches(".*" + LogMessages.PUSHED + ".*\n"));
    }

    @Test
    public void logContainsPushedNumber() {
        viewModel.txtinputProperty().set("123");

        viewModel.push();

        String message = logger.getLog();
        assertTrue(message.matches(".*" + viewModel.txtinputProperty().get() + ".*\n"));
    }

    @Test
    public void canLogThreePushes() {
        viewModel.txtinputProperty().set("0");

        viewModel.push();
        viewModel.push();
        viewModel.push();

        String messages = logger.getLog();
        assertTrue(messages.matches(".*\n.*\n.*\n"));
    }

    @Test
    public void pushIsNotLoggedWhenBadInput() {
        viewModel.txtinputProperty().set("bad input");

        viewModel.push();

        assertTrue(logger.getLog().isEmpty());
    }

}
