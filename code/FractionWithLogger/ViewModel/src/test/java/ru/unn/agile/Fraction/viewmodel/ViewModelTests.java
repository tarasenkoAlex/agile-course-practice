package ru.unn.agile.Fraction.viewmodel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Fraction.model.Fraction;
import ru.unn.agile.Fraction.model.Operation;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() throws IOException {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canCreateDefaultViewModel()
    {
        assertNotNull(new ViewModel());
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.frac1Property().get());
        assertEquals("", viewModel.frac2Property().get());
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        Assert.assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() throws IOException {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.frac1Property().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.frac1Property().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonGetDisabledInitially() {
        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.frac1Property().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonGetDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.frac1Property().set("trash");

        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.frac1Property().set("1");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonGetDisabledWithIncompleteInput() {
        viewModel.frac1Property().set("1");

        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonGetEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.getCalculationDisabled());
    }

    @Test
    public void checkAddGetOperationsProperty() {
        assertEquals(Operation.values()[0], viewModel.operationsProperty().get().get(0));
    }

    @Test
    public void checkMulGetOperationsProperty() {
        assertEquals(Operation.values()[1], viewModel.operationsProperty().get().get(1));
    }

    @Test
    public void checkSubGetOperationsProperty() {
        assertEquals(Operation.values()[2], viewModel.operationsProperty().get().get(2));
    }

    @Test
    public void checkDivgetOperationsProperty() {
        assertEquals(Operation.values()[3], viewModel.operationsProperty().get().get(3));
    }

    @Test
    public void checkAddGetOperations() {
        assertEquals(Operation.values()[0], viewModel.getOperations().get(0));
    }

    @Test
    public void checkMulGetOperations() {
        assertEquals(Operation.values()[1], viewModel.getOperations().get(1));
    }

    @Test
    public void checkSubGetOperations() {
        assertEquals(Operation.values()[2], viewModel.getOperations().get(2));
    }

    @Test
    public void checkDivGetOperations() {
        assertEquals(Operation.values()[3], viewModel.getOperations().get(3));
    }

    @Test
    public void canSetAddOperation() {
        viewModel.operationProperty().set(Operation.ADD);
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() throws IOException {
        viewModel.frac1Property().set("1");
        viewModel.frac2Property().set("4");

        viewModel.calculate();

        assertEquals("5", viewModel.resultProperty().get());
    }

    @Test
    public void operationAddGetCorrectResult() throws IOException {
        viewModel.frac1Property().set("1");
        viewModel.frac2Property().set("4");

        viewModel.calculate();

        assertEquals("5", viewModel.getResult());
    }

    @Test
    public void canSetSuccessMessage() throws IOException {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.frac1Property().set("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void operationMulHasCorrectResult() throws IOException {
        viewModel.frac1Property().set("2");
        viewModel.frac2Property().set("3");
        viewModel.operationProperty().set(Operation.MULTIPLY);

        viewModel.calculate();

        assertEquals("6", viewModel.resultProperty().get());
    }

    @Test
    public void operationMulGetCorrectResult() throws IOException {
        viewModel.frac1Property().set("2");
        viewModel.frac2Property().set("3");
        viewModel.operationProperty().set(Operation.MULTIPLY);

        viewModel.calculate();

        assertEquals("6", viewModel.getResult());
    }

    @Test
    public void operationAddWithNegativeNumbersHasCorrectResult() throws IOException {
        viewModel.frac1Property().set("2");
        viewModel.frac2Property().set("-3");
        viewModel.operationProperty().set(Operation.ADD);

        viewModel.calculate();

        assertEquals("-1", viewModel.resultProperty().get());
    }

    @Test
    public void operationAddWithNegativeNumbersGetCorrectResult() throws IOException {
        viewModel.frac1Property().set("2");
        viewModel.frac2Property().set("-3");
        viewModel.operationProperty().set(Operation.ADD);

        viewModel.calculate();

        assertEquals("-1", viewModel.getResult());
    }

    @Test (expected = IllegalArgumentException.class)
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        viewModel = new ViewModel(null);
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void logIsEmptyInTheBeginning() throws IOException {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterCalculation() throws IOException {
        setInputData();
        viewModel.calculate();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() throws IOException {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.frac1Property().get()
                + ".*" + viewModel.frac2Property().get() + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() throws IOException {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": Frac1 = " + viewModel.frac1Property().get()
                + "; Frac2 = " + viewModel.frac2Property().get() + ".*"));
    }

    @Test
    public void operationTypeIsMentionedInTheLog() throws IOException {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Add.*"));
    }

    @Test
    public void canPutSeveralLogMessages() throws IOException {
        setInputData();

        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canSeeOperationChangeInLog() throws IOException {
        setInputData();

        viewModel.onOperationChanged(Operation.ADD, Operation.MULTIPLY);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.OPERATION_WAS_CHANGED + "Mul.*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() throws IOException {
        viewModel.onOperationChanged(Operation.ADD, Operation.MULTIPLY);

        viewModel.onOperationChanged(Operation.MULTIPLY, Operation.MULTIPLY);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void argumentsAreCorrectlyLogged() throws IOException {
        setInputData();

        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.frac1Property().get() + "; "
                + viewModel.frac2Property().get() + "\\]"));
    }

    @Test
    public void calculateIsNotCalledWhenButtonIsDisabled() throws IOException {
        viewModel.calculate();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() throws IOException {
        viewModel.frac1Property().set("12");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.frac1Property().set("12");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void canLogWhenFocusWasNotChangedWithPartialInput() throws IOException {
        viewModel.frac1Property().set("12");
        viewModel.onFocusChanged(Boolean.FALSE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void cannotLogWithPartialInput() throws IOException {
        viewModel.frac1Property().set("12");
        viewModel.onFocusChanged(Boolean.FALSE, Boolean.TRUE);

        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void canGetLogsProperty() throws IOException {
        viewModel.operationProperty().set(Operation.SUBTRACTION);
        viewModel.onOperationChanged(Operation.ADD, Operation.SUBTRACTION);
        assertTrue(viewModel.logsProperty().get().contains("Operation was changed to "));
    }

    @Test
    public void canGetLogs() throws IOException {
        viewModel.operationProperty().set(Operation.SUBTRACTION);
        viewModel.onOperationChanged(Operation.ADD, Operation.SUBTRACTION);
        assertTrue(viewModel.getLogs().contains("Operation was changed to "));
    }

    @Test
    public void statusIsBadWhenZeroDenominatorFullFilled() throws IOException {
        viewModel.frac1Property().set("1/0");
        viewModel.frac2Property().set("1");
        viewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canGetBadStatusWhenZeroDenominatorFullFilled() throws IOException {
        viewModel.frac1Property().set("1/0");
        viewModel.frac2Property().set("1");
        viewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsBadWhenZeroDenominatorPartFilled() throws IOException {
        viewModel.frac1Property().set("1/0");
        viewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canGetBadStatusWhenZeroDenominatorPartFilled() throws IOException {
        viewModel.frac1Property().set("1/0");
        viewModel.calculate();

        assertEquals(Status.DIV_ZERO.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsBadWhenDivideByZero() throws IOException {
        viewModel.frac1Property().set("1/2");
        viewModel.frac2Property().set("0");
        viewModel.operationProperty().set(Operation.DIVISION);
        viewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canGetBadStatusWhenDivideByZero() throws IOException {
        viewModel.frac1Property().set("1/2");
        viewModel.frac2Property().set("0");
        viewModel.operationProperty().set(Operation.DIVISION);

        viewModel.calculate();

        assertEquals(Status.DIV_ZERO.toString(), viewModel.getStatus());
    }

    @Test
    public void canGetLogCalculateWasPressedMessages() throws IOException {
        assertEquals("Calculate. ", LogMessages.CALCULATE_WAS_PRESSED);
    }

    @Test
    public void canGetLogOperationWasChangedMessages() throws IOException {
        assertEquals("Operation was changed to ", LogMessages.OPERATION_WAS_CHANGED);
    }

    @Test
    public void canGetLogEditingFinishedMessages() {
        assertEquals("Updated input. ", LogMessages.EDITING_FINISHED);
    }

    @Test
    public void canCreateLogMessages() {
        assertNotNull(new LogMessages());
    }

    private void setInputData() {
        viewModel.frac1Property().set("1");
        viewModel.frac2Property().set("2");
    }
}
