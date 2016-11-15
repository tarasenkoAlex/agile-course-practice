/**
 * Created by Yevtyushin Valery on 15.11.2016.
 */

import mortrage.MortrageDataFactory;
import org.junit.Test;

import java.security.InvalidParameterException;

public class MortrageDataTest {

    private final double debt = 7000000;
    private final double years = 10;
    private final double percents = 20;

    @Test(expected = InvalidParameterException.class)
    public void testThatCheckInvalidParameterDebt() {
        MortrageDataFactory.getMortrageData(-debt, years, percents);
    }

    @Test(expected = InvalidParameterException.class)
    public void testThatCheckInvalidParameterYears() {
        MortrageDataFactory.getMortrageData(debt, -years, percents);
    }

    @Test(expected = InvalidParameterException.class)
    public void testThatCheckInvalidParameterPercents() {
        MortrageDataFactory.getMortrageData(debt, years, -percents);
    }
}
