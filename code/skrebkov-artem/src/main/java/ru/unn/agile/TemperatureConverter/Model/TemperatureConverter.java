package ru.unn.agile.TemperatureConverter.Model;

public class TemperatureConverter {
    public double compute(final double degrees,
                          final TemperatureScale sourceScale,
                          final TemperatureScale destinationScale) {
        double sourceDegrees = (degrees - sourceScale.getShift()) / sourceScale.getFactor();

        return sourceDegrees * destinationScale.getFactor() + destinationScale.getShift();
    }
}
