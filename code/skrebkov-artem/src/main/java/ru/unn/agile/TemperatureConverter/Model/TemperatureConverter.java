package ru.unn.agile.TemperatureConverter.Model;

public final class TemperatureConverter {
    public static double convert(final double degrees,
                                 final TemperatureScale sourceScale,
                                 final TemperatureScale destinationScale) {
        if (isLowerThanAbsoluteZero(degrees, sourceScale)) {
            String message = "Cannot convert temperature lower than absolute zero!";
            throw new IllegalArgumentException(message);
        }
        double source = (degrees - sourceScale.getCelsiusShift()) / sourceScale.getFactor();

        return source * destinationScale.getFactor() + destinationScale.getCelsiusShift();
    }

    private TemperatureConverter() {
    }

    private static boolean isLowerThanAbsoluteZero(final double degrees,
                                                   final TemperatureScale scale) {
        switch (scale) {
            case CELSIUS:
                if (degrees < AbsoluteZero.CELSIUS) {
                    return true;
                }
                break;
            case KELVIN:
                if (degrees < AbsoluteZero.KELVIN) {
                    return true;
                }
                break;
            case FAHRENHEIT:
                if (degrees < AbsoluteZero.FAHRENHEIT) {
                    return true;
                }
            case NEWTON:
                if (degrees < AbsoluteZero.NEWTON) {
                    return true;
                }
            default:
                return false;
        }

        return false;
    }
}

