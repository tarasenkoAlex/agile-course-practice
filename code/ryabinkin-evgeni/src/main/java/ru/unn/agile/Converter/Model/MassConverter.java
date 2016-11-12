package ru.unn.agile.Converter.Model;
import static ru.unn.agile.Converter.Model.Constants.*;

public class MassConverter {
    private void isNegative(final double kilogram) {
        if (kilogram < 0) {
            throw new IllegalArgumentException("Kilogram must be positive");
        }
    }

    public double convertToGram(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_GRAM_MULTIPLIER;
    }

    public double convertToTon(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_TON_MULTIPLIER;
    }

    public double convertToCentner(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_CENTNER_MULTIPLIER;
    }

    public double convertToPound(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_POUND_MULTIPLIER;
    }

    public double convertToMilligram(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_MILLIGRAM_MULTIPLIER;
    }

    public double convertToMicrogram(final double kilogram) {
        isNegative(kilogram);
        return kilogram * TO_MICROGRAM_MULTIPLIER;
    }
}
