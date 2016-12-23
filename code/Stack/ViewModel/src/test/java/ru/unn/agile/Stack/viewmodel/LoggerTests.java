package ru.unn.agile.Stack.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class LoggerTests {
    private ViewModel viewModel;

    public ILogger createLogger() {
        return new FakeLogger();
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel();
        ILogger logger = createLogger();
        viewModel.setLogger(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLoggerThrowsExceptionWithNullLogger() {
        viewModel.setLogger(null);
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        String log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void canLogPush() {
        viewModel.txtinputProperty().set("123");

        viewModel.push();

        String message = viewModel.getLog();
        assertTrue(message.matches(".*" + LogMessages.PUSHED + ".*\n"));
    }

    @Test
    public void logContainsPushedNumber() {
        viewModel.txtinputProperty().set("123");

        viewModel.push();

        String message = viewModel.getLog();
        assertTrue(message.matches(".*" + viewModel.txtinputProperty().get() + ".*\n"));
    }

    @Test
    public void canLogPop() {
        viewModel.txtinputProperty().set("123");

        viewModel.push();
        viewModel.pop();

        String message = viewModel.getLog();
        assertTrue(message.matches(".*\n.*" + LogMessages.POP + ".*\n"));
    }

    @Test
    public void logContainsPopNumber() {
        viewModel.txtinputProperty().set("123");

        viewModel.push();
        viewModel.pop();

        String message = viewModel.getLog();
        assertTrue(message.matches(".*\n.*" + viewModel.txtinputProperty().get() + ".*\n"));
    }

    @Test
    public void canLogTop() {
        viewModel.txtinputProperty().set("123");

        viewModel.push();
        viewModel.top();

        String message = viewModel.getLog();
        assertTrue(message.matches(".*\n.*" + LogMessages.TOP + ".*\n"));
    }

    @Test
    public void logContainsTopNumber() {
        viewModel.txtinputProperty().set("123");

        viewModel.push();
        viewModel.top();

        String message = viewModel.getLog();
        assertTrue(message.matches(".*\n.*" + viewModel.txtinputProperty().get() + ".*\n"));
    }

    @Test
    public void canLogIsEmpty() {
        viewModel.isEmpty();

        String message = viewModel.getLog();
        assertTrue(message.matches(".*" + LogMessages.IS_EMPTY + ".*\n"));
    }

    @Test
    public void canLogIsEmptyWhenIsNot() {
        viewModel.txtinputProperty().set("123");
        viewModel.push();

        viewModel.isEmpty();

        String message = viewModel.getLog();
        assertTrue(message.matches(".*\n.*" + LogMessages.IS_NOT_EMPTY + ".*\n"));
    }

    @Test
    public void canLogThreePushes() {
        viewModel.txtinputProperty().set("0");

        viewModel.push();
        viewModel.push();
        viewModel.push();

        String messages = viewModel.getLog();
        assertTrue(messages.matches(".*\n.*\n.*\n"));
    }

    @Test
    public void canLogPrint() {
        viewModel.print();
        String messages = viewModel.getLog();
        assertTrue(messages.matches(".*" + LogMessages.PRINTED + ".*\n"));
    }

    @Test
    public void pushIsNotLoggedWhenBadInput() {
        viewModel.txtinputProperty().set("bad input");

        viewModel.push();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void noLogWhenPopEmpty() {
        viewModel.pop();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void noLogWhenTopEmpty() {
        viewModel.top();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void canGetLogProperty() {
        assertNotNull(viewModel.logProperty());
    }
}
