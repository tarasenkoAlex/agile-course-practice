package ru.unn.agile.MortgageCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;

    private final String debt = "2000000";
    private final String years = "10";
    private final String percents = "20";

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
}
