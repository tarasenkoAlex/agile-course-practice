package ru.unn.agile.Fraction.viewmodel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Fraction.model.Operation;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class FractionViewModelTests {
    private FractionViewModel fractionViewModel;

    public void setFractionViewModel(final FractionViewModel fractionViewModel) {
        this.fractionViewModel = fractionViewModel;
    }

    @Before
    public void setUp() throws IOException {
        if (fractionViewModel == null) {
            fractionViewModel = new FractionViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        fractionViewModel = null;
    }

    @Test
    public void canCreateDefaultViewModel() {
        assertNotNull(new FractionViewModel());
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", fractionViewModel.frac1Property().get());
        assertEquals("", fractionViewModel.frac2Property().get());
        assertEquals(Operation.ADD, fractionViewModel.operationProperty().get());
        assertEquals("", fractionViewModel.resultProperty().get());
        assertEquals(Status.WAITING.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        Assert.assertEquals(Status.WAITING.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() throws IOException {
        fractionViewModel.calculate();
        assertEquals(Status.WAITING.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        fractionViewModel.frac1Property().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        fractionViewModel.frac1Property().set("1");

        assertEquals(Status.WAITING.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(fractionViewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonGetDisabledInitially() {
        assertTrue(fractionViewModel.getCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        fractionViewModel.frac1Property().set("trash");

        assertTrue(fractionViewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonGetDisabledWhenFormatIsBad() {
        setInputData();
        fractionViewModel.frac1Property().set("trash");

        assertTrue(fractionViewModel.getCalculationDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        fractionViewModel.frac1Property().set("1");

        assertTrue(fractionViewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonGetDisabledWithIncompleteInput() {
        fractionViewModel.frac1Property().set("1");

        assertTrue(fractionViewModel.getCalculationDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(fractionViewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonGetEnabledWithCorrectInput() {
        setInputData();

        assertFalse(fractionViewModel.getCalculationDisabled());
    }

    @Test
    public void checkAddGetOperationsProperty() {
        assertEquals(Operation.values()[0], fractionViewModel.operationsProperty().get().get(0));
    }

    @Test
    public void checkMulGetOperationsProperty() {
        assertEquals(Operation.values()[1], fractionViewModel.operationsProperty().get().get(1));
    }

    @Test
    public void checkSubGetOperationsProperty() {
        assertEquals(Operation.values()[2], fractionViewModel.operationsProperty().get().get(2));
    }

    @Test
    public void checkDivgetOperationsProperty() {
        assertEquals(Operation.values()[3], fractionViewModel.operationsProperty().get().get(3));
    }

    @Test
    public void checkAddGetOperations() {
        assertEquals(Operation.values()[0], fractionViewModel.getOperations().get(0));
    }

    @Test
    public void checkMulGetOperations() {
        assertEquals(Operation.values()[1], fractionViewModel.getOperations().get(1));
    }

    @Test
    public void checkSubGetOperations() {
        assertEquals(Operation.values()[2], fractionViewModel.getOperations().get(2));
    }

    @Test
    public void checkDivGetOperations() {
        assertEquals(Operation.values()[3], fractionViewModel.getOperations().get(3));
    }

    @Test
    public void canSetAddOperation() {
        fractionViewModel.operationProperty().set(Operation.ADD);
        assertEquals(Operation.ADD, fractionViewModel.operationProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() throws IOException {
        fractionViewModel.frac1Property().set("1");
        fractionViewModel.frac2Property().set("4");

        fractionViewModel.calculate();

        assertEquals("5", fractionViewModel.resultProperty().get());
    }

    @Test
    public void operationAddGetCorrectResult() throws IOException {
        fractionViewModel.frac1Property().set("1");
        fractionViewModel.frac2Property().set("4");

        fractionViewModel.calculate();

        assertEquals("5", fractionViewModel.getResult());
    }

    @Test
    public void canSetSuccessMessage() throws IOException {
        setInputData();

        fractionViewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        fractionViewModel.frac1Property().set("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void operationMulHasCorrectResult() throws IOException {
        fractionViewModel.frac1Property().set("2");
        fractionViewModel.frac2Property().set("3");
        fractionViewModel.operationProperty().set(Operation.MULTIPLY);

        fractionViewModel.calculate();

        assertEquals("6", fractionViewModel.resultProperty().get());
    }

    @Test
    public void operationMulGetCorrectResult() throws IOException {
        fractionViewModel.frac1Property().set("2");
        fractionViewModel.frac2Property().set("3");
        fractionViewModel.operationProperty().set(Operation.MULTIPLY);

        fractionViewModel.calculate();

        assertEquals("6", fractionViewModel.getResult());
    }

    @Test
    public void operationAddWithNegativeNumbersHasCorrectResult() throws IOException {
        fractionViewModel.frac1Property().set("2");
        fractionViewModel.frac2Property().set("-3");
        fractionViewModel.operationProperty().set(Operation.ADD);

        fractionViewModel.calculate();

        assertEquals("-1", fractionViewModel.resultProperty().get());
    }

    @Test
    public void operationAddWithNegativeNumbersGetCorrectResult() throws IOException {
        fractionViewModel.frac1Property().set("2");
        fractionViewModel.frac2Property().set("-3");
        fractionViewModel.operationProperty().set(Operation.ADD);

        fractionViewModel.calculate();

        assertEquals("-1", fractionViewModel.getResult());
    }

    @Test (expected = IllegalArgumentException.class)
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        fractionViewModel = new FractionViewModel(null);
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        FractionViewModel fractionViewModelLogged = new FractionViewModel(logger);

        assertNotNull(fractionViewModelLogged);
    }

    @Test
    public void logIsEmptyInTheBeginning() throws IOException {
        List<String> log = fractionViewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterCalculation() throws IOException {
        setInputData();
        fractionViewModel.calculate();
        String message = fractionViewModel.getLog().get(0);

        assertTrue(message.matches(".*" + Messages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() throws IOException {
        setInputData();

        fractionViewModel.calculate();

        String message = fractionViewModel.getLog().get(0);
        assertTrue(message.matches(".*" + fractionViewModel.frac1Property().get()
                + ".*" + fractionViewModel.frac2Property().get() + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() throws IOException {
        setInputData();

        fractionViewModel.calculate();

        String message = fractionViewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": Frac1 = " + fractionViewModel.frac1Property().get()
                + "; Frac2 = " + fractionViewModel.frac2Property().get() + ".*"));
    }

    @Test
    public void operationTypeIsMentionedInTheLog() throws IOException {
        setInputData();

        fractionViewModel.calculate();

        String message = fractionViewModel.getLog().get(0);
        assertTrue(message.matches(".*Add.*"));
    }

    @Test
    public void canPutSeveralLogMessages() throws IOException {
        setInputData();

        fractionViewModel.calculate();
        fractionViewModel.calculate();
        fractionViewModel.calculate();

        assertEquals(3, fractionViewModel.getLog().size());
    }

    @Test
    public void canSeeOperationChangeInLog() throws IOException {
        setInputData();

        fractionViewModel.onOperationChanged(Operation.ADD, Operation.MULTIPLY);

        String message = fractionViewModel.getLog().get(0);
        assertTrue(message.matches(".*" + Messages.OPERATION_WAS_CHANGED + "Mul.*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() throws IOException {
        fractionViewModel.onOperationChanged(Operation.ADD, Operation.MULTIPLY);

        fractionViewModel.onOperationChanged(Operation.MULTIPLY, Operation.MULTIPLY);

        assertEquals(1, fractionViewModel.getLog().size());
    }

    @Test
    public void argumentsAreCorrectlyLogged() throws IOException {
        setInputData();

        fractionViewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = fractionViewModel.getLog().get(0);
        assertTrue(message.matches(".*" + Messages.EDITING_FINISHED
                + "Input arguments are: \\["
                + fractionViewModel.frac1Property().get() + "; "
                + fractionViewModel.frac2Property().get() + "\\]"));
    }

    @Test
    public void calculateIsNotCalledWhenButtonIsDisabled() throws IOException {
        fractionViewModel.calculate();

        assertTrue(fractionViewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() throws IOException {
        fractionViewModel.frac1Property().set("12");
        fractionViewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        fractionViewModel.frac1Property().set("12");
        fractionViewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, fractionViewModel.getLog().size());
    }

    @Test
    public void canLogWhenFocusWasNotChangedWithPartialInput() throws IOException {
        fractionViewModel.frac1Property().set("12");
        fractionViewModel.onFocusChanged(Boolean.FALSE, Boolean.FALSE);

        assertEquals(1, fractionViewModel.getLog().size());
    }

    @Test
    public void cannotLogWithPartialInput() throws IOException {
        fractionViewModel.frac1Property().set("12");
        fractionViewModel.onFocusChanged(Boolean.FALSE, Boolean.TRUE);

        assertEquals(0, fractionViewModel.getLog().size());
    }

    @Test
    public void canGetLogsProperty() throws IOException {
        fractionViewModel.operationProperty().set(Operation.SUBTRACTION);
        fractionViewModel.onOperationChanged(Operation.ADD, Operation.SUBTRACTION);
        assertTrue(fractionViewModel.logsProperty().get().contains("Operation was changed to "));
    }

    @Test
    public void canGetLogs() throws IOException {
        fractionViewModel.operationProperty().set(Operation.SUBTRACTION);
        fractionViewModel.onOperationChanged(Operation.ADD, Operation.SUBTRACTION);
        assertTrue(fractionViewModel.getLogs().contains("Operation was changed to "));
    }

    @Test
    public void statusIsBadWhenZeroDenominatorFullFilled() throws IOException {
        fractionViewModel.frac1Property().set("1/0");
        fractionViewModel.frac2Property().set("1");
        fractionViewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void canGetBadStatusWhenZeroDenominatorFullFilled() throws IOException {
        fractionViewModel.frac1Property().set("1/0");
        fractionViewModel.frac2Property().set("1");
        fractionViewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), fractionViewModel.getStatus());
    }

    @Test
    public void statusIsBadWhenZeroDenominatorPartFilled() throws IOException {
        fractionViewModel.frac1Property().set("1/0");
        fractionViewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void canGetBadStatusWhenZeroDenominatorPartFilled() throws IOException {
        fractionViewModel.frac1Property().set("1/0");
        fractionViewModel.calculate();

        assertEquals(Status.DIV_ZERO.toString(), fractionViewModel.getStatus());
    }

    @Test
    public void getBadStatusWhenDivByZero() throws IOException {
        fractionViewModel.frac1Property().set("1/2");
        fractionViewModel.frac2Property().set("0");
        fractionViewModel.operationProperty().set(Operation.DIVISION);
        fractionViewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), fractionViewModel.statusProperty().get());
    }

    @Test
    public void canGetBadStatusWhenDivByZero() throws IOException {
        fractionViewModel.frac1Property().set("3/2");
        fractionViewModel.frac2Property().set("0");
        fractionViewModel.operationProperty().set(Operation.DIVISION);

        fractionViewModel.calculate();

        assertEquals(Status.DIV_ZERO.toString(), fractionViewModel.getStatus());
    }

    @Test
    public void canGetLogCalculateWasPressedMessages() throws IOException {
        assertEquals("Calculate. ", Messages.CALCULATE_WAS_PRESSED);
    }

    @Test
    public void canGetLogOperationWasChangedMessages() throws IOException {
        assertEquals("Operation was changed to ", Messages.OPERATION_WAS_CHANGED);
    }

    @Test
    public void canGetLogEditingFinishedMessages() {
        assertEquals("Updated input. ", Messages.EDITING_FINISHED);
    }

    private void setInputData() {
        fractionViewModel.frac1Property().set("1");
        fractionViewModel.frac2Property().set("2");
    }
}
