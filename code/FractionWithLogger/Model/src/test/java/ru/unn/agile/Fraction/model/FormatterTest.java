package ru.unn.agile.Fraction.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FormatterTest {
    @Test
    public void canFormatToString() {
        Fraction f = new Fraction(1, 2);
        String s = f.toString();
        assertEquals("1/2", s);
    }

    @Test
    public void canFormatIntToString() {
        Fraction f = new Fraction(1, 1);
        String s = f.toString();
        assertEquals("1", s);
    }

    @Test
    public void canFormatNegativeToString() {
        Fraction f = new Fraction(10, -7);
        String s = f.toString();
        assertEquals("-10/7", s);
    }

    @Test
    public void canFormatNegativeIntToString() {
        Fraction f = new Fraction(-101, 1);
        String s = f.toString();
        assertEquals("-101", s);
    }

    @Test
    public void canFormatFromString() {
        Fraction f = Fraction.valueOf("3/4");
        assertEquals(new Fraction(3, 4), f);
    }

    @Test
    public void canFormatFromIntString() {
        Fraction f = Fraction.valueOf("1");
        assertEquals(new Fraction(1), f);
    }

    @Test
    public void canFormatFromNegativeIntString() {
        Fraction f = Fraction.valueOf("-5");
        assertEquals(new Fraction(-5), f);
    }

    @Test
    public void canFormatFromNegativeString() {
        Fraction f = Fraction.valueOf("-3/12");
        assertEquals(new Fraction(-1, 4), f);
    }
}
