package ru.unn.agile.MassConverter.Model;
import static ru.unn.agile.MassConverter.Model.Multipliers.*;

public final class MassConverter {
    public enum ConversionSystem {
        GRAM("Gram") {
            @Override
            public double convertTo(final double kilogram) {
                return convertToSystem(GRAM_MULTIPLIER, kilogram);
            }

            @Override
            public double convertFrom(final double mass) {
                return convertFromSystem(GRAM_MULTIPLIER, mass);
            }
        },
        TONNE("Tonne") {
            @Override
            public double convertTo(final double kilogram) {
                return convertToSystem(TONNE_MULTIPLIER, kilogram);
            }

            @Override
            public double convertFrom(final double mass) {
                return convertFromSystem(TONNE_MULTIPLIER, mass);
            }
        },
        POUND("Pound") {
            @Override
            public double convertTo(final double kilogram) {
                return convertToSystem(POUND_MULTIPLIER, kilogram);
            }

            @Override
            public double convertFrom(final double mass) {
                return convertFromSystem(POUND_MULTIPLIER, mass);
            }
        },
        CENTNER("Centner") {
            @Override
            public double convertTo(final double kilogram) {
                return convertToSystem(CENTNER_MULTIPLIER, kilogram);
            }

            @Override
            public double convertFrom(final double mass) {
                return convertFromSystem(CENTNER_MULTIPLIER, mass);
            }
        },
        MILLIGRAM("Milligram") {
            @Override
            public double convertTo(final double kilogram) {
                return convertToSystem(MILLIGRAM_MULTIPLIER, kilogram);
            }

            @Override
            public double convertFrom(final double mass) {
                return convertFromSystem(MILLIGRAM_MULTIPLIER, mass);
            }
        },
        MICROGRAM("Microgram") {
            @Override
            public double convertTo(final double kilogram) {
                return convertToSystem(MICROGRAM_MULTIPLIER, kilogram);
            }

            @Override
            public double convertFrom(final double mass) {
                return convertFromSystem(MICROGRAM_MULTIPLIER, mass);
            }
        },
        KILOGRAM("Kilogram") {
            @Override
            public double convertTo(final double kilogram) {
                return kilogram;
            }

            @Override
            public double convertFrom(final double mass) {
                return mass;
            }
        };

        private String name;
        ConversionSystem(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }

        public abstract double convertTo(double kilogram);
        public abstract double convertFrom(double mass);
    }

    static double convertToSystem(final double multiplier, final double kilogram) {
        validateNumber(kilogram);
        return kilogram * multiplier;
    }

    static double convertFromSystem(final double multiplier, final double mass) {
        validateNumber(mass);
        return mass / multiplier;
    }

    private static void validateNumber(final double kilogram) {
        if (kilogram < 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
    }

    private MassConverter() {
    }
}
