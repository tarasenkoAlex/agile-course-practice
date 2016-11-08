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
}
