package ru.unn.agile.PositionalNotation;

import org.junit.Test;
import static org.junit.Assert.*;

public class OctalTest {
    @Test
    public void canInitialOctal(){
        Octal object = new Octal(new StringBuilder("17"));
        assertNotNull(object);
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkInitialData(){
        new Octal(new StringBuilder("19")).checkOctalValue();
    }
    @Test
    public void convert3ToDecimal(){
        int octal = new Octal(new StringBuilder("3")).convertToDecimal();
        assertEquals(octal,3);
    }
    @Test
    public void convert17ToDecimal(){
        int decimal = new Octal(new StringBuilder("17")).convertToDecimal();
        assertEquals(decimal,15);
    }
    @Test
    public void convert25ToDecimal(){
        int decimal = new Octal(new StringBuilder("25")).convertToDecimal();
        assertEquals(decimal,21);
    }
    @Test
    public void convert3ToBinary(){
        StringBuilder binary = new Octal(new StringBuilder("3")).convertToBinary();
        assertEquals(binary.toString(), "11");
    }
    @Test
    public void convert11ToBinary(){
        StringBuilder binary = new Octal(new StringBuilder("11")).convertToBinary();
        assertEquals(binary.toString(), "1001");
    }
    @Test
    public void convert37ToBinary(){
        StringBuilder binary = new Octal(new StringBuilder("37")).convertToBinary();
        assertEquals(binary.toString(), "11111");
    }
    @Test
    public void convert3ToHex(){
        StringBuilder binary = new Octal(new StringBuilder("3")).convertToHex();
        assertEquals(binary.toString(), "3");
    }
    @Test
    public void convert17ToHex(){
        StringBuilder binary = new Octal(new StringBuilder("17")).convertToHex();
        assertEquals(binary.toString(), "f");
    }
    @Test
    public void convert32ToHex(){
        StringBuilder binary = new Octal(new StringBuilder("32")).convertToHex();
        assertEquals(binary.toString(), "1a");
    }
}
