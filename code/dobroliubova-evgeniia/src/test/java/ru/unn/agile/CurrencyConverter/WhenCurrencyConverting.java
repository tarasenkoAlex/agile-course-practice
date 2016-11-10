package ru.unn.agile.CurrencyConverter;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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
        assertEquals(1, converter.execute(1, Constants.RUBLE, Constants.RUBLE), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canConvertFromNegativeAmount() {
        converter.execute(-1, Constants.RUBLE, Constants.RUBLE);
    }

    @Test
    public void isCorrectExceptionMessageForExecute() {
        try {
            converter.execute(-1, Constants.RUBLE, Constants.RUBLE);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Amount must be positive number"));
        }
    }

    @Test
    public void rubleToDollar() {
        assertEquals(1, converter.execute(62.6200, Constants.RUBLE, Constants.DOLLAR), delta);
    }

    @Test
    public void rubleToEuro() {
        assertEquals(1, converter.execute(69.1254, Constants.RUBLE, Constants.EURO), delta);
    }

    @Test
    public void rubleToHryvna() {
        assertEquals(1, converter.execute(24.3255, Constants.RUBLE, Constants.HRYVNA), delta);
    }

    @Test
    public void rubleToYen() {
        assertEquals(1, converter.execute(60.4592, Constants.RUBLE, Constants.YEN), delta);
    }

    @Test
    public void rubleToFranc() {
        assertEquals(1, converter.execute(63.5256, Constants.RUBLE, Constants.FRANC), delta);
    }

    @Test
    public void dollarToRuble() {
        assertEquals(62.6200, converter.execute(1, Constants.DOLLAR, Constants.RUBLE), delta);
    }

    @Test
    public void euroToRuble() {
        assertEquals(69.1254, converter.execute(1, Constants.EURO, Constants.RUBLE), delta);
    }

    @Test
    public void hryvnaToRuble() {
        assertEquals(24.3255, converter.execute(1, Constants.HRYVNA, Constants.RUBLE), delta);
    }

    @Test
    public void yenToRuble() {
        assertEquals(60.4592, converter.execute(1, Constants.YEN, Constants.RUBLE), delta);
    }

    @Test
    public void francToRuble() {
        assertEquals(63.5256, converter.execute(1, Constants.FRANC, Constants.RUBLE), delta);
    }

    @Test
    public void dollarToEuro() {
        assertEquals(1, converter.execute(1.1038, Constants.DOLLAR, Constants.EURO), delta);
    }

    @Test
    public void euroToDollar() {
        assertEquals(1, converter.execute(0.9058, Constants.EURO, Constants.DOLLAR), delta);
    }

    @Test
    public void dollarToHryvna() {
        assertEquals(1, converter.execute(0.3884, Constants.DOLLAR, Constants.HRYVNA), delta);
    }

    @Test
    public void hryvnaToDollar() {
        assertEquals(1, converter.execute(2.5742, Constants.HRYVNA, Constants.DOLLAR), delta);
    }

    @Test
    public void dollarToYen() {
        assertEquals(1, converter.execute(0.9654, Constants.DOLLAR, Constants.YEN), delta);
    }

    @Test
    public void yenToDollar() {
        assertEquals(1, converter.execute(1.0357, Constants.YEN , Constants.DOLLAR), delta);
    }

    @Test
    public void dollarToFranc() {
        assertEquals(1, converter.execute(1.0144, Constants.DOLLAR, Constants.FRANC), delta);
    }

    @Test
    public void francToDollar() {
        assertEquals(1, converter.execute(0.9857, Constants.FRANC, Constants.DOLLAR), delta);
    }

    @Test
    public void euroToHryvna() {
        assertEquals(1, converter.execute(0.3519, Constants.EURO, Constants.HRYVNA), delta);
    }

    @Test
    public void hryvnaToEuro() {
        assertEquals(1, converter.execute(2.8416, Constants.HRYVNA, Constants.EURO), delta);
    }

    @Test
    public void euroToYen() {
        assertEquals(1, converter.execute(0.8746, Constants.EURO, Constants.YEN), delta);
    }

    @Test
    public void yenToEuro() {
        assertEquals(1, converter.execute(1.1433, Constants.YEN, Constants.EURO), delta);
    }

    @Test
    public void euroToFranc() {
        assertEquals(1, converter.execute(0.9189, Constants.EURO, Constants.FRANC), delta);
    }

    @Test
    public void francToEuro() {
        assertEquals(1, converter.execute(1.0881, Constants.FRANC, Constants.EURO), delta);
    }

    @Test
    public void hryvnaToYen() {
        assertEquals(1, converter.execute(2.4854, Constants.HRYVNA, Constants.YEN), delta);
    }

    @Test
    public void yenToHryvna() {
        assertEquals(1, converter.execute(0.4023, Constants.YEN, Constants.HRYVNA), delta);
    }

    @Test
    public void hryvnaToFranc() {
        assertEquals(1, converter.execute(2.6114, Constants.HRYVNA, Constants.FRANC), delta);
    }

    @Test
    public void francToHryvna() {
        assertEquals(1, converter.execute(0.3829, Constants.FRANC, Constants.HRYVNA), delta);
    }

    @Test
    public void yenToFranc() {
        assertEquals(1, converter.execute(1.0507, Constants.YEN, Constants.FRANC), delta);
    }

    @Test
    public void francToYen() {
        assertEquals(1, converter.execute(0.9517, Constants.FRANC, Constants.YEN), delta);
    }
}
