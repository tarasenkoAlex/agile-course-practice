package ru.unn.agile.PositionalNotation;

import org.junit.Test;
import static org.junit.Assert.*;

public class ToDecimalConverterTest {
    @Test
    public void convert0FromBinaryToDecimal() {
        StringBuilder binary = new StringBuilder("0");
        int decimal = new ToDecimalConverter(binary).convertBinaryToDecimal();
        assertEquals(decimal, 0);
    }
    @Test
    public void convert10FromBinaryToDecimal() {
        StringBuilder binary = new StringBuilder("10");
        int decimal = new ToDecimalConverter(binary).convertBinaryToDecimal();
        assertEquals(decimal, 2);
    }
    @Test
    public void convert1101FromBinaryToDecimal() {
        StringBuilder binary = new StringBuilder("1101");
        int decimal = new ToDecimalConverter(binary).convertBinaryToDecimal();
        assertEquals(decimal, 13);
    }
    @Test
    public void convert0FromOctalToDecimal() {
        StringBuilder octal = new StringBuilder("0");
        int decimal = new ToDecimalConverter(octal).convertOctalToDecimal();
        assertEquals(decimal, 0);
    }
    @Test
    public void convert3FromOctalToDecimal() {
        StringBuilder octal = new StringBuilder("3");
        int decimal = new ToDecimalConverter(octal).convertOctalToDecimal();
        assertEquals(decimal, 3);
    }
    @Test
    public void convert17FromOctalToDecimal() {
        StringBuilder octal = new StringBuilder("17");
        int decimal = new ToDecimalConverter(octal).convertOctalToDecimal();
        assertEquals(decimal, 15);
    }
    @Test
    public void convert25FromOctalToDecimal() {
        StringBuilder octal = new StringBuilder("25");
        int decimal = new ToDecimalConverter(octal).convertOctalToDecimal();
        assertEquals(decimal, 21);
    }
    @Test
    public void converter0FromHexToDecimal() {
        StringBuilder hex = new StringBuilder("0");
        int decimal = new ToDecimalConverter(hex).convertHexToDecimal();
        assertEquals(decimal, 0);
    }
    @Test
    public void converterAFromHexToDecimal() {
        StringBuilder hex = new StringBuilder("a");
        int decimal = new ToDecimalConverter(hex).convertHexToDecimal();
        assertEquals(decimal, 10);
    }
    @Test
    public void converter1bFromHexToDecimal() {
        StringBuilder hex = new StringBuilder("1b");
        int decimal = new ToDecimalConverter(hex).convertHexToDecimal();
        assertEquals(decimal, 27);
    }
}
