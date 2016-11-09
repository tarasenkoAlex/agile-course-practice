package ru.unn.agile.TemperatureConverter.Model;

public class TemperatureConverter {

    public double fromCelsius(double celsius, TemperatureScale scale) {
        double sourceValue = celsius / TemperatureScale.CELSIUS.factor - TemperatureScale.CELSIUS.shift;
        return sourceValue * scale.factor + scale.shift;
    }

    public double fromKelvin(double kelvin, TemperatureScale scale) {
        double sourceValue = kelvin / TemperatureScale.KELVIN.factor - TemperatureScale.KELVIN.shift;

        return sourceValue * scale.factor + scale.shift;
    }
}
