package ru.unn.agile.polynomial.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.polynomial.viewmodel.ViewModel.Operation;
import ru.unn.agile.polynomial.viewmodel.ViewModel.Status;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private String correctFirstPolynomialOperandString = "5.3*x^9 + 0.5*x^3 + 55*x";
    private String correctSecondPolynomialOperandString = "x";
    private String correctSecondExponentOperandString = "3";

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void areDefaultsCorrect() {
        assertEquals("", viewModel.getSecondOperandLabelString());
        assertEquals("", viewModel.getFirstOperandString());
        assertEquals("", viewModel.getSecondOperandString());
        assertEquals("", viewModel.getResultString());
        assertEquals(Operation.ADD.toString(), viewModel.getOperationString());
        assertEquals(Status.WAIT.toString(), viewModel.getStatusString());
    }

    @Test
    public void canSetOperation() {
        viewModel.setOperationString(Operation.MULTIPLY.toString());
        assertEquals(Operation.MULTIPLY.toString(), viewModel.getOperationString());
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
        viewModel.setOperationString(Operation.EXPONENTIATE.toString());
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString(correctSecondPolynomialOperandString);
        assertEquals(true, viewModel.isCalculationDisabled());
    }

    @Test
    public void isCalculationDisabledWhenExponentiateOperationAndNegativeExponent() {
        viewModel.setOperationString(Operation.EXPONENTIATE.toString());
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString("-2");
        assertEquals(true, viewModel.isCalculationDisabled());
    }

    @Test
    public void isCalculationEnabledWhenExponentiateOperationAndCorrectOperands() {
        viewModel.setOperationString(Operation.EXPONENTIATE.toString());
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
        viewModel.setOperationString(Operation.EXPONENTIATE.toString());
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
        viewModel.setOperationString(Operation.EXPONENTIATE.toString());
        viewModel.setFirstOperandString(correctFirstPolynomialOperandString);
        viewModel.setSecondOperandString(correctSecondExponentOperandString);
        assertEquals(Status.READY.toString(), viewModel.getStatusString());
    }

    @Test
    public void isCalculationCorrectForAddWhenInputCorrect() {
        viewModel.setFirstOperandString("5.3*x^9 + 0.5*x^3 + 55*x");
        viewModel.setSecondOperandString("1*x^3 - 5*x");
        viewModel.setOperationString(Operation.ADD.toString());

        viewModel.calculate();

        assertEquals("50.0*x^1 + 1.5*x^3 + 5.3*x^9", viewModel.getResultString());
    }

    @Test
    public void isCalculationCorrectForSubtractWhenInputCorrect() {
        viewModel.setFirstOperandString("5.3*x^9 + 0.5*x^3 + 55*x");
        viewModel.setSecondOperandString("1*x^3 - 5*x");
        viewModel.setOperationString(Operation.SUBTRACT.toString());

        viewModel.calculate();

        assertEquals("60.0*x^1 - 0.5*x^3 + 5.3*x^9", viewModel.getResultString());
    }

    @Test
    public void isCalculationCorrectForMultiplyWhenInputCorrect() {
        viewModel.setFirstOperandString("6*x^9 + 0.5*x^3 + 55*x");
        viewModel.setSecondOperandString("3*x");
        viewModel.setOperationString(Operation.MULTIPLY.toString());

        viewModel.calculate();

        assertEquals("165.0*x^2 + 1.5*x^4 + 18.0*x^10", viewModel.getResultString());
    }

    @Test
    public void isCalculationCorrectForDivideWhenInputCorrect() {
        viewModel.setFirstOperandString("6*x^9 + 1.5*x^3 + 75*x");
        viewModel.setSecondOperandString("3*x");
        viewModel.setOperationString(Operation.DIVIDE.toString());

        viewModel.calculate();

        assertEquals("25.0*x^0 + 0.5*x^2 + 2.0*x^8", viewModel.getResultString());
    }

    @Test
    public void isCalculationCorrectForExponentiateWhenInputCorrect() {
        viewModel.setFirstOperandString("x^2");
        viewModel.setSecondOperandString("3");
        viewModel.setOperationString(Operation.EXPONENTIATE.toString());

        viewModel.calculate();

        assertEquals("1.0*x^8", viewModel.getResultString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isCalculationCorrectForExponentiateWhenInputIncorrect() {
        viewModel.setFirstOperandString("incorrect input");
        viewModel.setSecondOperandString("incorrect input");
        viewModel.setOperationString(Operation.EXPONENTIATE.toString());

        viewModel.calculate();
    }
}
