package ru.unn.agile.CurrencyConverter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collection;

/**
 * Created by Jane on 03.11.2016.
 */
public class whenConverting {
    private Converter converter;
    private double delta = 0.00001;

    @Before
    public void setUp() {
        converter = new Converter();
    }

    @Test
    public void theSame() {
        assertEquals(1, converter.execute(1, "theSame"), delta);
    }

    @Test
    public void rubleToDollar() {
        assertEquals(1, converter.execute(62.6200, "rubleToDollar"), delta);
    }

    @Test
    public void rubleToEuro() {
        assertEquals(1, converter.execute(69.1254, "rubleToEuro"), delta);
    }

    @Test
    public void rubleToHryvna(){
        assertEquals(1, converter.execute(24.3255, "rubleToHryvna"),delta);
    }

    @Test
    public void rubleToYen(){
        assertEquals(1, converter.execute(60.4592, "rubleToYen"),delta);
    }

    @Test
    public void rubleToFranc() {
        assertEquals(1,converter.execute(63.5256, "rubleToFranc"),delta);
    }

    @Test
    public void dollarToRuble() {
        assertEquals(62.6200, converter.execute(1, "dollarToRuble"), delta);
    }

    @Test
    public void euroToRuble() {
        assertEquals(69.1254, converter.execute(1, "euroToRuble"), delta);
    }

    @Test
    public void hryvnaToRuble() {
        assertEquals(24.3255, converter.execute(1, "hryvnaToRuble"), delta);
    }

    @Test
    public void yenToRuble() {
        assertEquals(60.4592, converter.execute(1, "yenToRuble"), delta);
    }

    @Test
    public void francToRuble() {
        assertEquals(63.5256, converter.execute(1, "francToRuble"), delta);
    }

    @Test
    public void dollarToEuro() {
        assertEquals(1, converter.execute(1.1038,"dollarToEuro"), delta);
    }

    @Test
    public void euroToDollar() {
        assertEquals(1, converter.execute(0.9058,"euroToDollar"), delta);
    }

    @Test
    public void dollarToHryvna() {
        assertEquals(1, converter.execute(0.3884,"dollarToHryvna"), delta);
    }

    @Test
    public void hryvnaToDollar() {
        assertEquals(1, converter.execute(2.5742,"hryvnaToDollar"), delta);
    }

    @Test
    public void dollarToYen() {
        assertEquals(1, converter.execute(0.9654,"dollarToYen"), delta);
    }

    @Test
    public void yenToDollar() {
        assertEquals(1, converter.execute(1.0357,"yenToDollar"), delta);
    }

    @Test
    public void dollarToFranc() {
        assertEquals(1, converter.execute(1.0144,"dollarToFranc"), delta);
    }

    @Test
    public void francToDollar() {
        assertEquals(1, converter.execute(0.9857,"francToDollar"), delta);
    }

    @Test
    public void euroToHryvna() {
        assertEquals(1, converter.execute(0.3519,"euroToHryvna"), delta);
    }

    @Test
    public void hryvnaToEuro() {
        assertEquals(1, converter.execute(2.8416,"hryvnaToEuro"), delta);
    }

    @Test
    public void euroToYen() {
        assertEquals(1, converter.execute(0.8746,"euroToYen"), delta);
    }

    @Test
    public void yenToEuro() {
        assertEquals(1, converter.execute(1.1433,"yenToEuro"), delta);
    }

    @Test
    public void euroToFranc() {
        assertEquals(1, converter.execute(0.9189,"euroToFranc"), delta);
    }

    @Test
    public void francToEuro() {
        assertEquals(1, converter.execute(1.0881,"francToEuro"), delta);
    }

    @Test
    public void hryvnaToYen() {
        assertEquals(1, converter.execute(2.4854,"hryvnaToYen"), delta);
    }

    @Test
    public void yenToHryvna() {
        assertEquals(1, converter.execute(0.4023,"yenToHryvna"), delta);
    }

    @Test
    public void hryvnaToFranc() {
        assertEquals(1, converter.execute(2.6114,"hryvnaToFranc"), delta);
    }

    @Test
    public void francToHryvna() {
        assertEquals(1, converter.execute(0.3829,"francToHryvna"), delta);
    }

    @Test
    public void yenToFranc() {
        assertEquals(1, converter.execute(1.0507,"yenToFranc"), delta);
    }

    @Test
    public void francToYen() {
        assertEquals(1, converter.execute(0.9517,"francToYen"), delta);
    }
}
