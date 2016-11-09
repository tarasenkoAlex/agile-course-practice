package ru.unn.agile.TemperatureConverter.Model;

public class TemperatureConverter {
    public double convert(double degrees, TemperatureScale sourceScale, TemperatureScale destinationScale) {
        double sourceDegrees = degrees / sourceScale.factor - sourceScale.shift;

        return sourceDegrees * destinationScale.factor + destinationScale.shift;
    }
}
