package ru.unn.agile.PositionalNotation;

import org.junit.Test;
import static org.junit.Assert.*;

public class FromDecimalConverterTest {
    @Test
    public void convert0ToBinary() {
        StringBuilder binary = new FromDecimalConverter(0).convertToBinary();
        assertEquals(binary.toString(), "0");
    }
    @Test
    public void convert10ToBinary() {
        StringBuilder binary = new FromDecimalConverter(10).convertToBinary();
        assertEquals(binary.toString(), "1010");
    }
    @Test
    public void convert19ToBinary() {
        StringBuilder binary = new FromDecimalConverter(19).convertToBinary();
        assertEquals(binary.toString(), "10011");
    }
    @Test
    public void convert0ToOctal() {
        StringBuilder octal = new FromDecimalConverter(0).convertToOctal();
        assertEquals(octal.toString(), "0");
    }
    @Test
    public void convert7ToOctal() {
        StringBuilder octal = new FromDecimalConverter(6).convertToOctal();
        assertEquals(octal.toString(), "6");
    }
    @Test
    public void convert9ToOctal() {
        StringBuilder octal = new FromDecimalConverter(9).convertToOctal();
        assertEquals(octal.toString(), "11");
    }
    @Test
    public void convert26ToOctal() {
        StringBuilder octal = new FromDecimalConverter(26).convertToOctal();
        assertEquals(octal.toString(), "32");
    }
    @Test
    public void convert0ToHex() {
        StringBuilder hex = new FromDecimalConverter(0).convertToHex();
        assertEquals(hex.toString(), "0");
    }
    @Test
    public void convert13ToHex() {
        StringBuilder hex = new FromDecimalConverter(13).convertToHex();
        assertEquals(hex.toString(), "d");
    }
    @Test
    public void convert59ToHex() {
        StringBuilder hex = new FromDecimalConverter(59).convertToHex();
        assertEquals(hex.toString(), "3b");
    }
}
