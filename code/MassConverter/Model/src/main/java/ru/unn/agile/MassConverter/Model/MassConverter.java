package ru.unn.agile.MassConverter.Model;
import static ru.unn.agile.MassConverter.Model.Constants.*;

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

    public enum SystemToConvert {
        POUND("Pound") {
            public double convert(final double kilogram) {
                return convertToPound(kilogram);
            }
        },
        TONNE("Tonne") {
            public double convert(final double kilogram) {
                return convertToTon(kilogram);
            }
        },
        GRAM("Gram") {
            public double convert(final double kilogram) {
                return convertToGram(kilogram);
            }
        },
        MILLIGRAM("Milligram") {
            public double convert(final double kilogram) {
                return convertToMilligram(kilogram);
            }
        },
        MICROGRAM("Microgram") {
            public double convert(final double kilogram) {
                return convertToMicrogram(kilogram);
            }
        },
        CENTNER("Centner") {
            public double convert(final double kilogram) {
                return convertToCentner(kilogram);
            }
        };

        private final String name;
        SystemToConvert(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract double convert(double kilogram);
    }

    private MassConverter() {
    }
}
