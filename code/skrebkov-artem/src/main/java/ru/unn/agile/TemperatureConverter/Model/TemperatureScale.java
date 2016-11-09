package ru.unn.agile.TemperatureConverter.Model;

public enum TemperatureScale {
    CELSIUS(1.0, 0.0),
    KELVIN(1.0, 273.15),
    FAHRENHEIT(9.0 / 5.0, 32.0),
    NEWTON(33.0 / 100.0, 0.0);

    public final double factor;
    public final double shift;

    private TemperatureScale(double factor, double shift) {
        this.factor = factor;
        this.shift = shift;
    }
}