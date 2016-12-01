package ru.unn.agile.MortgageCalculator.model;
/**
 * Created by Yevtyushin Valery on 15.11.2016.
 */

import org.junit.Test;

import java.security.InvalidParameterException;

public class MortrageDataTest {

    private final double debt = 7000000;
    private final double years = 10;
    private final double percents = 20;
    private MortrageDataBuilder mortrageDataBuilder;

    @Test (expected = InvalidParameterException.class)
    public void testThatCheckInvalidParameterDebt() {
        mortrageDataBuilder = new MortrageDataBuilder();
        mortrageDataBuilder.setDebt(-debt);
    }

    @Test (expected = InvalidParameterException.class)
    public void testThatCheckInvalidParameterYears() {
        mortrageDataBuilder = new MortrageDataBuilder();
        mortrageDataBuilder.setYears(-years);
    }

    @Test (expected = InvalidParameterException.class)
    public void testThatCheckInvalidParameterPercents() {
        mortrageDataBuilder = new MortrageDataBuilder();
        mortrageDataBuilder.setPercents(-percents);
    }
}
