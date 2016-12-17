package ru.unn.agile.MultisystemCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.MultisystemCalculator.Model.Format;

import java.util.List;

import static org.junit.Assert.*;
import static ru.unn.agile.MultisystemCalculator.viewmodel.CalculatorViewModel.LogMessages.*;

public class MultisystemCalculatorViewModelTest {
    private CalculatorViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new CalculatorViewModel(new FakeLogger());
    }

    public void setExternalViewModel(final CalculatorViewModel viewModel) {
        this.viewModel = viewModel;
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
        assertEquals("Please provide input data", viewModel.resultProperty().get());
    }

    @Test
    public void canCreateObjectWithDefaultConstructor() {
        assertNotNull(new CalculatorViewModel());
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

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new CalculatorViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
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
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + ".*" + viewModel.firstArgProperty().get()
                + ".*" + viewModel.secondArgProperty().get() + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        System.out.print(message);
        assertTrue(message.matches(".*Arguments"
                + ": first argument = " + viewModel.firstArgProperty().get()
                + "; second argument = " + viewModel.secondArgProperty().get() + ".*"));
    }

    @Test
    public void operationTypeIsMentionedInTheLog() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*ADDITION.*"));
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
        setInputData();

        viewModel.onOperationChanged(Operation.ADDITION, Operation.DIVISION);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + OPERATION_WAS_CHANGED + "DIVISION.*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() {
        viewModel.onOperationChanged(Operation.ADDITION, Operation.DIVISION);

        viewModel.onOperationChanged(Operation.ADDITION, Operation.ADDITION);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void argumentsAreCorrectlyLogged() {
        setInputData();

        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.firstArgProperty().get() + "; "
                + viewModel.secondArgProperty().get() + "]"));
    }

    @Test
    public void calculateIsNotCalledWhenButtonIsDisabled() {
        viewModel.calculate();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.firstArgProperty().set("0b01");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.firstArgProperty().set("0b01");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }



    private void setInputData() {
        viewModel.firstArgProperty().set("0b01");
        viewModel.secondArgProperty().set("0b11");
        viewModel.selectedOperationProperty().set(Operation.ADDITION);
        viewModel.selectedFormatProperty().set(Format.BIN);
    }

}
