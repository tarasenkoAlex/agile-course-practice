package ru.unn.agile.MultisystemCalculator.Model;

import java.util.Map;

/**
 * Created by Alexander on 07.11.2016.
 */
public final class NumberParser {
    private static final int MIN_NUMBER_ARG_LENGTH = 3;
    private static final int MIN_REPRESENTATION_LENGTH = 1;
    private static final int MAX_REPRESENTATION_LENGTH = 31;

    private NumberParser() {
    }

    public static int parseNumber(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Number representation can't be empty");
        }
        if (s.length() < MIN_NUMBER_ARG_LENGTH) {
            throw new IllegalArgumentException("Illegal number argument. "
                    + "Please, follow convention of arguments representation:"
                    + " 0b[-]{0,1} | 0o[-]{0-7} | 0x[-]{0-9,A,B,C,D,E,F}");
        }
        switch (s.substring(0, 2)) {
            case "0b":
                return parseBin(s.substring(2));

            case "0o":
                return parseOct(s.substring(2));

            case "0x":
                return parseHex(s.substring(2));

            default:
                throw new IllegalArgumentException("Illegal number argument. "
                        + "Please, follow convention of arguments representation:"
                        + " 0b[-]{0,1} | 0o[-]{0-7} | 0x[-]{0-9,A,B,C,D,E,F}");
        }
    }

    private static int parseHex(final String hex) {
        return parseBin(convertHexToBin(hex));
    }

    private static String convertHexToBin(final String hex) {
        return toBin(hex, NumeralSystemsData.HEX_TO_BINARY_MAPPING);
    }

    private static String toBin(final String representation, final Map toBinaryMapping) {
        StringBuilder binaryRepresentation = new StringBuilder();
        char[] chars = representation.toCharArray();
        int counter = 0;
        if (chars[0] == '-') {
            counter = 1;
            binaryRepresentation.append('-');
        }
        for (; counter < chars.length; counter++) {
            if (toBinaryMapping.containsKey(new Character(chars[counter]))) {
                binaryRepresentation.append(toBinaryMapping.get(new Character(chars[counter])));
            } else {
                throw new IllegalArgumentException("Illegal number argument. "
                        + "Please, follow convention of arguments representation:"
                        + " 0b[-]{0,1} | 0o[-]{0-7} | 0x[-]{0-9,A,B,C,D,E,F}");
            }
        }
        return binaryRepresentation.toString();
    }

    private static int parseOct(final String oct) {
        return parseBin(convertOctToBin(oct));
    }

    private static String convertOctToBin(final String oct) {
        return toBin(oct, NumeralSystemsData.OCT_TO_BINARY_MAPPING);
    }


    private static int parseBin(final String binary) {
        int signum = 1;
        String representation = binary;
        char[] digits;
        if (binary.charAt(0) == '-') {
            signum = -1;
            representation = representation.substring(1);
        }
        validateBin(representation);
        if (representation.indexOf('1') < 0) {
            representation = "0";
        } else {
            representation = representation.substring(representation.indexOf('1'));
        }
        digits = representation.toCharArray();
        if (digits.length > MAX_REPRESENTATION_LENGTH) {
            throw new IllegalArgumentException("Absolute value of argument " + binary + " is too "
                    + "big");
        }
        if (digits.length < MIN_REPRESENTATION_LENGTH) {
            throw new IllegalArgumentException("Arguments can't be empty");
        }
        return signum * convertBinToDecimal(representation);
    }

    private static void validateBin(final String binary) {
        char[] chars = binary.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '1' && chars[i] != '0') {
                throw new IllegalArgumentException("Illegal number argument. "
                        + "Please, follow convention of arguments representation:"
                        + " 0b[-]{0,1} | 0o[-]{0-7} | 0x[-]{0-9,A,B,C,D,E,F}");
            }
        }
    }

    private static byte[] getBits(final String binary) {
        byte[] output = new byte[binary.length()];
        char[] binaryDigits = binary.toCharArray();
        for (int i = 0; i < binaryDigits.length; i++) {
            if (binaryDigits[i] == '1') {
                output[i] = 1;
            } else {
                output[i] = 0;
            }
        }
        return output;
    }

    private static int convertBinToDecimal(final String binary) {
        byte[] binaryDigits = getBits(binary);
        int result = 0;
        int bit = 1;
        for (int i = binaryDigits.length - 1; i >= 0; i--) {
            result += bit * binaryDigits[i];
            bit = 1 << binaryDigits.length - i;
        }
        return result;
    }
}
