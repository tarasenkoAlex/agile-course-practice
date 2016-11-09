package ru.unn.agile.PositionalNotation;

import org.junit.Test;
import static org.junit.Assert.*;

public class FromDecimalConverterTest {
    @Test
    public void convert0ToBinary() {
        String binary = FromDecimalConverter.convertToBinary(0);
        assertEquals(binary, "0");
    }
    @Test
    public void convert10ToBinary() {
        String binary = FromDecimalConverter.convertToBinary(10);
        assertEquals(binary, "1010");
    }
    @Test
    public void convert19ToBinary() {
        String binary = FromDecimalConverter.convertToBinary(19);
        assertEquals(binary, "10011");
    }
    @Test
    public void convert0ToOctal() {
        String octal = FromDecimalConverter.convertToOctal(0);
        assertEquals(octal, "0");
    }
    @Test
    public void convert7ToOctal() {
        String octal = FromDecimalConverter.convertToOctal(6);
        assertEquals(octal, "6");
    }
    @Test
    public void convert9ToOctal() {
        String octal = FromDecimalConverter.convertToOctal(9);
        assertEquals(octal, "11");
    }
    @Test
    public void convert26ToOctal() {
        String octal = FromDecimalConverter.convertToOctal(26);
        assertEquals(octal, "32");
    }
    @Test
    public void convert0ToHex() {
        String hex = FromDecimalConverter.convertToHex(0);
        assertEquals(hex, "0");
    }
    @Test
    public void convert13ToHex() {
        String hex = FromDecimalConverter.convertToHex(13);
        assertEquals(hex, "d");
    }
    @Test
    public void convert59ToHex() {
        String hex = FromDecimalConverter.convertToHex(59);
        assertEquals(hex, "3b");
    }
}
