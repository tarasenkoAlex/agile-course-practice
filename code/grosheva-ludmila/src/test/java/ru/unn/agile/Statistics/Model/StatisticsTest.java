
package ru.unn.agile.Statistics.Model;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class StatisticsTest {
private static final double DELTA = 0.00001;

    public Statistics testingValues() {
        double []x = new double[]{1, 2, 3};
        double []p = new double[]{0.1, 0.5, 0.4};
            return new Statistics(x, p);
    }

    public Statistics testingNegativeValues() {
        double []x = new double[]{-1, -2, -3};
        double []p = new double[]{0.1, 0.5, 0.4};
        return new Statistics(x, p);
    }
    @Test
    public void canCreateStatisticValueAndPossibility() {
        Statistics stat = testingValues();
        assertNotNull(stat);
    }

    @Test
    public void canComputeExpectedValue() {
        Statistics stat = testingValues();
double expectedValue = stat.computeExpectedValue();
        assertEquals(2.3, expectedValue, DELTA);
    }

    @Test
    public void canComputeSecondMoment() {
        Statistics stat = testingValues();
        double secondMoment = stat.computeInitialMoment(2);
        assertEquals(5.7, secondMoment, DELTA);
    }
    @Test
    public void canComputeDispersion() {
        Statistics stat = testingValues();
        double dispersion = stat.computeDispersion();
        assertEquals(0.41, dispersion, DELTA);
    }


    @Test(expected = InvalidParameterException.class)
    public void cannotCreateWrongPossibility() {
        double []x = new double[]{1, 2, 3};
        double []p = new double[]{0.1, -0.5, 0.4};
        Statistics stat = new Statistics(x, p);
    }
    @Test(expected = InvalidParameterException.class)
    public void cannotCreateWrongPossibilitySum() {
        double []x = new double[]{1, 2, 3};
        double []p = new double[]{0.1, 0.5, 0.9};
        Statistics stat = new Statistics(x, p);
    }

    @Test(expected = InvalidParameterException.class)
    public void cannotCreateWhenDifferentArraySizes() {
        new Statistics(new double[]{1, 2}, new double[]{1});
    }

    @Test
    public void canComputeInitialMoment() {
        Statistics stat = testingValues();
        double initialMoment = stat.computeInitialMoment(3);
        assertEquals(14.9, initialMoment, DELTA);

    }

    @Test
    public void canComputeInitialMomentWithNegativeValues() {
        Statistics stat = testingNegativeValues();
        double initialMoment = stat.computeInitialMoment(3);
        assertEquals(-14.9, initialMoment, DELTA);
    }

    @Test
    public void canComputeFourthCentralMoment() {
        Statistics stat = testingValues();
        double centralMoment = stat.computeCentralMoment(4);
        assertEquals(0.3857, centralMoment, DELTA);
    }
    @Test
    public void canComputeThirdCentralMoment() {
        Statistics stat = testingValues();
        double centralMoment = stat.computeCentralMoment(3);
        assertEquals(-0.096, centralMoment, DELTA);
    }
    @Test
    public void canComputeAbsoluteInitialMoment() {
        Statistics stat = testingValues();
        double absoluteInitialMoment = stat.computeAbsoluteInitialMoment(3);
        assertEquals(14.9, absoluteInitialMoment, DELTA);
    }

    @Test
    public void canComputeAbsoluteInitialMomentWithNegativeValues() {
        Statistics stat = testingNegativeValues();
        double absoluteInitialMoment = stat.computeAbsoluteInitialMoment(3);
        assertEquals(14.9, absoluteInitialMoment, DELTA);
    }

    @Test
    public void canComputeAbsoluteCentralMoment() {
        Statistics stat = testingValues();
        double absoluteCentralMoment = stat.computeAbsoluteCentralMoment(3);
        assertEquals(0.3704, absoluteCentralMoment, DELTA);
    }

    @Test
    public void canComputeAbsoluteCentralMomentWithNegativeValues() {
        Statistics stat = testingNegativeValues();
        double absoluteCentralMoment = stat.computeAbsoluteCentralMoment(3);
        assertEquals(0.3704, absoluteCentralMoment, DELTA);
    }
    @Test
    public void canComputeFirstFactorialMoment() {
        Statistics stat = testingValues();
        double factorialMoment = stat.computeFactorialMoment(1);
        assertEquals(2.3, factorialMoment, DELTA);
    }

    @Test
    public void canComputeFirstFactorialMomentWithNegativeValues() {
        Statistics stat = testingNegativeValues();
        double factorialMoment = stat.computeFactorialMoment(1);
        assertEquals(-2.3, factorialMoment, DELTA);
    }

    @Test
    public void canComputeSecondFactorialMoment() {
        Statistics stat = testingValues();
        double factorialMoment = stat.computeFactorialMoment(2);
        assertEquals(3.4, factorialMoment, DELTA);
    }

    @Test
    public void canComputeSecondFactorialMomentWithNegativeValues() {
        Statistics stat = testingNegativeValues();
        double factorialMoment = stat.computeFactorialMoment(2);
        assertEquals(8, factorialMoment, DELTA);
    }
}

