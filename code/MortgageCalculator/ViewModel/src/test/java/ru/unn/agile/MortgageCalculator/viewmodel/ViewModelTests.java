package ru.unn.agile.MortgageCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ViewModelTests {

    private final String debt = "2000000";
    private final String years = "10";
    private final String percents = "20";

    private ViewModel viewModel;

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
        assertEquals("", viewModel.getTotalSum());
        assertEquals("", viewModel.getPayment());
        assertEquals("", viewModel.getOverPayment());
        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void checkStatusBadFormat() {
        viewModel.setDebt("Wrong input");
        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    void inputCheckData() {
        viewModel.setDebt(debt);
        viewModel.setYears(years);
        viewModel.setPercents(percents);
    }

    @Test
    public void checkStatusReady() {
        inputCheckData();
        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void checkCalculate() {
        inputCheckData();
        viewModel.calculate();
        assertEquals("38716.86", viewModel.getPayment());
        assertEquals("4646022.34", viewModel.getTotalSum());
        assertEquals("2646022.34", viewModel.getOverPayment());
    }

    @Test
    public void activityButtonWhenInputCorrectData() {
        inputCheckData();
        assertEquals(true, viewModel.isCalcButtonEnabled());
    }

    @Test
    public void activityButtonWhenInputNoCorrectData() {
        inputCheckData();
        viewModel.setDebt("Wrong input");
        assertEquals(false, viewModel.isCalcButtonEnabled());
    }

    @Test
    public void activityButtonWhenInputNotAllData() {
        viewModel.setYears(years);
        assertEquals(false, viewModel.isCalcButtonEnabled());
    }

    @Test
    public void checkResultFieldsWhenChangeData() {
        checkCalculate();
        viewModel.setPercents("10");
        assertEquals("", viewModel.getOverPayment());
        assertEquals("", viewModel.getPayment());
        assertEquals("", viewModel.getTotalSum());
    }

    @Test
    public void inputNegativeNumberOrNull() {
        inputCheckData();
        viewModel.setYears("-10");
        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void inputOverMaxPercents() {
        inputCheckData();
        viewModel.setPercents("110");
        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
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
        inputCheckData();
        viewModel.calculate();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }
}
