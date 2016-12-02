package ru.unn.agile.ComplexNumberCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ComplexNumberCalculatorViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ComplexNumberCalculatorViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.firstArgRealProperty().get());
        assertEquals("", viewModel.firstArgImProperty().get());
        assertEquals("", viewModel.secondArgRealProperty().get());
        assertEquals("", viewModel.secondArgImProperty().get());
        assertEquals(Operations.ADDITION, viewModel.selectedOperationProperty().get());
        assertEquals("", viewModel.resultProperty().get());
    }


    @Test
    public void canSetAddOperation() {
        viewModel.selectedOperationProperty().set(Operations.ABS);
        assertEquals(Operations.ABS, viewModel.selectedOperationProperty().get());
    }


    @Test
    public void operationAddHasCorrectResult() {
        viewModel.firstArgRealProperty().set("1");
        viewModel.firstArgImProperty().set("4");
        viewModel.secondArgRealProperty().set("-2");
        viewModel.secondArgImProperty().set("-2.5");

        viewModel.calculate();

        assertEquals("-1.0 + 1.5i", viewModel.resultProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.firstArgRealProperty().set("one");
        viewModel.firstArgImProperty().set("4");
        viewModel.secondArgRealProperty().set("-2");
        viewModel.secondArgImProperty().set("-2.5");

        viewModel.calculate();

        assertEquals("Incorrect input for the first argument", viewModel.resultProperty().get());
    }


    @Test
    public void operationMulHasCorrectResult() {
        viewModel.firstArgRealProperty().set("2");
        viewModel.firstArgImProperty().set("3");
        viewModel.secondArgRealProperty().set("1");
        viewModel.secondArgImProperty().set("2");
        viewModel.selectedOperationProperty().set(Operations.MULTIPLICATION);

        viewModel.calculate();

        assertEquals("-4.0 + 7.0i", viewModel.resultProperty().get());
    }

    @Test
    public void operationAddWithNegativeNumbersHasCorrectResult() {
        viewModel.firstArgRealProperty().set("1.2");
        viewModel.firstArgImProperty().set("2.3");
        viewModel.secondArgRealProperty().set("-10.4");
        viewModel.secondArgImProperty().set("-20.5");
        viewModel.selectedOperationProperty().set(Operations.ADDITION);

        viewModel.calculate();

        assertEquals("-9.2 - 18.2i", viewModel.resultProperty().get());
    }

}
