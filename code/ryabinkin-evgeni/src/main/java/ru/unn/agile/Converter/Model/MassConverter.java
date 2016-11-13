package ru.unn.agile.Converter.Model;
import static ru.unn.agile.Converter.Model.Constants.*;

public final class MassConverter {
    private static void isNegative(final double kilogram) {
        if (kilogram < 0) {
            throw new IllegalArgumentException("Kilogram must be positive");
        }
    }

    public static double convertToGram(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_GRAM_MULTIPLIER;
    }

    public static double convertToTon(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_TON_MULTIPLIER;
    }

    public static double convertToCentner(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_CENTNER_MULTIPLIER;
    }

    public static double convertToPound(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_POUND_MULTIPLIER;
    }

    public static double convertToMilligram(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_MILLIGRAM_MULTIPLIER;
    }

    public static double convertToMicrogram(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_MICROGRAM_MULTIPLIER;
    }

    private MassConverter() {
    }
}
