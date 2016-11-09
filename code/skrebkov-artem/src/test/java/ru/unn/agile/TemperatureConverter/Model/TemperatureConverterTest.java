package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TemperatureConverterTest {

    @Test
    public void failingTest() {
        fail();
    }

    @Test
    public void convertCelsius_0_To_Kelvin() {
        TemperatureConverter converter = new TemperatureConverter();

        double kelvin = converter.celsiusToKelvin(0.0);

        assertEquals(273.15, kelvin, 0.000001);
    }
}
