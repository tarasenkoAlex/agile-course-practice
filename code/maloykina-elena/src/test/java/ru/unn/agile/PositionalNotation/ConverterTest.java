package ru.unn.agile.PositionalNotation;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {
    @Test
    public void convert101FromBinaryToDecimal() {
        String binary = "101";
        int decimal = Converter.binaryToDecimal(binary);
        assertEquals(decimal, 5);
    }
    @Test
    public void convert0FromBinaryToOctal() {
        String binary = "0";
        String octal = Converter.binaryToOctal(binary);
        assertEquals(octal, "0");
    }
    @Test
    public void convert1111FromBinaryToOctal() {
        String binary = "1111";
        String octal = Converter.binaryToOctal(binary);
        assertEquals(octal, "17");
    }
    @Test
    public void convert10101FromBinaryToOctal() {
        String binary = "10101";
        String octal = Converter.binaryToOctal(binary);
        assertEquals(octal, "25");
    }
    @Test
    public void convert0FromBinaryToHex() {
        String binary = "0";
        String hex = Converter.binaryToHex(binary);
        assertEquals(hex, "0");
    }
    @Test
    public void convert111FromBinaryToHex() {
        String binary = "111";
        String hex = Converter.binaryToHex(binary);
        assertEquals(hex, "7");
    }
    @Test
    public void convert11101FromBinaryToHex() {
        String binary = "11101";
        String hex = Converter.binaryToHex(binary);
        assertEquals(hex, "1d");
    }
    @Test
    public void convert3FromOctalToBinary() {
        String octal = "3";
        String binary = Converter.octalToBinary(octal);
        assertEquals(binary, "11");
    }
    @Test
    public void convert11FromOctalToBinary() {
        String octal = "11";
        String binary = Converter.octalToBinary(octal);
        assertEquals(binary, "1001");
    }
    @Test
    public void convert37FromOctalToBinary() {
        String octal = "37";
        String binary = Converter.octalToBinary(octal);
        assertEquals(binary, "11111");
    }
    @Test
    public void convert25FromOctalToDecimal() {
        String octal = "25";
        int decimal = Converter.octalToDecimal(octal);
        assertEquals(decimal, 21);
    }
    @Test
    public void convert3FromOctalToHex() {
        String octal = "3";
        String hex = Converter.octalToHex(octal);
        assertEquals(hex, "3");
    }
    @Test
    public void convert17FromOctalToHex() {
        String octal = "17";
        String hex = Converter.octalToHex(octal);
        assertEquals(hex, "f");
    }
    @Test
    public void convert32FromOctalToHex() {
        String octal = "32";
        String hex = Converter.octalToHex(octal);
        assertEquals(hex, "1a");
    }
    @Test
    public void convert4FromDecimalToBinary() {
        String binary = Converter.decimalToBinary(4);
        assertEquals(binary, "100");
    }
    @Test
    public void convert5FromDecimalToOctal() {
        String octal = Converter.decimalToOctal(5);
        assertEquals(octal, "5");
    }
    @Test
    public void convert6FromDecimalToHex() {
        String hex = Converter.decimalToHex(6);
        assertEquals(hex, "6");
    }
    @Test
    public void convert7FromHexBinary() {
        String hex = "7";
        String binary = Converter.hexToBinary(hex);
        assertEquals(binary, "111");
    }
    @Test
    public void convert14aFromHexToBinary() {
        String hex = "14a";
        String binary = Converter.hexToBinary(hex);
        assertEquals(binary, "101001010");
    }
    @Test
    public void convert13f40FromHexToBinary() {
        String hex = "13f40";
        String binary = Converter.hexToBinary(hex);
        assertEquals(binary, "10011111101000000");
    }
    @Test
    public void convert8FromHexToOctal() {
        String hex = "8";
        String octal = Converter.hexToOctal(hex);
        assertEquals(octal, "10");
    }
    @Test
    public void convert14aFromHexToOctal() {
        String hex = "14a";
        String octal = Converter.hexToOctal(hex);
        assertEquals(octal, "512");
    }
    @Test
    public void convert19FromHexToOctal() {
        String hex = "19";
        String octal = Converter.hexToOctal(hex);
        assertEquals(octal, "31");
    }
    @Test
    public void converter1bFromHexToDecimal() {
        String hex = "1b";
        int decimal = Converter.hexToDecimal(hex);
        assertEquals(decimal, 27);
    }
}
