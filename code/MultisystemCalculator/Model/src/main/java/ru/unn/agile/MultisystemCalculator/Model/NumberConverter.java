package ru.unn.agile.MultisystemCalculator.Model;

import java.util.List;

/**
 * Created by Alexander on 09.11.2016.
 */
public final class NumberConverter {
    private NumberConverter() {
    }

    public static String decimalToSystem(final int decimal, final int base, final List
            symbolMappings) {
        if (decimal == 0) {
            return "0";
        }
        if (base < 2) {
            throw new IllegalArgumentException("Base of numeral system should be greater than 1");
        }
        if (symbolMappings.size() < base) {
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
            representation.append(symbolMappings.get(number % base));
            number = number / base;
        }
        if (isNegative) {
            representation.append('-');
        }
        return representation.reverse().toString();
    }
}
