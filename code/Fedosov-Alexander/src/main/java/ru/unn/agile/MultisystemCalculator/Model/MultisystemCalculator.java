package ru.unn.agile.MultisystemCalculator.Model;

/**
 * Created by Alexander on 08.11.2016.
 */
public final class MultisystemCalculator {
    private static char[] binaryMapping = {'0', '1'};
    private static char[] octalMapping = {'0', '1', '2', '3', '4', '5', '6', '7'};
    private static char[] hexadecimalMapping = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int OCT_BASE = 8;
    private static final int HEX_BASE = 16;

    private MultisystemCalculator() {
    }

    private static int add(final String first, final String second) {
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

    private static int subtract(final String first, final String second) {
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

    private static int multiply(final String first, final String second) {
        int frst = NumberParser.parseNumber(first);
        int scnd = NumberParser.parseNumber(second);
        if ((frst > 0 && scnd < 0 || frst < 0 && scnd > 0) && (frst < -Integer.MAX_VALUE / scnd
                || scnd < -Integer.MAX_VALUE / frst)) {
            throw new IllegalArgumentException("Multiplication leads to Integer overflow");
        }
        if ((frst > 0 && scnd > 0 || frst < 0 && scnd < 0) && (frst > Integer.MAX_VALUE / scnd
                || scnd > Integer.MAX_VALUE / frst)) {
            throw new IllegalArgumentException("Multiplication leads to Integer overflow");
        }
        return frst * scnd;
    }

    private static int divide(final String first, final String second) {
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
        return "0b" + NumberConverter.decimalToSystem(add(first, second), 2, binaryMapping);
    }

    public static String subB(final String first, final String second) {
        return "0b" + NumberConverter.decimalToSystem(subtract(first, second), 2, binaryMapping);
    }

    public static String mulB(final String first, final String second) {
        return "0b" + NumberConverter.decimalToSystem(multiply(first, second), 2, binaryMapping);
    }

    public static String divB(final String first, final String second) {
        return "0b" + NumberConverter.decimalToSystem(divide(first, second), 2, binaryMapping);
    }

    public static String addO(final String first, final String second) {
        return "0o" + NumberConverter.decimalToSystem(add(first, second), OCT_BASE, octalMapping);
    }

    public static String subO(final String first, final String second) {
        return "0o" + NumberConverter.decimalToSystem(subtract(first, second), OCT_BASE,
                octalMapping);
    }

    public static String mulO(final String first, final String second) {
        return "0o" + NumberConverter.decimalToSystem(multiply(first, second), OCT_BASE,
                octalMapping);
    }

    public static String divO(final String first, final String second) {
        return "0o" + NumberConverter.decimalToSystem(divide(first, second), OCT_BASE,
                octalMapping);
    }

    public static String addH(final String first, final String second) {
        return "0x" + NumberConverter.decimalToSystem(add(first, second), HEX_BASE,
                hexadecimalMapping);
    }

    public static String subH(final String first, final String second) {
        return "0x" + NumberConverter.decimalToSystem(subtract(first, second), HEX_BASE,
                hexadecimalMapping);
    }

    public static String mulH(final String first, final String second) {
        return "0x" + NumberConverter.decimalToSystem(multiply(first, second), HEX_BASE,
                hexadecimalMapping);
    }

    public static String divH(final String first, final String second) {
        return "0x" + NumberConverter.decimalToSystem(divide(first, second), HEX_BASE,
                hexadecimalMapping);
    }

}
