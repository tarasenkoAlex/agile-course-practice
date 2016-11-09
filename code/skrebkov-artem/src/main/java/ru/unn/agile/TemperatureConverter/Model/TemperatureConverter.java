package ru.unn.agile.TemperatureConverter.Model;

public class TemperatureConverter {

    public double fromCelsius(double celsius, TemperatureScale scale) {
        return celsius * scale.factor + scale.shift;
    }
}
