package ru.unn.agile.MultisystemCalculator.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alexander on 07.11.2016.
 */
public class NumberParserTest {

    @Test
    public void testThatCanParsePositiveBinary() {
        int number = NumberParser.parseNumber("0b1111");
        assertEquals(15, number);
    }

    @Test
    public void testThatCanParseNegativeBinary() {
        int number = NumberParser.parseNumber("0b-1111");
        assertEquals(-15, number);
    }

    @Test
    public void testThatCanParsePositiveOctal() {
        int number = NumberParser.parseNumber("0o10");
        assertEquals(8, number);
    }

    @Test
    public void testThatCanParseNegativeOctal() {
        int number = NumberParser.parseNumber("0o-10");
        assertEquals(-8, number);
    }

    @Test
    public void testThatCanParsePositiveHexadecimal() {
        int number = NumberParser.parseNumber("0xABCDEF");
        assertEquals(11259375, number);
    }

    @Test
    public void testThatCanParseNegativeHexadecimal() {
        int number = NumberParser.parseNumber("0x-ABCDEF");
        assertEquals(-11259375, number);
    }

    @Test
    public void testThatCanParseMinimalNegativeHexadecimal() {
        int number = NumberParser.parseNumber("0x-7FFFFFFF");
        assertEquals(-Integer.MAX_VALUE, number);
    }

    @Test
    public void testThatCanParseMaximalPositiveHexadecimal() {
        int number = NumberParser.parseNumber("0x7FFFFFFF");
        assertEquals(Integer.MAX_VALUE, number);
    }

    @Test
    public void testThatCanParseMinimalNegativeOctal() {
        int number = NumberParser.parseNumber("0o-17777777777");
        assertEquals(-Integer.MAX_VALUE, number);
    }

    @Test
    public void testThatCanParseMaximalPositiveOctal() {
        int number = NumberParser.parseNumber("0o17777777777");
        assertEquals(Integer.MAX_VALUE, number);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatWrongBinaryRepresentationRisesException() {
        NumberParser.parseNumber("0bA12");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatWrongOctalRepresentationRisesException() {
        NumberParser.parseNumber("0o178");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatWrongHexadecimalRepresentationRisesException() {
        NumberParser.parseNumber("0x17G8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatEmptyParameterRisesException() {
        NumberParser.parseNumber("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatNullParameterRisesException() {
        NumberParser.parseNumber(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatWrongSystemPredicateRisesException() {
        NumberParser.parseNumber("0a123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatBiggerThatIntegerMaxValueArgumentRisesException() {
        NumberParser.parseNumber("0x-8FFFFFFF");

    }
}
