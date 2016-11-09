package ru.unn.agile.TemperatureConverter.Model;

public enum TemperatureScale {
    CELSIUS(1.0, 0.0),
    KELVIN(1.0, 273.15),
    FAHRENHEIT(1.8, 32.0),
    NEWTON(0.33, 0.0);

    private final double factor;
    private final double shift;

    private TemperatureScale(final double factor, final double shift) {
        this.factor = factor;
        this.shift = shift;
    }

    public double getFactor() {
        return factor;
    }
    public double getShift() {
        return shift;
    }
}
