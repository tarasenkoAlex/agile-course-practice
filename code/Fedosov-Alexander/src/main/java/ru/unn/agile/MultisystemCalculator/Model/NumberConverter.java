package ru.unn.agile.MultisystemCalculator.Model;

/**
 * Created by Alexander on 09.11.2016.
 */
public final class NumberConverter {
    private NumberConverter() {
    }

    public static String decimalToSystem(final int decimal, final int system, final char[]
            symbolMappings) {
        if (decimal == 0) {
            return "0";
        }
        if (system < 2) {
            throw new IllegalArgumentException("Base of numeral system should be greater than 1");
        }
        if (symbolMappings.length < system) {
            throw new IllegalArgumentException("Length of symbol mapping array should be equal to"
                    + " numeral system base");
        }
        boolean isNegative = decimal < 0;
        int number = decimal;
        StringBuilder representation = new StringBuilder();
        if (isNegative) {
            number *= -1;
        }
        while (number != 0) {
            representation.append(number % system);
            number = number / system;
        }
        if (isNegative) {
            representation.append('-');
        }
        return representation.reverse().toString();
    }
}
