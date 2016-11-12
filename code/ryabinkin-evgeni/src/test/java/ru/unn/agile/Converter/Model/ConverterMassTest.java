package ru.unn.agile.Converter.Model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class ConverterMassTest {
    private final double delta = 0.00000001;
    private MassConverter converter;

    @Before
    public void setUp() {
        converter = new MassConverter();
    }

    //////////////////////////////////////////////////////////////////////
    @Test
    public void convertKgToGr() {
        double kilogram = converter.convertToGram(1);
        assertEquals(kilogram, 1000, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToGr() {
        converter.convertToGram(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToGr() {
        try {
            converter.convertToGram(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    @Test
    public void convertKgToTon() {
        double kilogram = converter.convertToTon(1);
        assertEquals(kilogram, 0.001, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToTon() {
        converter.convertToTon(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToTon() {
        try {
            converter.convertToTon(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    @Test
    public void convertKgToCentner() {
        double kilogram = converter.convertToCentner(1);
        assertEquals(kilogram, 0.01, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToCentner() {
        converter.convertToCentner(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToCentner() {
        try {
            converter.convertToCentner(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    @Test
    public void convertKgToPound() {
        double kilogram = converter.convertToPound(1);
        assertEquals(kilogram, 2.679, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToPound() {
        converter.convertToPound(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToPound() {
        try {
            converter.convertToPound(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    @Test
    public void convertKgToMilligram() {
        double kilogram = converter.convertToMilligram(1);
        assertEquals(kilogram, 1000000, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToMilligram() {
        converter.convertToMilligram(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToMilligram() {
        try {
            converter.convertToMilligram(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    @Test
    public void convertKgToMicrogram() {
        double kilogram = converter.convertToMicrogram(1);
        assertEquals(kilogram, 1000000000, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToMicrogram() {
        converter.convertToMicrogram(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToMicrogram() {
        try {
            converter.convertToMicrogram(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }
    ///////////////////////////////////////////////////////////////////////
}
