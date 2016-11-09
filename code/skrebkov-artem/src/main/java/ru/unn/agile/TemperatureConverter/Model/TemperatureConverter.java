package ru.unn.agile.TemperatureConverter.Model;

public class TemperatureConverter {

    public double fromCelsius(double celsius, TemperatureScale scale) {
        return celsius / TemperatureScale.CELSIUS.factor * scale.factor
                + TemperatureScale.CELSIUS.shift + scale.shift;
    }

    public double fromKelvin(double kelvin, TemperatureScale scale) {
        return kelvin / TemperatureScale.KELVIN.factor * scale.factor
                - TemperatureScale.KELVIN.shift + scale.shift;
    }
}
