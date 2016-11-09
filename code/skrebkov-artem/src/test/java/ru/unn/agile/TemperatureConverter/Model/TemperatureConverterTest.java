package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TemperatureConverterTest {
    private static final double DELTA = 0.000001;

    @Test
    public void convertCelsius_0_To_Kelvin() {
        TemperatureConverter converter = new TemperatureConverter();

        double kelvin = converter.celsiusToKelvin(0.0);

        assertEquals(273.15, kelvin, DELTA);
    }

    @Test
    public void convertCelsius_100_To_Kelvin() {
        TemperatureConverter converter = new TemperatureConverter();

        double kelvin = converter.celsiusToKelvin(100.0);

        assertEquals(373.15, kelvin, DELTA);
    }
}
