package test;
/**
 * Created by Yevtyushin Valery on 13.11.2016.
 */

import main.MortrageCalculator;
import main.mortrage.MortrageData;
import main.mortrage.MortrageDataBuilder;
import org.junit.Test;

import static org.junit.Assert.*;


public class MortrageCalculatorTest {

    private final double debt = 7000000;
    private final double years = 10;
    private final double percents = 20;
    private final double delta = 0.0001;

    private MortrageDataBuilder mortrageDataBuilder = new MortrageDataBuilder();
    private MortrageData mortrageData = mortrageDataBuilder
            .setDebt(debt).setPercents(percents)
            .setYears(years)
            .build();

    @Test
    public void testThatCalculateCountOverpayment() {
        assertEquals(9261078.16045, MortrageCalculator.countOverpayment(mortrageData), delta);
    }

    @Test
    public void testThatCalculateTotalSum() {
        assertEquals(16261078.16045, MortrageCalculator.countTotalSum(mortrageData), delta);
    }

    @Test
    public void testThatCalculateMonthCountPayemnt() {
        assertEquals(135508.98467, MortrageCalculator.countPayment(mortrageData), delta);
    }
}
