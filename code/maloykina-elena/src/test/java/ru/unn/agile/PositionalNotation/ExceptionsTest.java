package ru.unn.agile.PositionalNotation;

import org.junit.Test;

public class ExceptionsTest {
    @Test(expected = IllegalArgumentException.class)
    public void checkBinary1201() {
        StringBuilder binary = new StringBuilder("1201");
        new Exceptions().checkBinaryValue(binary);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkBinary1a01() {
        StringBuilder binary = new StringBuilder("1a01");
        new Exceptions().checkBinaryValue(binary);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkOctal19() {
        StringBuilder octal = new StringBuilder("19");
        new Exceptions().checkOctalValue(octal);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkOctal10a() {
        StringBuilder octal = new StringBuilder("10a");
        new Exceptions().checkOctalValue(octal);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkDecimal() {
        new Exceptions().checkDecimalValue(-2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkHex() {
        StringBuilder hex = new StringBuilder("1r");
        new Exceptions().checkHexValue(hex);
    }
}
