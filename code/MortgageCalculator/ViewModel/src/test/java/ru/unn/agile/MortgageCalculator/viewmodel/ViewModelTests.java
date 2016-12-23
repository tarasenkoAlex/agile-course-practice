package ru.unn.agile.MortgageCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private static final int ANY_KEY = 123;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getDebt());
        assertEquals("", viewModel.getYears());
        assertEquals("", viewModel.getPercents());
        assertEquals("", viewModel.getOverPayment());
        assertEquals("", viewModel.getPayment());
        assertEquals("", viewModel.getTotalSum());
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.setDebt("1");

        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertFalse(viewModel.isCalcButtonEnabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.setDebt("trash");

        assertFalse(viewModel.isCalcButtonEnabled());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.setDebt("1");

        assertFalse(viewModel.isCalcButtonEnabled());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertTrue(viewModel.isCalcButtonEnabled());
    }

    @Test
    public void calculatingHasCorrectResult() {
        viewModel.setDebt("1000000");
        viewModel.setYears("40");
        viewModel.setPercents("5");

        viewModel.calculate();

        assertEquals("1284091.37", viewModel.getOverPayment());
        assertEquals("4758.53", viewModel.getPayment());
        assertEquals("2284091.37", viewModel.getTotalSum());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void badFormatWhenDebtIsNegative() {
        viewModel.setDebt("-1");

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void badFormatWhenYearsIsNegative() {
        viewModel.setYears("-1");

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void badFormatWhenPercentsAreOutOfRange() {
        viewModel.setPercents("-1");

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }


    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("No exception error!");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Wrong exception type");
        }
    }

    @Test
    public void EmptyLogInTheBeginning() {
        List<String> logList = viewModel.getLog();

        assertTrue(logList.isEmpty());
    }

    @Test
    public void logListContainsProperMessageAfterCalc() {
        setInputData();

        viewModel.calculate();
        String logMessage = viewModel.getLog().get(0);

        assertTrue(logMessage.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterCalculation() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.getDebt()
                + ".*" + viewModel.getYears()
                + ".*" + viewModel.getPercents() + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": Debt = " + viewModel.getDebt()
                + "; Years = " + viewModel.getYears()
                + "; Percents = " + viewModel.getPercents() + ".*"));
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        setInputData();

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertFalse(viewModel.isCalcButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        viewModel.setDebt("trash");

        viewModel.processKeyInTextField(ANY_KEY);

        assertFalse(viewModel.isCalcButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWithIncompleteInput() {
        viewModel.setDebt("1000");
        viewModel.setYears("2");

        viewModel.processKeyInTextField(ANY_KEY);

        assertFalse(viewModel.isCalcButtonEnabled());
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        setInputData();

        viewModel.processKeyInTextField(ANY_KEY);

        assertTrue(viewModel.isCalcButtonEnabled());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.setDebt("a");

        viewModel.calculate();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        setInputData();

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        setInputData();

        viewModel.processKeyInTextField(viewModel.ENTER_KEY_CODE);

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    private void setInputData() {
        viewModel.setDebt("1");
        viewModel.setYears("2");
        viewModel.setPercents("3");
    }
}
