package ru.unn.agile.Converter.Model;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static ru.unn.agile.Converter.Model.MassConverter.*;



public class ConverterMassTest {
    private static final double DELTA = 0.00000001;

    @Test
    public void convertKgToGr() {
        double kilogram = convertToGram(1);
        assertEquals(1000, kilogram, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToGr() {
        convertToGram(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToGr() {
        try {
            convertToGram(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }

    @Test
    public void convertKgToTon() {
        double kilogram = convertToTon(1);
        assertEquals(0.001, kilogram, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToTon() {
        convertToTon(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToTon() {
        try {
            convertToTon(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }

    @Test
    public void convertKgToCentner() {
        double kilogram = convertToCentner(1);
        assertEquals(0.01, kilogram, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToCentner() {
        convertToCentner(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToCentner() {
        try {
            convertToCentner(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }

    @Test
    public void convertKgToPound() {
        double kilogram = convertToPound(1);
        assertEquals(2.679, kilogram, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToPound() {
        convertToPound(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToPound() {
        try {
            convertToPound(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }

    @Test
    public void convertKgToMilligram() {
        double kilogram = convertToMilligram(1);
        assertEquals(1000000, kilogram, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToMilligram() {
        convertToMilligram(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToMilligram() {
        try {
            convertToMilligram(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }

    @Test
    public void convertKgToMicrogram() {
        double kilogram = convertToMicrogram(1);
        assertEquals(1000000000, kilogram, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertNegativeKgToMicrogram() {
        convertToMicrogram(-1);
    }

    @Test
    public void isCorrectExceptionMessageForConvertNegativeKgToMicrogram() {
        try {
            convertToMicrogram(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Kilogram must be positive"));
        }
    }
  }
