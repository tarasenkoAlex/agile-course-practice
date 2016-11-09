package ru.unn.agile.SquareConverter;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class SquareConverterTest {

    private final double delta = 0.0000000001;

    private SquareConverter converter;

    public SquareConverter getConverter() {
        return converter;
    }

    @Before
    public void setUp() {
        converter = new SquareConverter();
    }

    @Test
    public  void canConvertFromSqrMeterToSqrKilometer() {
        double sqrKilometerCount = converter.toSqrKilometer(1);
        assertEquals(0.000001, sqrKilometerCount, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void canConvertFromNegativeSqrMeterCountToSqrKilometer() {
        converter.toSqrKilometer(-1);
    }

    @Test
    public void isCorrectExceptionMessageForToSqrKilometer() {
        try {
            converter.toSqrKilometer(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

    @Test
    public void canConvertFromSqrMeterToHectare() {
        double sqrKilometerCount = converter.toHectare(1);
        assertEquals(0.0001, sqrKilometerCount, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void canConvertFromNegativeSqrMeterCountToHectare() {
        converter.toHectare(-1);
    }

    @Test
    public void isCorrectExceptionMessageForToSqrHectare() {
        try {
            converter.toHectare(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

    @Test
    public void canConvertFromSqrMeterToAr() {
        double sqrKilometerCount = converter.toAr(1);
        assertEquals(0.01, sqrKilometerCount, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void canConvertFromNegativeSqrMeterCountToAr() {
        converter.toAr(-1);
    }

    @Test
    public void isCorrectExceptionMessageForToSqrAr() {
        try {
            converter.toAr(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

    @Test
    public void canConvertFromSqrMeterToSqrCentimeter() {
        double sqrKilometerCount = converter.toSqrCentimeter(1);
        assertEquals(10000, sqrKilometerCount, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void canConvertFromNegativeSqrMeterCountToSqrCentimeter() {
        converter.toSqrCentimeter(-1);
    }

    @Test
    public void isCorrectExceptionMessageForToSqrSqrCentimeter() {
        try {
            converter.toSqrCentimeter(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

    @Test
    public void canConvertFromSqrMeterToSqrMillimeter() {
        double sqrKilometerCount = converter.toSqrMillimeter(1);
        assertEquals(1000000, sqrKilometerCount, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void canConvertFromNegativeSqrMeterCountToSqrMillimeter() {
        converter.toSqrMillimeter(-1);
    }

    @Test
    public void isCorrectExceptionMessageForToSqrSqrMillimeter() {
        try {
            converter.toSqrMillimeter(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

    @Test
    public void canConvertFromSqrMeterToSqrYard() {
        double sqrKilometerCount = converter.toSqrYard(1);
        assertEquals(1.196, sqrKilometerCount, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void canConvertFromNegativeSqrMeterCountToSqrYard() {
        converter.toSqrYard(-1);
    }

    @Test
    public void isCorrectExceptionMessageForToSqrSqrYard() {
        try {
            converter.toSqrYard(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

    @Test
    public void canConvertFromSqrMeterToSqrFoot() {
        double sqrKilometerCount = converter.toSqrFoot(1);
        assertEquals(10.76, sqrKilometerCount, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void canConvertFromNegativeSqrMeterCountToSqrFoot() {
        converter.toSqrFoot(-1);
    }

    @Test
    public void isCorrectExceptionMessageForToSqrSqrFoot() {
        try {
            converter.toSqrFoot(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

    @Test
    public void canConvertFromSqrMeterToSqrInch() {
        double sqrKilometerCount = converter.toSqrInch(1);
        assertEquals(1550, sqrKilometerCount, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void canConvertFromNegativeSqrMeterCountToSqrInch() {
        converter.toSqrInch(-1);
    }

    @Test
    public void isCorrectExceptionMessageForToSqrSqrInch() {
        try {
            converter.toSqrInch(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

    @Test
    public void canConvertFromSqrMeterToSqrAcre() {
        double sqrKilometerCount = converter.toSqrAcre(1);
        assertEquals(0.0002471, sqrKilometerCount, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void canConvertFromNegativeSqrMeterCountToSqrAcre() {
        converter.toSqrAcre(-1);
    }

    @Test
    public void isCorrectExceptionMessageForToSqrSqrAcre() {
        try {
            converter.toSqrAcre(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

    @Test
    public void canConvertFromSqrMeterToSqrMile() {
        double sqrKilometerCount = converter.toSqrMile(1);
        assertEquals(0.0000003861, sqrKilometerCount, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void canConvertFromNegativeSqrMeterCountToSqrMile() {
        converter.toSqrMile(-1);
    }

    @Test
    public void isCorrectExceptionMessageForToSqrSqrMile() {
        try {
            converter.toSqrMile(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

    @Test
    public void isExceptionMessageCorrectWhenSecondArgumentIllegalForToDistanationUnit() {
        try {
            converter.toDistanationUnit(1, -1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("distanationMultiplier must be positive number"));
        }
    }

    @Test
    public void whenLegalArgumentsForToDistanationUnit() {
        double result = converter.toDistanationUnit(1, 1);
        assertEquals(1, result, delta);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void whenIllegalFirstArgumentForToDistanationUnit() {
        converter.toDistanationUnit(-1, 1);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void whenIllegalSecondArgumentForToDistanationUnit() {
        converter.toDistanationUnit(1, -1);
    }

    @Test(expected  = IllegalArgumentException.class)
    public void whenIllegalBothArgumentsForToDistanationUnit() {
        converter.toDistanationUnit(-1, -1);
    }

    @Test
    public void isExceptionMessageCorrectWhenFirstArgumentIllegalForToDistanationUnit() {
        try {
            converter.toDistanationUnit(-1, 1);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Square must be positive number"));
        }
    }

}
