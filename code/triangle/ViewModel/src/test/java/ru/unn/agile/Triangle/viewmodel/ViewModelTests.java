package ru.unn.agile.Triangle.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals("", viewModel.axProperty().get());
        assertEquals("", viewModel.ayProperty().get());
        assertEquals("", viewModel.bxProperty().get());
        assertEquals("", viewModel.byProperty().get());
        assertEquals("", viewModel.cxProperty().get());
        assertEquals("", viewModel.cyProperty().get());

        assertEquals("", viewModel.getArea());
        assertEquals("", viewModel.getPerimeter());
        assertEquals("", viewModel.getCircumcircleRadius());
        assertEquals("", viewModel.getCircumcircleCenterX());
        assertEquals("", viewModel.getCircumcircleCenterX());
        assertEquals("", viewModel.getIncircleRadius());
        assertEquals("", viewModel.getIncircleCenterX());
        assertEquals("", viewModel.getIncircleCenterY());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void whenInputIsEnteredCalculateButtonIsEnabled() {
        setInputData();

        assertFalse(viewModel.getCalculationDisabled());
    }

    @Test
    public void whenInputHasEmptyFieldsCalculateButtonIsDisabled() {
        setInputData();
        viewModel.axProperty().set("");

        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void whenInputIsIncorrectCalculateButtonIsDisabled() {
        setInputData();
        viewModel.axProperty().set("atata");

        assertTrue(viewModel.getCalculationDisabled());
    }

    @Test
    public void canCalculateResultsWithCorrectInput() {
        setInputData();
        viewModel.calculate();

        assertEquals("0.5", viewModel.getArea());
        assertEquals("3.414", viewModel.getPerimeter());
        assertEquals("0.707", viewModel.getCircumcircleRadius());
        assertEquals("0.5", viewModel.getCircumcircleCenterX());
        assertEquals("0.5", viewModel.getCircumcircleCenterY());
        assertEquals("0.293", viewModel.getIncircleRadius());
        assertEquals("0.293", viewModel.getIncircleCenterX());
        assertEquals("0.293", viewModel.getIncircleCenterY());
    }

    private void setInputData() {
        viewModel.axProperty().set("0.0");
        viewModel.ayProperty().set("0.0");
        viewModel.bxProperty().set("1.0");
        viewModel.byProperty().set("0.0");
        viewModel.cxProperty().set("0.0");
        viewModel.cyProperty().set("1.0");
    }
}
