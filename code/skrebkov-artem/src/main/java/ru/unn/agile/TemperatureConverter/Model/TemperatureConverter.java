package ru.unn.agile.TemperatureConverter.Model;

public class TemperatureConverter {
    public double compute(double degrees, TemperatureScale sourceScale, TemperatureScale destinationScale) {
        double sourceDegrees = (degrees - sourceScale.shift) / sourceScale.factor;

        return sourceDegrees * destinationScale.factor + destinationScale.shift;
    }
}
