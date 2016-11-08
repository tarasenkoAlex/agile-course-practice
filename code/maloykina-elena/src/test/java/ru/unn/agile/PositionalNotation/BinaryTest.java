package ru.unn.agile.PositionalNotation;

import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryTest {
    @Test
    public void canInitialBinary(){
        Binary object = new Binary(new StringBuilder("101"));
        assertNotNull(object);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkInitialData(){
        new Binary(new StringBuilder("1201")).checkBinaryValue();
    }
    @Test
    public void convert0ToDecimal(){
        int decimal = new Binary(new StringBuilder("0")).convertToDecimal();
        assertEquals(decimal, 0);
    }
    @Test
    public void convert10ToDecimal(){
        int decimal = new Binary(new StringBuilder("10")).convertToDecimal();
        assertEquals(decimal, 2);
    }
    @Test
    public void convert1101ToDecimal(){
        int decimal = new Binary(new StringBuilder("1101")).convertToDecimal();
        assertEquals(decimal, 13);
    }
    @Test
    public void convert110ToOctal(){
        StringBuilder octal = new Binary(new StringBuilder("110")).convertToOctal();
        assertEquals(octal.toString(), "6");
    }
    @Test
    public void convert1111ToOctal(){
        StringBuilder octal = new Binary(new StringBuilder("1111")).convertToOctal();
        assertEquals(octal.toString(), "17");
    }
    @Test
    public void convert10101ToOctal(){
        StringBuilder octal = new Binary(new StringBuilder("10101")).convertToOctal();
        assertEquals(octal.toString(), "25");
    }
    @Test
    public void convert0ToHex(){
        StringBuilder octal = new Binary(new StringBuilder("0")).convertToHex();
        assertEquals(octal.toString(), "0");
    }
    @Test
    public void convert111ToHex(){
        StringBuilder octal = new Binary(new StringBuilder("111")).convertToHex();
        assertEquals(octal.toString(), "7");
    }
    @Test
    public void convert11101ToHex(){
        StringBuilder octal = new Binary(new StringBuilder("11101")).convertToHex();
        assertEquals(octal.toString(), "1d");
    }
}
