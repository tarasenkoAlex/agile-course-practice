package ru.unn.agile.TemperatureConverter.Model;

public final class TemperatureConverter {
    public static double convert(final double degrees,
                                 final TemperatureScale sourceScale,
                                 final TemperatureScale destinationScale) {
        double sourceDegrees = (degrees - sourceScale.getCelsiusShift()) / sourceScale.getFactor();

        return sourceDegrees * destinationScale.getFactor() + destinationScale.getCelsiusShift();
    }

    private TemperatureConverter() {
    }
}
