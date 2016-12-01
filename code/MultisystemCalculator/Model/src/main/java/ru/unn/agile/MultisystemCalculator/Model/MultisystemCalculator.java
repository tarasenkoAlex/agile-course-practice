package ru.unn.agile.MultisystemCalculator.Model;

/**
 * Created by Alexander on 08.11.2016.
 */
public final class MultisystemCalculator {
    private static final int OCT_BASE = 8;
    private static final int HEX_BASE = 16;

    private MultisystemCalculator() {
    }

    public static int add(final String first, final String second) {
        int frst = NumberParser.parseNumber(first);
        int scnd = NumberParser.parseNumber(second);
        if (scnd > 0 && frst > 0 && (scnd > Integer.MAX_VALUE - frst || frst > Integer.MAX_VALUE
                - scnd)) {
            throw new IllegalArgumentException("Addition leads to Integer overflow");
        }
        if (scnd < 0 && frst < 0 && (scnd + Integer.MAX_VALUE < -frst || frst + Integer
                .MAX_VALUE < -scnd)) {
            throw new IllegalArgumentException("Addition leads to Integer overflow");
        }
        return frst + scnd;
    }

    public static int subtract(final String first, final String second) {
        int frst = NumberParser.parseNumber(first);
        int scnd = NumberParser.parseNumber(second);
        if (frst > 0 && scnd < 0 && (frst > Integer.MAX_VALUE + scnd || -scnd > Integer
                .MAX_VALUE - frst)) {
            throw new IllegalArgumentException("Subtraction leads to Integer overflow");
        }
        if (frst < 0 && scnd > 0 && (frst < -Integer.MAX_VALUE + scnd || scnd > Integer.MAX_VALUE
                + frst)) {
            throw new IllegalArgumentException("Subtraction leads to Integer overflow");
        }
        return frst - scnd;
    }

    public static int multiply(final String first, final String second) {
        int frst = NumberParser.parseNumber(first);
        int scnd = NumberParser.parseNumber(second);
        if ((frst > 0 && scnd < 0 || frst < 0 && scnd > 0) && (frst < -Integer.MAX_VALUE / scnd
                || scnd < -Integer.MAX_VALUE / frst)) {
            throw new IllegalArgumentException("Multiplication leads to Integer overflow");
        }
        if (frst > 0 && scnd > 0 && (frst > Integer.MAX_VALUE / scnd
                || scnd > Integer.MAX_VALUE / frst)) {
            throw new IllegalArgumentException("Multiplication leads to Integer overflow");
        }
        if (frst < 0 && scnd < 0 & (frst < Integer.MAX_VALUE / scnd
                || scnd < Integer.MAX_VALUE / frst)) {
            throw new IllegalArgumentException("Multiplication leads to Integer overflow");
        }
        return frst * scnd;
    }

    public static int divide(final String first, final String second) {
        int frst = NumberParser.parseNumber(first);
        int scnd = NumberParser.parseNumber(second);
        if (scnd == 0) {
            throw new IllegalArgumentException("Division by zero is forbidden");
        }
        return Math.round(((float) frst) / ((float) scnd));
    }

    public static String addD(final String first, final String second) {
        return "0d" + add(first, second);
    }

    public static String subD(final String first, final String second) {
        return "0d" + subtract(first, second);
    }

    public static String mulD(final String first, final String second) {
        return "0d" + multiply(first, second);
    }

    public static String divD(final String first, final String second) {
        return "0d" + divide(first, second);
    }

    public static String addB(final String first, final String second) {
        return "0b" + NumberConverter.decimalToSystem(add(first, second), 2, NumeralSystemsData
                .BINARY_CHARACTERS);
    }

    public static String subB(final String first, final String second) {
        return "0b" + NumberConverter.decimalToSystem(subtract(first, second), 2,
                NumeralSystemsData.BINARY_CHARACTERS);
    }

    public static String mulB(final String first, final String second) {
        return "0b" + NumberConverter.decimalToSystem(multiply(first, second), 2,
                NumeralSystemsData.BINARY_CHARACTERS);
    }

    public static String divB(final String first, final String second) {
        return "0b" + NumberConverter.decimalToSystem(divide(first, second), 2,
                NumeralSystemsData.BINARY_CHARACTERS);
    }

    public static String addO(final String first, final String second) {
        return "0o" + NumberConverter.decimalToSystem(add(first, second), OCT_BASE,
                NumeralSystemsData.OCTAL_CHARACTERS);
    }

    public static String subO(final String first, final String second) {
        return "0o" + NumberConverter.decimalToSystem(subtract(first, second), OCT_BASE,
                NumeralSystemsData.OCTAL_CHARACTERS);
    }

    public static String mulO(final String first, final String second) {
        return "0o" + NumberConverter.decimalToSystem(multiply(first, second), OCT_BASE,
                NumeralSystemsData.OCTAL_CHARACTERS);
    }

    public static String divO(final String first, final String second) {
        return "0o" + NumberConverter.decimalToSystem(divide(first, second), OCT_BASE,
                NumeralSystemsData.OCTAL_CHARACTERS);
    }

    public static String addH(final String first, final String second) {
        return "0x" + NumberConverter.decimalToSystem(add(first, second), HEX_BASE,
                NumeralSystemsData.HEXADECIMAL_CHARACTERS);
    }

    public static String subH(final String first, final String second) {
        return "0x" + NumberConverter.decimalToSystem(subtract(first, second), HEX_BASE,
                NumeralSystemsData.HEXADECIMAL_CHARACTERS);
    }

    public static String mulH(final String first, final String second) {
        return "0x" + NumberConverter.decimalToSystem(multiply(first, second), HEX_BASE,
                NumeralSystemsData.HEXADECIMAL_CHARACTERS);
    }

    public static String divH(final String first, final String second) {
        return "0x" + NumberConverter.decimalToSystem(divide(first, second), HEX_BASE,
                NumeralSystemsData.HEXADECIMAL_CHARACTERS);
    }
}
