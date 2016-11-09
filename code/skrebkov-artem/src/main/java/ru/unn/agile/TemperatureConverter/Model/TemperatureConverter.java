package ru.unn.agile.TemperatureConverter.Model;

public class TemperatureConverter {

    public double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public double celsiusToFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32.0;
    }

    public double celsiusToNewton(double celsius) {
        return celsius * 33.0 / 100.0;
    }
}
