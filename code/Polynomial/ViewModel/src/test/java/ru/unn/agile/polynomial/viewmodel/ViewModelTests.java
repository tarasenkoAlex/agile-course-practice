package ru.unn.agile.polynomial.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import ru.unn.agile.polynomial.viewmodel.ViewModel.Operation;
import ru.unn.agile.polynomial.viewmodel.ViewModel.Status;
import ru.unn.agile.polynomial.viewmodel.ViewModel.LogMessages;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private String correctFirstPolynomialOperandString = "5.3*x^9 + 0.5*x^3 + 55*x";
    private String correctSecondPolynomialOperandString = "x";
    private String correctSecondExponentOperandString = "3";

    private String correctFirstPolynomialOperandRegex = "5.3\\*x\\^9 \\+ 0.5\\*x\\^3 \\+ 55\\*x";
    private String correctSecondPolynomialOperandRegex = "x";

    protected void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private void setInputData() {
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString(correctSecondPolynomialOperandString);
        viewModel.setOperation(Operation.ADD);
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void areDefaultsCorrect() {
        assertEquals("", viewModel.getFirstOperandString());
        assertEquals("", viewModel.getSecondOperandString());
        assertEquals("", viewModel.getResultString());
        assertEquals(Operation.ADD, viewModel.getOperation());
        assertEquals(Status.WAIT.toString(), viewModel.getStatusString());
    }

    @Test
    public void canSetOperation() {
        viewModel.setOperation(Operation.MULTIPLY);
        assertEquals(Operation.MULTIPLY, viewModel.getOperation());
    }

    @Test
    public void canSetFirstOperandString() {
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        assertEquals(correctFirstPolynomialOperandString, viewModel.getFirstOperandString());
    }

    @Test
    public void canSetSecondOperandString() {
        viewModel.setSecondOperandString(correctSecondPolynomialOperandString);
        assertEquals(correctSecondPolynomialOperandString, viewModel.getSecondOperandString());
    }

    @Test
    public void isCalculationDisabledWhenOperandsDefault() {
        assertEquals(true, viewModel.isCalculationDisabled());
    }

    @Test
    public void isCalculationDisabledWhenIncorrectBothOperands() {
        viewModel.setFirstOperandString("incorrect input one");
        viewModel.setSecondOperandString("incorrect input two");
        assertEquals(true, viewModel.isCalculationDisabled());
    }

    @Test
    public void isCalculationDisabledWhenCorrectFirstAndIncorrectSecondOperands() {
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString("incorrect input");
        assertEquals(true, viewModel.isCalculationDisabled());
    }

    @Test
    public void isCalculationDisabledWhenIncorrectFirstAndCorrectSecondOperands() {
        viewModel.setFirstOperandString("incorrect input");
        viewModel.setSecondOperandString(correctSecondPolynomialOperandString);
        assertEquals(true, viewModel.isCalculationDisabled());
    }

    @Test
    public void isCalculationEnabledWhenCorrectBothOperands() {
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString(correctSecondPolynomialOperandString);
        assertEquals(false, viewModel.isCalculationDisabled());
    }

    @Test
    public void isCalculationDisabledWhenExponentiateOperationAndCorrectPolynomialSecondOperand() {
        viewModel.setOperation(Operation.EXPONENTIATE);
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString(correctSecondPolynomialOperandString);
        assertEquals(true, viewModel.isCalculationDisabled());
    }

    @Test
    public void isCalculationDisabledWhenExponentiateOperationAndNegativeExponent() {
        viewModel.setOperation(Operation.EXPONENTIATE);
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString("-2");
        assertEquals(true, viewModel.isCalculationDisabled());
    }

    @Test
    public void isCalculationEnabledWhenExponentiateOperationAndCorrectOperands() {
        viewModel.setOperation(Operation.EXPONENTIATE);
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString(correctSecondExponentOperandString);
        assertEquals(false, viewModel.isCalculationDisabled());
    }

    @Test
    public void isStatusBadInputWhenInputIncorrect() {
        viewModel.setFirstOperandString("incorrect input one");
        viewModel.setSecondOperandString("incorrect input two");
        assertEquals(Status.BAD.toString(), viewModel.getStatusString());
    }

    @Test
    public void isStatusWaitWhenAllOperandsEmpty() {
        viewModel.setFirstOperandString("");
        viewModel.setSecondOperandString("");
        assertEquals(Status.WAIT.toString(), viewModel.getStatusString());
    }

    @Test
    public void isStatusBadWhenFirstOperandEmptySecondIncorrect() {
        viewModel.setFirstOperandString("");
        viewModel.setSecondOperandString("incorrect input");
        assertEquals(Status.BAD.toString(), viewModel.getStatusString());
    }

    @Test
    public void isStatusBadWhenFirstOperandEmptySecondIncorrectForExponentiation() {
        viewModel.setOperation(Operation.EXPONENTIATE);
        viewModel.setFirstOperandString("");
        viewModel.setSecondOperandString("incorrect input");
        assertEquals(Status.BAD.toString(), viewModel.getStatusString());
    }

    @Test
    public void isStatusWaitWhenFirstOperandEmptySecondCorrect() {
        viewModel.setFirstOperandString("");
        viewModel.setSecondOperandString(correctSecondPolynomialOperandString);
        assertEquals(Status.WAIT.toString(), viewModel.getStatusString());
    }

    @Test
    public void isStatusBadWhenSecondOperandEmptyFirstIncorrect() {
        viewModel.setFirstOperandString("incorrect input");
        viewModel.setSecondOperandString("");
        assertEquals(Status.BAD.toString(), viewModel.getStatusString());
    }

    @Test
    public void isStatusWaitWhenSecondOperandEmptyFirstCorrect() {
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString("");
        assertEquals(Status.WAIT.toString(), viewModel.getStatusString());
    }

    @Test
    public void isStatusReadyWhenInputCorrect() {
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString(correctSecondPolynomialOperandString);
        assertEquals(Status.READY.toString(), viewModel.getStatusString());
    }

    @Test
    public void isStatusReadyWhenInputCorrectForExponentiation() {
        viewModel.setOperation(Operation.EXPONENTIATE);
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString(correctSecondExponentOperandString);
        assertEquals(Status.READY.toString(), viewModel.getStatusString());
    }

    @Test
    public void isCalculationCorrectForAddWhenInputCorrect() {
        viewModel.setFirstOperandString("5.3*x^9 + 0.5*x^3 + 55*x");
        viewModel.setSecondOperandString("1*x^3 - 5*x");
        viewModel.setOperation(Operation.ADD);

        viewModel.calculate();

        assertEquals("50.0*x^1 + 1.5*x^3 + 5.3*x^9", viewModel.getResultString());
    }

    @Test
    public void isCalculationCorrectForSubtractWhenInputCorrect() {
        viewModel.setFirstOperandString("5.3*x^9 + 0.5*x^3 + 55*x");
        viewModel.setSecondOperandString("1*x^3 - 5*x");
        viewModel.setOperation(Operation.SUBTRACT);

        viewModel.calculate();

        assertEquals("60.0*x^1 - 0.5*x^3 + 5.3*x^9", viewModel.getResultString());
    }

    @Test
    public void isCalculationCorrectForMultiplyWhenInputCorrect() {
        viewModel.setFirstOperandString("6*x^9 + 0.5*x^3 + 55*x");
        viewModel.setSecondOperandString("3*x");
        viewModel.setOperation(Operation.MULTIPLY);

        viewModel.calculate();

        assertEquals("165.0*x^2 + 1.5*x^4 + 18.0*x^10", viewModel.getResultString());
    }

    @Test
    public void isCalculationCorrectForDivideWhenInputCorrect() {
        viewModel.setFirstOperandString("6*x^9 + 1.5*x^3 + 75*x");
        viewModel.setSecondOperandString("3*x");
        viewModel.setOperation(Operation.DIVIDE);

        viewModel.calculate();

        assertEquals("25.0*x^0 + 0.5*x^2 + 2.0*x^8", viewModel.getResultString());
    }

    @Test
    public void isCalculationCorrectForExponentiateWhenInputCorrect() {
        viewModel.setFirstOperandString("x^2");
        viewModel.setSecondOperandString("3");
        viewModel.setOperation(Operation.EXPONENTIATE);

        viewModel.calculate();

        assertEquals("1.0*x^8", viewModel.getResultString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isCalculationCorrectForExponentiateWhenInputIncorrect() {
        viewModel.setFirstOperandString("incorrect input");
        viewModel.setSecondOperandString("incorrect input");
        viewModel.setOperation(Operation.EXPONENTIATE);

        viewModel.calculate();
    }

    @Test
    public void canGetOperations() {
        assertNotNull(viewModel.getOperations());
    }

    @Test
    public void canGetOperationsProperty() {
        assertNotNull(viewModel.operationsProperty());
    }

    @Test
    public void canGetOperationProperty() {
        assertNotNull(viewModel.operationProperty());
    }

    @Test
    public void canGetFirstOperandStringProperty() {
        assertNotNull(viewModel.firstOperandStringProperty());
    }

    @Test
    public void canGetSecondOperandStringProperty() {
        assertNotNull(viewModel.secondOperandStringProperty());
    }

    @Test
    public void canGetResultStringProperty() {
        assertNotNull(viewModel.resultStringProperty());
    }

    @Test
    public void canGetStatusStringProperty() {
        assertNotNull(viewModel.statusStringProperty());
    }

    @Test
    public void canGetCalculationDisabledProperty() {
        assertNotNull(viewModel.calculationDisabledProperty());
    }

    @Test
    public void canConvertOperationToString() {
        assertNotNull(viewModel.getOperation().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        new ViewModel(null);
    }

    @Test
    public void canGetAccessLogProperty() {
        viewModel.setOperation(Operation.SUBTRACT);
        String log = viewModel.logsProperty().get();
        assertFalse(log.isEmpty());
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterCalculation() {
        setInputData();
        viewModel.calculate();
        String message = viewModel.getLogs();
        assertTrue(message.matches("(?s).*" + LogMessages.CALCULATE_WAS_PRESSED.toString() + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLogs();
        assertTrue(message.matches("(?s).*" + correctFirstPolynomialOperandRegex
                + ".*" + correctSecondPolynomialOperandRegex + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLogs();
        assertTrue(message.matches("(?s).*Arguments"
                + ": First = " + correctFirstPolynomialOperandRegex
                + "; Second = " + correctSecondPolynomialOperandRegex + ".*"));
    }

    @Test
    public void operationTypeIsMentionedInTheLog() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLogs();
        assertTrue(message.matches("(?s).*Add.*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();

        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canSeeOperationChangeInLog() {
        viewModel.setOperation(Operation.MULTIPLY);
        String message = viewModel.getLogs();
        assertTrue(message.matches("(?s).*" + LogMessages.OPERATION_WAS_CHANGED + "Multiply.*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() {
        viewModel.setOperation(Operation.MULTIPLY);
        viewModel.setOperation(Operation.MULTIPLY);
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void argumentsAreCorrectlyLogged() {
        setInputData();
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        String message = viewModel.getLogs();

        assertTrue(message.matches("(?s).*" + LogMessages.EDITING_FINISHED.toString()
                + "Input arguments are: \\["
                + correctFirstPolynomialOperandRegex + "; "
                + correctSecondPolynomialOperandRegex + "].*"));
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.firstOperandStringProperty().set("x");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.firstOperandStringProperty().set("x");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }
}
