package ru.unn.agile.PositionalNotation;

import org.junit.Test;
import static org.junit.Assert.*;

public class ToDecimalConverterTest {
    @Test
    public void convert0FromBinaryToDecimal() {
        String binary = "0";
        int decimal = ToDecimalConverter.convertBinaryToDecimal(binary);
        assertEquals(decimal, 0);
    }
    @Test
    public void convert10FromBinaryToDecimal() {
        String binary = "10";
        int decimal = ToDecimalConverter.convertBinaryToDecimal(binary);
        assertEquals(decimal, 2);
    }
    @Test
    public void convert1101FromBinaryToDecimal() {
        String binary = "1101";
        int decimal = ToDecimalConverter.convertBinaryToDecimal(binary);
        assertEquals(decimal, 13);
    }
    @Test
    public void convert0FromOctalToDecimal() {
        String octal = "0";
        int decimal = ToDecimalConverter.convertOctalToDecimal(octal);
        assertEquals(decimal, 0);
    }
    @Test
    public void convert3FromOctalToDecimal() {
        String octal = "3";
        int decimal = ToDecimalConverter.convertOctalToDecimal(octal);
        assertEquals(decimal, 3);
    }
    @Test
    public void convert17FromOctalToDecimal() {
        String octal = "17";
        int decimal = ToDecimalConverter.convertOctalToDecimal(octal);
        assertEquals(decimal, 15);
    }
    @Test
    public void convert25FromOctalToDecimal() {
        String octal = "25";
        int decimal = ToDecimalConverter.convertOctalToDecimal(octal);
        assertEquals(decimal, 21);
    }
    @Test
    public void converter0FromHexToDecimal() {
        String hex = "0";
        int decimal = ToDecimalConverter.convertHexToDecimal(hex);
        assertEquals(decimal, 0);
    }
    @Test
    public void converterAFromHexToDecimal() {
        String hex = "a";
        int decimal = ToDecimalConverter.convertHexToDecimal(hex);
        assertEquals(decimal, 10);
    }
    @Test
    public void converterFFromHexToDecimal() {
        String hex = "F";
        int decimal = ToDecimalConverter.convertHexToDecimal(hex);
        assertEquals(decimal, 15);
    }
    @Test
    public void converter1bFromHexToDecimal() {
        String hex = "1b";
        int decimal = ToDecimalConverter.convertHexToDecimal(hex);
        assertEquals(decimal, 27);
    }
    @Test
    public void converter1BFromHexToDecimal() {
        String hex = "1B";
        int decimal = ToDecimalConverter.convertHexToDecimal(hex);
        assertEquals(decimal, 27);
    }
}
