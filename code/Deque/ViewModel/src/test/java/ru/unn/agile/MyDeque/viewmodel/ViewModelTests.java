package ru.unn.agile.MyDeque.viewmodel;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static ru.unn.agile.MyDeque.viewmodel.ViewModel.LogMess.*;
import static ru.unn.agile.MyDeque.viewmodel.ViewModel.Status.*;
import static ru.unn.agile.MyDeque.viewmodel.ViewModel.Operations.*;

import static org.junit.Assert.*;

public class ViewModelTests {

    private ViewModel viewmodel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewmodel = viewModel;
    }

    @Before
    public void setUp() {
        viewmodel = new ViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewmodel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewmodel.getValue());
        assertEquals(WAITING, viewmodel.getStatus());
        assertEquals("", viewmodel.getResult());
    }

    @Test
    public void isWaitingStatusInTheBeginning() {
        assertEquals(WAITING, viewmodel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewmodel.setValue("qwe");
        viewmodel.textFieldKey(KeyboardsKeys.ANY);

        assertEquals(BAD_FORMAT, viewmodel.getStatus());
    }

    @Test
    public void canCleanStatusIfAcceptIsOK() {
        viewmodel.setValue("qwe");
        viewmodel.textFieldKey(KeyboardsKeys.ANY);
        viewmodel.setValue("3");
        viewmodel.textFieldKey(KeyboardsKeys.ANY);

        assertEquals(READY, viewmodel.getStatus());
    }

    @Test
    public void isAcceptButtonDisabledInitially() {
        assertEquals(false, viewmodel.isAcceptButtonEnabled());
    }

    @Test
    public void isAcceptButtonDisabledWhenFormatIsBad() {
        inputField();
        viewmodel.setValue("trash");
        viewmodel.textFieldKey(KeyboardsKeys.ANY);

        assertEquals(false, viewmodel.isAcceptButtonEnabled());
    }

    private void inputField() {
        viewmodel.setValue("5");
    }

    @Test
    public void canGetOperationName() {
        String addName = PUSH_HEAD.toString();
        assertEquals("Push head", addName);
    }

    @Test
    public void canGetQuantityOfOperations() {
        int quantityOfOperations = values().length;
        assertEquals(4, quantityOfOperations);
    }

    @Test
    public void canCompareOperationsByName() {
        assertNotEquals(POP_HEAD, POP_TAIL);
    }

    @Test
    public void canSetPushHeadOperation() {
        viewmodel.setOperation(PUSH_HEAD);
        assertEquals(PUSH_HEAD, viewmodel.getOperation());
    }

    @Test
    public void canSetPushTailOperation() {
        viewmodel.setOperation(PUSH_TAIL);
        assertEquals(PUSH_TAIL, viewmodel.getOperation());
    }

    @Test
    public void canSetPopHeadOperation() {
        viewmodel.setOperation(POP_HEAD);
        assertEquals(POP_HEAD, viewmodel.getOperation());
    }

    @Test
    public void canSetPopTailOperation() {
        viewmodel.setOperation(POP_TAIL);
        assertEquals(POP_TAIL, viewmodel.getOperation());
    }

    @Test
    public void canSetSuccess() {
        inputField();

        viewmodel.accept();

        assertEquals(SUCCESS, viewmodel.getStatus());
    }

    @Test
    public void canPerformPushTailAction() {
        viewmodel.setValue("6");
        viewmodel.setOperation(PUSH_TAIL);

        viewmodel.accept();

        assertEquals("6", viewmodel.getResult());
    }

    @Test
    public void canPerformPushHeadAction() {
        viewmodel.setValue("7");
        viewmodel.setOperation(PUSH_HEAD);

        viewmodel.accept();

        assertEquals("7", viewmodel.getResult());
    }

    @Test
    public void canPerformPushToHeadAndPopFromHead() {
        viewmodel.setOperation(PUSH_HEAD);
        for (int i = 4; i < 6; i++) {
            viewmodel.setValue(Integer.toString(i));
            viewmodel.accept();
        }

        viewmodel.setOperation(POP_HEAD);

        viewmodel.accept();

        assertEquals("5", viewmodel.getResult());
    }

    @Test
    public void canPerformPushToHeadAndPopFromTail() {
        viewmodel.setOperation(PUSH_HEAD);
        for (int i = 7; i < 9; i++) {
            viewmodel.setValue(Integer.toString(i));
            viewmodel.accept();
        }

        viewmodel.setOperation(POP_TAIL);

        viewmodel.accept();

        assertEquals("7", viewmodel.getResult());
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception is not thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can not be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> logList = viewmodel.getLog();

        assertTrue(logList.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterAction() {
        setInputData();
        viewmodel.accept();
        String message = viewmodel.getLog().get(1);

        assertTrue(message.matches(".*" + ViewModel.LogMess.ACTION_WAS_STARTED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterAction() {
        setInputData();

        viewmodel.accept();

        String message = viewmodel.getLog().get(1);
        assertTrue(message.matches(".*" + viewmodel.getValue() + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() {
        setInputData();

        viewmodel.accept();

        String message = viewmodel.getLog().get(1);
        assertTrue(message.matches(".*; Value = " + viewmodel.getValue() + ".*"));
    }

    @Test
    public void operationTypeIsMentionedInTheLog() {
        setInputData();

        viewmodel.accept();

        String message = viewmodel.getLog().get(1);
        assertTrue(message.matches(".*Push head.*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();

        viewmodel.accept();
        viewmodel.accept();
        viewmodel.accept();

        assertEquals(4, viewmodel.getLog().size());
    }

    @Test
    public void canSeeOperationChangeInLog() throws IOException {
        setInputData();

        viewmodel.onOperationChanged(PUSH_HEAD, PUSH_TAIL);

        String message = viewmodel.getLog().get(1);
        assertTrue(message.matches(".*" + OPERATION_WAS_CHANGED + "Push tail.*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() throws IOException {
        viewmodel.onOperationChanged(PUSH_HEAD, PUSH_TAIL);

        viewmodel.onOperationChanged(PUSH_TAIL, PUSH_TAIL);

        assertEquals(1, viewmodel.getLog().size());
    }

    @Test
    public void actIsNotCalledWhenButtonIsDisabled() {
        viewmodel.accept();

        assertTrue(viewmodel.getLog().isEmpty());
    }

    @Test
    public void newValueNotEqualOldValue() {
        viewmodel.setValue("777");
        String message = viewmodel.getLog().get(viewmodel.getLog().size() - 1);
        assertTrue(message.matches(".*" + EDITING_FINISHED + "oldValue = .*"));
    }

    @Test
    public void setIncorrectOperation() {
        viewmodel.setValue("");
        assertTrue(viewmodel.getLog().isEmpty());
    }

    private void setInputData() {
        viewmodel.setValue("4");
        viewmodel.setOperation(PUSH_HEAD);
    }
}
