package ru.unn.agile.Fraction.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Fraction.model.Operation;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
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
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
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
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.frac1Property().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.frac1Property().set("1");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetAddOperation() {
        viewModel.operationProperty().set(Operation.ADD);
        assertEquals(Operation.ADD, viewModel.operationProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() {
        viewModel.frac1Property().set("1");
        viewModel.frac2Property().set("4");

        viewModel.calculate();

        assertEquals("5", viewModel.resultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
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
    public void operationMulHasCorrectResult() {
        viewModel.frac1Property().set("2");
        viewModel.frac2Property().set("3");
        viewModel.operationProperty().set(Operation.MULTIPLY);

        viewModel.calculate();

        assertEquals("6", viewModel.resultProperty().get());
    }

    @Test
    public void operationAddWithNegativeNumbersHasCorrectResult() {
        viewModel.frac1Property().set("2");
        viewModel.frac2Property().set("-3");
        viewModel.operationProperty().set(Operation.ADD);

        viewModel.calculate();

        assertEquals("-1", viewModel.resultProperty().get());
    }

    @Test
    public void statusIsBadWhenZeroDenominatorFullFilled() {
        viewModel.frac1Property().set("1/0");
        viewModel.frac2Property().set("1");
        viewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsBadWhenZeroDenominatorPartFilled() {
        viewModel.frac1Property().set("1/0");
        viewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsBadWhenDivideByZero() {
        viewModel.frac1Property().set("1/2");
        viewModel.frac2Property().set("0");
        viewModel.operationProperty().set(Operation.DIVISION);
        viewModel.calculate();
        assertEquals(Status.DIV_ZERO.toString(), viewModel.statusProperty().get());
    }

    private void setInputData() {
        viewModel.frac1Property().set("1");
        viewModel.frac2Property().set("2");
    }
}
