package ru.unn.agile.MultisystemCalculator.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alexander on 07.11.2016.
 */
public class MultisystemCalculatorTest {

    @Test
    public void testThatCanDoAdditionAndOutputInBinary() {
        assertEquals("0b10", MultisystemCalculator.addB("0b1", "0b1"));
    }

    @Test
    public void testThatCanDoAdditionOfNegativeNumbersAndOutputInBinary() {
        assertEquals("0b-1110", MultisystemCalculator.addB("0b-1111", "0b1"));
    }

    @Test
    public void testThatCanDoAdditionAndOutputInOctal() {
        assertEquals("0o-10", MultisystemCalculator.addO("0o-20", "0o10"));
    }

    @Test
    public void testThatCanDoAdditionAndOutputInHexadecimal() {
        assertEquals("0xA", MultisystemCalculator.addH("0x5", "0x5"));
    }

    @Test
    public void testThatCanDoSubtractionAndOutputInBinary() {
        assertEquals("0b0", MultisystemCalculator.subB("0b1", "0b1"));
    }

    @Test
    public void testThatCanDoSubtractionAndOutputInOctal() {
        assertEquals("0o-30", MultisystemCalculator.subO("0o-20", "0o10"));
    }

    @Test
    public void testThatCanDoSubtractionAndOutputInHexadecimal() {
        assertEquals("0x10", MultisystemCalculator.subH("0x15", "0x5"));
    }

    @Test
    public void testThatCanDoMultiplicationAndOutputInBinary() {
        assertEquals("0b100", MultisystemCalculator.mulB("0b10", "0b10"));
    }

    @Test
    public void testThatCanDoMultiplicationAndOutputInOctal() {
        assertEquals("0o20", MultisystemCalculator.mulO("0o-20", "0o-1"));
    }

    @Test
    public void testThatCanDoMultiplicationAndOutputInHexadecimal() {
        assertEquals("0xF", MultisystemCalculator.mulH("0x3", "0x5"));
    }

    @Test
    public void testThatCanDoDivisionAndOutputInBinary() {
        assertEquals("0b1", MultisystemCalculator.divB("0b10", "0b10"));
    }

    @Test
    public void testThatCanDoDivisionAndOutputInOctal() {
        assertEquals("0o20", MultisystemCalculator.divO("0o-20", "0o-1"));
    }

    @Test
    public void testThatCanDoDivisionAndOutputInHexadecimal() {
        assertEquals("0x1", MultisystemCalculator.divH("0x3", "0x5"));
    }
}
