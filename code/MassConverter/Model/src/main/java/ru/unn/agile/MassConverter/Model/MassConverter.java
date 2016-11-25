package ru.unn.agile.MassConverter.Model;
import static ru.unn.agile.MassConverter.Model.Multipliers.*;

public final class MassConverter {
    public enum SystemToConvert {
        GRAM("Gram") {
            @Override
            public double convert(final double kilogram) {
                return convertToSystem(TO_GRAM_MULTIPLIER, kilogram);
            }
        },
        TONNE("Tonne") {
            @Override
            public double convert(final double kilogram) {
                return convertToSystem(TO_TONNE_MULTIPLIER, kilogram);
            }
        },
        POUND("Pound") {
            @Override
            public double convert(final double kilogram) {
                return convertToSystem(TO_POUND_MULTIPLIER, kilogram);
            }
        },
        CENTNER("Centner") {
            @Override
            public double convert(final double kilogram) {
                return convertToSystem(TO_CENTNER_MULTIPLIER, kilogram);
            }
        },
        MILLIGRAM("Milligram") {
            @Override
            public double convert(final double kilogram) {
                return convertToSystem(TO_MILLIGRAM_MULTIPLIER, kilogram);
            }
        },
        MICROGRAM("Microgram") {
            @Override
            public double convert(final double kilogram) {
                return convertToSystem(TO_MICROGRAM_MULTIPLIER, kilogram);
            }
        };

        private String name;
        SystemToConvert(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }

        public abstract double convert(double kilogram);
    }

    static double convertToSystem(final double multiplier, final double kilogram) {
        validateNumber(kilogram);
        return kilogram * multiplier;
    }

    private static void validateNumber(final double kilogram) {
        if (kilogram < 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
    }

    private MassConverter() {
    }
}
