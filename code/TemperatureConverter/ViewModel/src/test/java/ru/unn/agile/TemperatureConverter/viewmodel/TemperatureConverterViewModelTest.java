package ru.unn.agile.TemperatureConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.TemperatureConverter.model.TemperatureScale;

import static org.junit.Assert.*;

public class TemperatureConverterViewModelTest {
    private TemperatureConverterViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new TemperatureConverterViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void setDefaultValuesOfTemperatureFields() {
        assertEquals("", viewModel.getFirstValue());
        assertEquals("", viewModel.getSecondValue());
    }

    @Test
    public void canSetValuesFromTextFirstField() {
        viewModel.setFirstValue("10");

        assertEquals("10", viewModel.getFirstValue());
    }

    @Test
    public void canSetValuesFromTextSecondField() {
        viewModel.setSecondValue("af");

        assertEquals("af", viewModel.getSecondValue());
    }

    @Test
    public void canSetDefaultValueOfTemperatureScalesFields() {
        assertEquals(TemperatureScale.CELSIUS, viewModel.getFirstScale());
        assertEquals(TemperatureScale.CELSIUS, viewModel.getSecondScale());
    }

    @Test
    public void canSetValuesFromComboBoxes() {
        viewModel.setFirstScale("KELVIN");
        viewModel.setSecondScale("KELVIN");

        assertEquals(TemperatureScale.KELVIN, viewModel.getFirstScale());
        assertEquals(TemperatureScale.KELVIN, viewModel.getSecondScale());
    }

    @Test
    public void canSetDefaultValueToScaleFieldsWhenInputValueIsIncorrect() {
        viewModel.setFirstScale("AD");
        viewModel.setSecondScale("1R");

        assertEquals(TemperatureScale.CELSIUS, viewModel.getFirstScale());
        assertEquals(TemperatureScale.CELSIUS, viewModel.getSecondScale());
    }

    @Test
    public void canCorrectlyConvertTemperatureValuesWhenInputFirstValueAndScalesIsEqual() {
        viewModel.setFirstScale("CELSIUS");
        viewModel.setSecondScale("CELSIUS");
        viewModel.setFirstValue("0");

        assertEquals("0.0", viewModel.getSecondValue());
    }

    @Test
    public void canCorrectlyConvertTemperatureValuesWhenInputSecondValueAndScalesIsEqual() {
        viewModel.setFirstScale("CELSIUS");
        viewModel.setSecondScale("CELSIUS");
        viewModel.setSecondValue("0");

        assertEquals("0.0", viewModel.getFirstValue());
    }

    @Test
    public void canCorrectlyConvertTemperatureValuesWhenInputFirstValueAndScalesIsDiferent() {
        viewModel.setFirstScale("CELSIUS");
        viewModel.setSecondScale("FAHRENHEIT");
        viewModel.setFirstValue("0");

        assertEquals("32.0", viewModel.getSecondValue());
    }

    @Test
    public void canCorrectlyConvertTemperatureValuesWhenInputSecondValueAndScalesIsDiferent() {
        viewModel.setFirstScale("KELVIN");
        viewModel.setSecondScale("CELSIUS");
        viewModel.setFirstValue("0");

        assertEquals("-273.15", viewModel.getSecondValue());
    }

    @Test
    public void canSetWarningAndEmptyStringToSecondValueWhenInputIsIncorrect() {
        viewModel.setFirstValue("a");

        assertEquals("We wrote incorrect value of temperature!", viewModel.getWarningLabelText());
        assertEquals("", viewModel.getSecondValue());
    }

    @Test
    public void canSetWarningAndEmptyStringToSecondValueWhenInputIsLowerThanAbsoluteZero() {
        viewModel.setSecondScale("KELVIN");
        viewModel.setFirstScale("FAHRENHEIT");
        viewModel.setFirstValue("-300");

        assertEquals("We wrote incorrect value of temperature!", viewModel.getWarningLabelText());
        assertEquals("", viewModel.getSecondValue());
    }

    @Test
    public void canSetEmptyStringToWarningLabelWhenUserWritedCorrectValue() {
        viewModel.setSecondScale("KELVIN");
        viewModel.setFirstScale("FAHRENHEIT");
        viewModel.setFirstValue("as");

        viewModel.setFirstValue("250");

        assertEquals("", viewModel.getWarningLabelText());
    }

    @Test
    public void canConvertTemperatureAfterChangeSecondScale() {
        viewModel.setFirstScale("KELVIN");
        viewModel.setSecondScale("FAHRENHEIT");
        viewModel.setFirstValue("250");

        viewModel.setSecondScale("CELSIUS");

        assertEquals("-23.149999999999977", viewModel.getSecondValue());
    }

    @Test
    public void canConvertTemperatureAfterChangeFirstScale() {
        viewModel.setFirstScale("CELSIUS");
        viewModel.setSecondScale("FAHRENHEIT");
        viewModel.setSecondValue("50");

        viewModel.setFirstScale("KELVIN");

        assertEquals("283.15", viewModel.getFirstValue());
    }
}
