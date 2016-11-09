package ru.unn.agile.PositionalNotation;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {
    @Test
    public void convert101FromBinaryToDecimal() {
        StringBuilder binary = new StringBuilder("101");
        int decimal = new Converter().binaryToDecimal(binary);
        assertEquals(decimal, 5);
    }
    @Test
    public void convert0FromBinaryToOctal() {
        StringBuilder binary = new StringBuilder("0");
        StringBuilder octal = new Converter().binaryToOctal(binary);
        assertEquals(octal.toString(), "0");
    }
    @Test
    public void convert1111FromBinaryToOctal() {
        StringBuilder binary = new StringBuilder("1111");
        StringBuilder octal = new Converter().binaryToOctal(binary);
        assertEquals(octal.toString(), "17");
    }
    @Test
    public void convert10101FromBinaryToOctal() {
        StringBuilder binary = new StringBuilder("10101");
        StringBuilder octal = new Converter().binaryToOctal(binary);
        assertEquals(octal.toString(), "25");
    }
    @Test
    public void convert0FromBinaryToHex() {
        StringBuilder binary = new StringBuilder("0");
        StringBuilder hex = new Converter().binaryToHex(binary);
        assertEquals(hex.toString(), "0");
    }
    @Test
    public void convert111FromBinaryToHex() {
        StringBuilder binary = new StringBuilder("111");
        StringBuilder hex = new Converter().binaryToHex(binary);
        assertEquals(hex.toString(), "7");
    }
    @Test
    public void convert11101FromBinaryToHex() {
        StringBuilder binary = new StringBuilder("11101");
        StringBuilder hex = new Converter().binaryToHex(binary);
        assertEquals(hex.toString(), "1d");
    }
    @Test
    public void convert3FromOctalToBinary() {
        StringBuilder octal = new StringBuilder("3");
        StringBuilder binary = new Converter().octalToBinary(octal);
        assertEquals(binary.toString(), "11");
    }
    @Test
    public void convert11FromOctalToBinary() {
        StringBuilder octal = new StringBuilder("11");
        StringBuilder binary = new Converter().octalToBinary(octal);
        assertEquals(binary.toString(), "1001");
    }
    @Test
    public void convert37FromOctalToBinary() {
        StringBuilder octal = new StringBuilder("37");
        StringBuilder binary = new Converter().octalToBinary(octal);
        assertEquals(binary.toString(), "11111");
    }
    @Test
    public void convert25FromOctalToDecimal() {
        StringBuilder octal = new StringBuilder("25");
        int decimal = new Converter().octalToDecimal(octal);
        assertEquals(decimal, 21);
    }
    @Test
    public void convert3FromOctalToHex() {
        StringBuilder octal = new StringBuilder("3");
        StringBuilder hex = new Converter().octalToHex(octal);
        assertEquals(hex.toString(), "3");
    }
    @Test
    public void convert17FromOctalToHex() {
        StringBuilder octal = new StringBuilder("17");
        StringBuilder hex = new Converter().octalToHex(octal);
        assertEquals(hex.toString(), "f");
    }
    @Test
    public void convert32FromOctalToHex() {
        StringBuilder octal = new StringBuilder("32");
        StringBuilder hex = new Converter().octalToHex(octal);
        assertEquals(hex.toString(), "1a");
    }
    @Test
    public void convert4FromDecimalToBinary() {
        StringBuilder binary = new Converter().decimalToBinary(4);
        assertEquals(binary.toString(), "100");
    }
    @Test
    public void convert5FromDecimalToOctal() {
        StringBuilder octal = new Converter().decimalToOctal(5);
        assertEquals(octal.toString(), "5");
    }
    @Test
    public void convert6FromDecimalToHex() {
        StringBuilder hex = new Converter().decimalToHex(6);
        assertEquals(hex.toString(), "6");
    }
    @Test
    public void convert7FromHexBinary() {
        StringBuilder hex = new StringBuilder("7");
        StringBuilder binary = new Converter().hexToBinary(hex);
        assertEquals(binary.toString(), "111");
    }
    @Test
    public void convert14aFromHexToBinary() {
        StringBuilder hex = new StringBuilder("14a");
        StringBuilder binary = new Converter().hexToBinary(hex);
        assertEquals(binary.toString(), "101001010");
    }
    @Test
    public void convert13f40FromHexToBinary() {
        StringBuilder hex = new StringBuilder("13f40");
        StringBuilder binary = new Converter().hexToBinary(hex);
        assertEquals(binary.toString(), "10011111101000000");
    }
    @Test
    public void convert8FromHexToOctal() {
        StringBuilder hex = new StringBuilder("8");
        StringBuilder octal = new Converter().hexToOctal(hex);
        assertEquals(octal.toString(), "10");
    }
    @Test
    public void convert14aFromHexToOctal() {
        StringBuilder hex = new StringBuilder("14a");
        StringBuilder octal = new Converter().hexToOctal(hex);
        assertEquals(octal.toString(), "512");
    }
    @Test
    public void convert19FromHexToOctal() {
        StringBuilder hex = new StringBuilder("19");
        StringBuilder octal = new Converter().hexToOctal(hex);
        assertEquals(octal.toString(), "31");
    }
    @Test
    public void converter1bFromHexToDecimal() {
        StringBuilder hex = new StringBuilder("1b");
        int decimal = new Converter().hexToDecimal(hex);
        assertEquals(decimal, 27);
    }
}
