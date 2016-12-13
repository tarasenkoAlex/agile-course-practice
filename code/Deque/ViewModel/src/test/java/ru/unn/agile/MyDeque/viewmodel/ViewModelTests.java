package ru.unn.agile.MyDeque.viewmodel;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import ru.unn.agile.MyDeque.viewmodel.ViewModel.Status;

import static org.junit.Assert.*;

public class ViewModelTests {

    private ViewModel viewmodel;

    @Before
    public void setUp() {
        viewmodel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewmodel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewmodel.getValue());
        assertEquals(Status.WAITING, viewmodel.getStatus());
        assertEquals("", viewmodel.getResult());
    }

    @Test
    public void isWaitingStatusInTheBeginning() {
        assertEquals(Status.WAITING, viewmodel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewmodel.setValue("qwe");
        viewmodel.textFieldKey(KeyboardsKeys.ANY);

        assertEquals(Status.BAD_FORMAT, viewmodel.getStatus());
    }

    @Test
    public void canCleanStatusIfAcceptIsOK() {
        viewmodel.setValue("qwe");
        viewmodel.textFieldKey(KeyboardsKeys.ANY);
        viewmodel.setValue("3");
        viewmodel.textFieldKey(KeyboardsKeys.ANY);

        assertEquals(Status.READY, viewmodel.getStatus());
    }

    @Test
    public void isAcceptButtonDisabledInitially() {
        assertEquals(false, viewmodel.isAcceptButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        inputField();
        viewmodel.textFieldKey(KeyboardsKeys.ANY);
        assertEquals(true, viewmodel.isAcceptButtonEnabled());

        viewmodel.setValue("trash");
        viewmodel.textFieldKey(KeyboardsKeys.ANY);

        assertEquals(false, viewmodel.isAcceptButtonEnabled());
    }

    private void inputField() {
        viewmodel.setValue("5");
    }

    @Test
    public void canGetOperationName() {
        String addName = ViewModel.Operations.PUSH_HEAD.toString();
        assertEquals("Push head", addName);
    }

    @Test
    public void canGetQuantityOfOperations() {
        int quantityOfOperations = ViewModel.Operations.values().length;
        assertEquals(4, quantityOfOperations);
    }

    @Test
    public void canCreateListingOfOperations() {
        ViewModel.Operations[] operations = ViewModel.Operations.values();
        ViewModel.Operations[] currentOperations = new ViewModel.Operations[]{
                ViewModel.Operations.PUSH_HEAD,
                ViewModel.Operations.POP_HEAD,
                ViewModel.Operations.PUSH_TAIL,
                ViewModel.Operations.POP_TAIL};

        assertArrayEquals(currentOperations, operations);
    }

    @Test
    public void canCompareOperationsByName() {
        assertEquals(ViewModel.Operations.PUSH_HEAD, ViewModel.Operations.PUSH_HEAD);
        assertNotEquals(ViewModel.Operations.POP_HEAD, ViewModel.Operations.POP_TAIL);
    }

    @Test
    public void canSetPushHeadOperation() {
        viewmodel.setOperation(ViewModel.Operations.PUSH_HEAD);
        assertEquals(ViewModel.Operations.PUSH_HEAD, viewmodel.getOperation());
    }

    @Test
    public void canSetPushTailOperation() {
        viewmodel.setOperation(ViewModel.Operations.PUSH_TAIL);
        assertEquals(ViewModel.Operations.PUSH_TAIL, viewmodel.getOperation());
    }

    @Test
    public void canSetPopHeadOperation() {
        viewmodel.setOperation(ViewModel.Operations.POP_HEAD);
        assertEquals(ViewModel.Operations.POP_HEAD, viewmodel.getOperation());
    }

    @Test
    public void canSetPopTailOperation() {
        viewmodel.setOperation(ViewModel.Operations.POP_TAIL);
        assertEquals(ViewModel.Operations.POP_TAIL, viewmodel.getOperation());
    }

    @Test
    public void canSetSuccess() {
        inputField();

        viewmodel.accept();

        assertEquals(Status.SUCCESS, viewmodel.getStatus());
    }

    @Test
    public void canPerformPushTailAction() {
        viewmodel.setValue("6");
        viewmodel.setOperation(ViewModel.Operations.PUSH_TAIL);

        viewmodel.accept();

        assertEquals("6", viewmodel.getResult());
    }

    @Test
    public void canPerformPushHeadAction() {
        viewmodel.setValue("7");
        viewmodel.setOperation(ViewModel.Operations.PUSH_HEAD);

        viewmodel.accept();

        assertEquals("7", viewmodel.getResult());
    }

    @Test
    public void canPerformPushToHeadAndPopFromHead() {
        viewmodel.setOperation(ViewModel.Operations.PUSH_HEAD);
        for (int i = 4; i < 6; i++) {
            viewmodel.setValue(Integer.toString(i));
            viewmodel.accept();
        }

        viewmodel.setOperation(ViewModel.Operations.POP_HEAD);

        viewmodel.accept();

        assertEquals("5", viewmodel.getResult());
    }

    public void canPerformPushToHeadAndPopFromTail() {
        viewmodel.setOperation(ViewModel.Operations.PUSH_HEAD);
        for (int i = 7; i < 9; i++) {
            viewmodel.setValue(Integer.toString(i));
            viewmodel.accept();
        }

        viewmodel.setOperation(ViewModel.Operations.POP_TAIL);

        viewmodel.accept();

        assertEquals("7", viewmodel.getResult());
    }

}
