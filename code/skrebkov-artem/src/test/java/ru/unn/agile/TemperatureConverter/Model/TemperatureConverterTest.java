package ru.unn.agile.TemperatureConverter.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TemperatureConverterTest {
    private static final double DELTA = 0.000001;

    @Test
    public void convertCelsius_0_To_Kelvin() {
        TemperatureConverter converter = new TemperatureConverter();

        double kelvin = converter.convert(0.0, TemperatureScale.CELSIUS, TemperatureScale.KELVIN);

        assertEquals(273.15, kelvin, DELTA);
    }

    @Test
    public void convertCelsius_100_To_Kelvin() {
        TemperatureConverter converter = new TemperatureConverter();

        double kelvin = converter.convert(100.0, TemperatureScale.CELSIUS, TemperatureScale.KELVIN);

        assertEquals(373.15, kelvin, DELTA);
    }

    @Test
    public void convertCelsius_0_To_Fahrenheit() {
        TemperatureConverter converter = new TemperatureConverter();

        double fahrenheit = converter.convert(0, TemperatureScale.CELSIUS, TemperatureScale.FAHRENHEIT);

        assertEquals(32.0, fahrenheit, DELTA);
    }

    @Test
    public void convertCelsius_100_To_Fahrenheit() {
        TemperatureConverter converter = new TemperatureConverter();

        double fahrenheit = converter.convert(100.0, TemperatureScale.CELSIUS, TemperatureScale.FAHRENHEIT);

        assertEquals(212.0, fahrenheit, DELTA);
    }

    @Test
    public void convertCelsius_0_To_Newton() {
        TemperatureConverter converter = new TemperatureConverter();

        double newton = converter.convert(0.0, TemperatureScale.CELSIUS, TemperatureScale.NEWTON);

        assertEquals(0.0, newton, DELTA);
    }

    @Test
    public void convertCelsius_100_To_Newton() {
        TemperatureConverter converter = new TemperatureConverter();

        double newton = converter.convert(100.0, TemperatureScale.CELSIUS, TemperatureScale.NEWTON);

        assertEquals(33.0, newton, DELTA);
    }

    @Test
    public void convertKelvin_0_To_Celsius() {
        TemperatureConverter converter = new TemperatureConverter();

        double celsius = converter.convert(0.0, TemperatureScale.KELVIN, TemperatureScale.CELSIUS);

        assertEquals(-273.15, celsius, DELTA);
    }

    @Test
    public void convertKelvin_100_To_Celsius() {
        TemperatureConverter converter = new TemperatureConverter();

        double celsius = converter.convert(100.0, TemperatureScale.KELVIN, TemperatureScale.CELSIUS);

        assertEquals(-173.15, celsius, DELTA);
    }

    @Test
    public void convertKelvin_0_To_Newton() {
        TemperatureConverter converter = new TemperatureConverter();

        double kelvin = converter.convert(0.0, TemperatureScale.KELVIN, TemperatureScale.NEWTON);

        assertEquals(-90.1395, kelvin, DELTA);
    }

    @Test
    public void convertKelvin_0_To_Fahrenheit() {
        TemperatureConverter converter = new TemperatureConverter();

        double fahrenheit = converter.convert(0.0, TemperatureScale.KELVIN, TemperatureScale.FAHRENHEIT);

        assertEquals(-459.67, fahrenheit, DELTA);
    }

    @Test
    public void convertKelvin_100_To_Fahrenheit() {
        TemperatureConverter converter = new TemperatureConverter();

        double fahrenheit = converter.convert(100.0, TemperatureScale.KELVIN, TemperatureScale.FAHRENHEIT);

        assertEquals(-279.67, fahrenheit, DELTA);
    }

    @Test
    public void convertNewton_0_To_Kelvin() {
        TemperatureConverter converter = new TemperatureConverter();

        double kelvin = converter.convert(0.0, TemperatureScale.NEWTON, TemperatureScale.KELVIN);

        assertEquals(273.15, kelvin, DELTA);
    }

    @Test
    public void convertNewton_33_To_Kelvin() {
        TemperatureConverter converter = new TemperatureConverter();

        double kelvin = converter.convert(33.0, TemperatureScale.NEWTON, TemperatureScale.KELVIN);

        assertEquals(373.15, kelvin, DELTA);
    }

    @Test
    public void convertNewton_0_To_Fahrenheit() {
        TemperatureConverter converter = new TemperatureConverter();

        double fahrenheit = converter.convert(0.0, TemperatureScale.NEWTON, TemperatureScale.FAHRENHEIT);

        assertEquals(32.0, fahrenheit, DELTA);
    }

    @Test
    public void convertNewton_11_To_Fahrenheit() {
        TemperatureConverter converter = new TemperatureConverter();

        double fahrenheit = converter.convert(11.0, TemperatureScale.NEWTON, TemperatureScale.FAHRENHEIT);

        assertEquals(92.0, fahrenheit, DELTA);
    }

    @Test
    public void convertNewton_0_To_Celsius() {
        TemperatureConverter converter = new TemperatureConverter();

        double celsius = converter.convert(0.0, TemperatureScale.NEWTON, TemperatureScale.CELSIUS);

        assertEquals(0.0, celsius, DELTA);
    }

    @Test
    public void convertNewton_33_To_Celsius() {
        TemperatureConverter converter = new TemperatureConverter();

        double celsius = converter.convert(33.0, TemperatureScale.NEWTON, TemperatureScale.CELSIUS);

        assertEquals(100.0, celsius, DELTA);
    }
}
