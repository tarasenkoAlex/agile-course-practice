package ru.unn.agile.MultisystemCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.MultisystemCalculator.Model.Format;

import static org.junit.Assert.assertEquals;


/**
 * Created by Дарья on 01.12.2016.
 */
public class MultisystemCalculatorViewModelTest {
    private CalculatorViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new CalculatorViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void testCanSetDefaultValues() {
        assertEquals("", viewModel.firstArgProperty().get());
        assertEquals("", viewModel.secondArgProperty().get());
        assertEquals(Operation.ADDITION, viewModel.selectedOperationProperty().get());
        assertEquals("", viewModel.resultProperty().get());
    }

    @Test
    public void testCanInputWrongFormat() {
        viewModel.firstArgProperty().set("3a5");
        viewModel.secondArgProperty().set("1a3");
        viewModel.selectedOperationProperty().set(Operation.ADDITION);
        viewModel.selectedFormatProperty().set(Format.BIN);
        viewModel.calculate();
        assertEquals("Illegal number argument. "
                + "Please, follow convention of arguments representation:"
                + " 0b[-]{0,1} | 0o[-]{0-7} | 0x[-]{0-9,A,B,C,D,E,F}", viewModel.resultProperty()
                .getValue());
    }

    @Test
    public void testCanDivisionByZero() {
        viewModel.firstArgProperty().set("0b0");
        viewModel.secondArgProperty().set("0x0");
        viewModel.selectedOperationProperty().set(Operation.DIVISION);
        viewModel.selectedFormatProperty().set(Format.BIN);
        viewModel.calculate();
        assertEquals("Division by zero is forbidden", viewModel.resultProperty().getValue());
    }

    @Test
    public void testCanInputNull() {
        viewModel.firstArgProperty().set(null);
        viewModel.secondArgProperty().set("");
        viewModel.selectedOperationProperty().set(Operation.ADDITION);
        viewModel.selectedFormatProperty().set(Format.BIN);
        viewModel.calculate();
        assertEquals("Number representation can't be empty", viewModel.resultProperty().getValue());
    }

    @Test
    public void testCanInputOverflow() {
        viewModel.firstArgProperty().set("0x7FFFFFFF");
        viewModel.secondArgProperty().set("0x7FFFFFFF");
        viewModel.selectedOperationProperty().set(Operation.MULTIPLICATION);
        viewModel.selectedFormatProperty().set(Format.BIN);
        viewModel.calculate();
        assertEquals("Multiplication leads to Integer overflow", viewModel.resultProperty()
                .getValue());
    }

    @Test
    public void canSetMultOperation() {
        viewModel.selectedOperationProperty().set(Operation.MULTIPLICATION);
        assertEquals(Operation.MULTIPLICATION, viewModel.selectedOperationProperty().get());
    }

    @Test
    public void addIsDefaultOperation() {
        assertEquals(Operation.ADDITION, viewModel.selectedOperationProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() {
        viewModel.firstArgProperty().set("0b101");
        viewModel.secondArgProperty().set("0x0101");
        viewModel.selectedOperationProperty().set(Operation.ADDITION);
        viewModel.selectedFormatProperty().set(Format.BIN);
        viewModel.calculate();

        assertEquals("0b100000110", viewModel.resultProperty().getValue());
    }

    @Test
    public void operationMulHasCorrectResult() {
        viewModel.firstArgProperty().set("0b101");
        viewModel.secondArgProperty().set("0x0101");
        viewModel.selectedOperationProperty().set(Operation.MULTIPLICATION);
        viewModel.selectedFormatProperty().set(Format.HEX);
        viewModel.calculate();

        assertEquals("0x505", viewModel.resultProperty().get());
    }

    @Test
    public void operationSubWithNegativeNumbersHasCorrectResult() {
        viewModel.firstArgProperty().set("0b-1101");
        viewModel.secondArgProperty().set("0b11");
        viewModel.selectedOperationProperty().set(Operation.SUBTRACTION);
        viewModel.selectedFormatProperty().set(Format.OCT);
        viewModel.calculate();

        assertEquals("0o-20", viewModel.resultProperty().get());
    }

}
