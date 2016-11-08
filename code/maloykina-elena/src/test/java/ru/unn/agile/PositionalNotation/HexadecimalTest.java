package ru.unn.agile.PositionalNotation;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class HexadecimalTest {
    @Test
    public void canInitialHexadeimal(){
        Hexadecimal object = new Hexadecimal(new StringBuilder("12"));
        assertNotNull(object);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkInitialData(){
        new Hexadecimal(new StringBuilder("1r")).checkHexValue();
    }
    @Test
    public void converter9ToDecimal(){
        int decimal = new Hexadecimal(new StringBuilder("9")).convertToDecimal();
        assertEquals(decimal, 9);
    }
    @Test
    public void converterAToDecimal(){
        int decimal = new Hexadecimal(new StringBuilder("a")).convertToDecimal();
        assertEquals(decimal, 10);
    }
    @Test
    public void converter1bToDecimal(){
        int decimal = new Hexadecimal(new StringBuilder("1b")).convertToDecimal();
        assertEquals(decimal, 27);
    }
    @Test
    public void convert7Binary(){
        StringBuilder binary = new Hexadecimal(new StringBuilder("7")).convertToBinary();
        assertEquals(binary.toString(), "111");
    }
    @Test
    public void convert14aToBinary(){
        StringBuilder binary = new Hexadecimal(new StringBuilder("14a")).convertToBinary();
        assertEquals(binary.toString(), "101001010");
    }
    @Test
    public void convert13f40ToBinary(){
        StringBuilder binary = new Hexadecimal(new StringBuilder("13f40")).convertToBinary();
        assertEquals(binary.toString(), "10011111101000000");
    }
    @Test
    public void convert8ToOctal(){
        StringBuilder binary = new Hexadecimal(new StringBuilder("8")).convertToOctal();
        assertEquals(binary.toString(), "10");
    }
    @Test
    public void convert14aToOctal(){
        StringBuilder binary = new Hexadecimal(new StringBuilder("14a")).convertToOctal();
        assertEquals(binary.toString(), "512");
    }
    @Test
    public void convert19ToOctal(){
        StringBuilder binary = new Hexadecimal(new StringBuilder("19")).convertToOctal();
        assertEquals(binary.toString(), "31");
    }
}
