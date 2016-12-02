package ru.unn.agile.CurrencyConverter.model;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WhenCurrencyConverting {
    private Converter converter;
    private double delta = 0.001;

    @Before
    public void setUp() {
        converter = new Converter();
    }

    @Test
    public void theSame() {
        assertEquals(1, converter.execute(1, Constants.RUBLE.getValue(),
                Constants.RUBLE.getValue()), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canConvertFromNegativeAmount() {
        converter.execute(-1, Constants.RUBLE.getValue(),
                Constants.RUBLE.getValue());
    }

    @Test
    public void isCorrectExceptionMessageForExecute() {
        try {
            converter.execute(-1, Constants.RUBLE.getValue(),
                    Constants.RUBLE.getValue());
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Amount must be positive number"));
        }
    }

    @Test
    public void rubleToDollar() {
        assertEquals(1, converter.execute(62.6200, Constants.RUBLE.getValue(),
                Constants.DOLLAR.getValue()), delta);
    }

    @Test
    public void rubleToEuro() {
        assertEquals(1, converter.execute(69.1254, Constants.RUBLE.getValue(),
                Constants.EURO.getValue()), delta);
    }

    @Test
    public void rubleToHryvna() {
        assertEquals(1, converter.execute(24.3255, Constants.RUBLE.getValue(),
                Constants.HRYVNA.getValue()), delta);
    }

    @Test
    public void rubleToYen() {
        assertEquals(1, converter.execute(60.4592, Constants.RUBLE.getValue(),
                Constants.YEN.getValue()), delta);
    }

    @Test
    public void rubleToFranc() {
        assertEquals(1, converter.execute(63.5256, Constants.RUBLE.getValue(),
                Constants.FRANC.getValue()), delta);
    }

    @Test
    public void dollarToRuble() {
        assertEquals(62.6200, converter.execute(1, Constants.DOLLAR.getValue(),
                Constants.RUBLE.getValue()), delta);
    }

    @Test
    public void euroToRuble() {
        assertEquals(69.1254, converter.execute(1, Constants.EURO.getValue(),
                Constants.RUBLE.getValue()), delta);
    }

    @Test
    public void hryvnaToRuble() {
        assertEquals(24.3255, converter.execute(1, Constants.HRYVNA.getValue(),
                Constants.RUBLE.getValue()), delta);
    }

    @Test
    public void yenToRuble() {
        assertEquals(60.4592, converter.execute(1, Constants.YEN.getValue(),
                Constants.RUBLE.getValue()), delta);
    }

    @Test
    public void francToRuble() {
        assertEquals(63.5256, converter.execute(1, Constants.FRANC.getValue(),
                Constants.RUBLE.getValue()), delta);
    }

    @Test
    public void dollarToEuro() {
        assertEquals(1, converter.execute(1.1038, Constants.DOLLAR.getValue(),
                Constants.EURO.getValue()), delta);
    }

    @Test
    public void euroToDollar() {
        assertEquals(1, converter.execute(0.9058, Constants.EURO.getValue(),
                Constants.DOLLAR.getValue()), delta);
    }

    @Test
    public void dollarToHryvna() {
        assertEquals(1, converter.execute(0.3884, Constants.DOLLAR.getValue(),
                Constants.HRYVNA.getValue()), delta);
    }

    @Test
    public void hryvnaToDollar() {
        assertEquals(1, converter.execute(2.5742, Constants.HRYVNA.getValue(),
                Constants.DOLLAR.getValue()), delta);
    }

    @Test
    public void dollarToYen() {
        assertEquals(1, converter.execute(0.9654, Constants.DOLLAR.getValue(),
                Constants.YEN.getValue()), delta);
    }

    @Test
    public void yenToDollar() {
        assertEquals(1, converter.execute(1.0357, Constants.YEN.getValue(),
                Constants.DOLLAR.getValue()), delta);
    }

    @Test
    public void dollarToFranc() {
        assertEquals(1, converter.execute(1.0144, Constants.DOLLAR.getValue(),
                Constants.FRANC.getValue()), delta);
    }

    @Test
    public void francToDollar() {
        assertEquals(1, converter.execute(0.9857, Constants.FRANC.getValue(),
                Constants.DOLLAR.getValue()), delta);
    }

    @Test
    public void euroToHryvna() {
        assertEquals(1, converter.execute(0.3519, Constants.EURO.getValue(),
                Constants.HRYVNA.getValue()), delta);
    }

    @Test
    public void hryvnaToEuro() {
        assertEquals(1, converter.execute(2.8416, Constants.HRYVNA.getValue(),
                Constants.EURO.getValue()), delta);
    }

    @Test
    public void euroToYen() {
        assertEquals(1, converter.execute(0.8746, Constants.EURO.getValue(),
                Constants.YEN.getValue()), delta);
    }

    @Test
    public void yenToEuro() {
        assertEquals(1, converter.execute(1.1433, Constants.YEN.getValue(),
                Constants.EURO.getValue()), delta);
    }

    @Test
    public void euroToFranc() {
        assertEquals(1, converter.execute(0.9189, Constants.EURO.getValue(),
                Constants.FRANC.getValue()), delta);
    }

    @Test
    public void francToEuro() {
        assertEquals(1, converter.execute(1.0881, Constants.FRANC.getValue(),
                Constants.EURO.getValue()), delta);
    }

    @Test
    public void hryvnaToYen() {
        assertEquals(1, converter.execute(2.4854, Constants.HRYVNA.getValue(),
                Constants.YEN.getValue()), delta);
    }

    @Test
    public void yenToHryvna() {
        assertEquals(1, converter.execute(0.4023, Constants.YEN.getValue(),
                Constants.HRYVNA.getValue()), delta);
    }

    @Test
    public void hryvnaToFranc() {
        assertEquals(1, converter.execute(2.6114, Constants.HRYVNA.getValue(),
                Constants.FRANC.getValue()), delta);
    }

    @Test
    public void francToHryvna() {
        assertEquals(1, converter.execute(0.3829, Constants.FRANC.getValue(),
                Constants.HRYVNA.getValue()), delta);
    }

    @Test
    public void yenToFranc() {
        assertEquals(1, converter.execute(1.0507, Constants.YEN.getValue(),
                Constants.FRANC.getValue()), delta);
    }

    @Test
    public void francToYen() {
        assertEquals(1, converter.execute(0.9517, Constants.FRANC.getValue(),
                Constants.YEN.getValue()), delta);
    }
}
