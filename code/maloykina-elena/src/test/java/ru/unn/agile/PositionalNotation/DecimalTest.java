package ru.unn.agile.PositionalNotation;

import org.junit.Test;
import static org.junit.Assert.*;

public class DecimalTest {
    @Test
    public void canInitialDecimal(){
        Decimal object = new Decimal(12);
        assertNotNull(object);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkInitialData(){
        new Decimal(-2).checkDecimalValue();
    }
    @Test
    public void convert0ToBinary(){
        StringBuilder binary = new Decimal(0).convertToBinary();
        assertEquals(binary.toString(), "0");
    }
    @Test
    public void convert10ToBinary(){
        StringBuilder binary = new Decimal(10).convertToBinary();
        assertEquals(binary.toString(), "1010");
    }
    @Test
    public void convert19ToBinary(){
        StringBuilder binary = new Decimal(19).convertToBinary();
        assertEquals(binary.toString(), "10011");
    }
    @Test
    public void convert0ToOctal(){
        StringBuilder octal = new Decimal(0).convertToOctal();
        assertEquals(octal.toString(),"0");
    }
    @Test
    public void convert7ToOctal(){
        StringBuilder octal = new Decimal(6).convertToOctal();
        assertEquals(octal.toString(),"6");
    }
    @Test
    public void convert9ToOctal(){
        StringBuilder octal = new Decimal(9).convertToOctal();
        assertEquals(octal.toString(),"11");
    }
    @Test
    public void convert26ToOctal(){
        StringBuilder octal = new Decimal(26).convertToOctal();
        assertEquals(octal.toString(),"32");
    }
    @Test
    public void convert1ToHex(){
        StringBuilder hex = new Decimal(1).convertToHex();
        assertEquals(hex.toString(), "1");
    }
    @Test
    public void convert13ToHex(){
        StringBuilder hex = new Decimal(13).convertToHex();
        assertEquals(hex.toString(), "d");
    }
    @Test
    public void convert59ToHex(){
        StringBuilder hex = new Decimal(59).convertToHex();
        assertEquals(hex.toString(), "3b");
    }
}
