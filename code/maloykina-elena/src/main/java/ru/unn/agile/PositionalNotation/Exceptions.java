package ru.unn.agile.PositionalNotation;

public class Exceptions {
    public void checkBinaryValue(final StringBuilder binary) {
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) > '1' || binary.charAt(i) < '0') {
                throw new IllegalArgumentException("Incorrect binary number");
            }
        }
    }
    public void checkOctalValue(final StringBuilder octal) {
        for (int i = 0; i < octal.length(); i++) {
            if (octal.charAt(i) > '7' || octal.charAt(i) < '0') {
                throw new IllegalArgumentException("Incorrect octal number");
            }
        }
    }
    public void checkDecimalValue(final int decimal) {
        if (decimal < 0) {
            throw new IllegalArgumentException("Enter positive number");
        }
    }
    public void checkHexValue(final StringBuilder hex) {
        for (int i = 0; i < hex.length(); i++) {
            if (!(hex.charAt(i) <= '9' && hex.charAt(i) >= '0'
                    || hex.charAt(i) <= 'f' && hex.charAt(i) >= 'a')) {
                throw new IllegalArgumentException("Incorrect hex number");
            }
        }
    }
}
