package ru.unn.agile.CurrencyConverter;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Collection;

/**
 * Created by Jane on 03.11.2016.
 */
public class WhenCurrencyConverting {
    private Converter converter;
    private double delta = 0.001;

    @Before
    public void setUp() {
        converter = new Converter();
    }

    @Test
    public void theSame() {
        assertEquals(1, converter.execute(1, Converter.RUBLE, Converter.RUBLE), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canConvertFromNegativeAmount() {
        converter.execute(-1, Converter.RUBLE, Converter.RUBLE);
    }

    @Test
    public void IsCorrectExceptionMessageForExecute() {
        try {
            converter.execute(-1, Converter.RUBLE, Converter.RUBLE);
        }
        catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Amount must be positive number"));
        }
    }

    @Test
    public void rubleToDollar() {
        assertEquals(1, converter.execute(62.6200, Converter.RUBLE, Converter.DOLLAR), delta);
    }

    @Test
    public void rubleToEuro() {
        assertEquals(1, converter.execute(69.1254, Converter.RUBLE, Converter.EURO), delta);
    }

    @Test
    public void rubleToHryvna() {
        assertEquals(1, converter.execute(24.3255, Converter.RUBLE, Converter.HRYVNA), delta);
    }

    @Test
    public void rubleToYen() {
        assertEquals(1, converter.execute(60.4592, Converter.RUBLE, Converter.YEN), delta);
    }

    @Test
    public void rubleToFranc() {
        assertEquals(1,converter.execute(63.5256, Converter.RUBLE, Converter.FRANC),delta);
    }

    @Test
    public void dollarToRuble() {
        assertEquals(62.6200, converter.execute(1, Converter.DOLLAR, Converter.RUBLE), delta);
    }

    @Test
    public void euroToRuble() {
        assertEquals(69.1254, converter.execute(1, Converter.EURO, Converter.RUBLE), delta);
    }

    @Test
    public void hryvnaToRuble() {
        assertEquals(24.3255, converter.execute(1, Converter.HRYVNA, Converter.RUBLE), delta);
    }

    @Test
    public void yenToRuble() {
        assertEquals(60.4592, converter.execute(1, Converter.YEN, Converter.RUBLE), delta);
    }

    @Test
    public void francToRuble() {
        assertEquals(63.5256, converter.execute(1, Converter.FRANC, Converter.RUBLE), delta);
    }

    @Test
    public void dollarToEuro() {
        assertEquals(1, converter.execute(1.1038, Converter.DOLLAR, Converter.EURO), delta);
    }

    @Test
    public void euroToDollar() {
        assertEquals(1, converter.execute(0.9058, Converter.EURO, Converter.DOLLAR), delta);
    }

    @Test
    public void dollarToHryvna() {
        assertEquals(1, converter.execute(0.3884, Converter.DOLLAR, Converter.HRYVNA), delta);
    }

    @Test
    public void hryvnaToDollar() {
        assertEquals(1, converter.execute(2.5742, Converter.HRYVNA, Converter.DOLLAR), delta);
    }

    @Test
    public void dollarToYen() {
        assertEquals(1, converter.execute(0.9654, Converter.DOLLAR, Converter.YEN), delta);
    }

    @Test
    public void yenToDollar() {
        assertEquals(1, converter.execute(1.0357, Converter.YEN ,Converter.DOLLAR), delta);
    }

    @Test
    public void dollarToFranc() {
        assertEquals(1, converter.execute(1.0144, Converter.DOLLAR, Converter.FRANC), delta);
    }

    @Test
    public void francToDollar() {
        assertEquals(1, converter.execute(0.9857, Converter.FRANC, Converter.DOLLAR), delta);
    }

    @Test
    public void euroToHryvna() {
        assertEquals(1, converter.execute(0.3519, Converter.EURO, Converter.HRYVNA), delta);
    }

    @Test
    public void hryvnaToEuro() {
        assertEquals(1, converter.execute(2.8416, Converter.HRYVNA, Converter.EURO), delta);
    }

    @Test
    public void euroToYen() {
        assertEquals(1, converter.execute(0.8746, Converter.EURO, Converter.YEN), delta);
    }

    @Test
    public void yenToEuro() {
        assertEquals(1, converter.execute(1.1433, Converter.YEN, Converter.EURO), delta);
    }

    @Test
    public void euroToFranc() {
        assertEquals(1, converter.execute(0.9189, Converter.EURO, Converter.FRANC), delta);
    }

    @Test
    public void francToEuro() {
        assertEquals(1, converter.execute(1.0881, Converter.FRANC, Converter.EURO), delta);
    }

    @Test
    public void hryvnaToYen() {
        assertEquals(1, converter.execute(2.4854, Converter.HRYVNA, Converter.YEN), delta);
    }

    @Test
    public void yenToHryvna() {
        assertEquals(1, converter.execute(0.4023, Converter.YEN, Converter.HRYVNA), delta);
    }

    @Test
    public void hryvnaToFranc() {
        assertEquals(1, converter.execute(2.6114, Converter.HRYVNA, Converter.FRANC), delta);
    }

    @Test
    public void francToHryvna() {
        assertEquals(1, converter.execute(0.3829, Converter.FRANC, Converter.HRYVNA), delta);
    }

    @Test
    public void yenToFranc() {
        assertEquals(1, converter.execute(1.0507, Converter.YEN, Converter.FRANC), delta);
    }

    @Test
    public void francToYen() {
        assertEquals(1, converter.execute(0.9517, Converter.FRANC, Converter.YEN), delta);
    }
}
