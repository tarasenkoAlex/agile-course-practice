package com.github.rybval.polynomial.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MonomialTest {
    private static final int POWER = 5;
    private static final double COEFFICIENT = 5.4;

    @Test
    public void canCreateMonomial() {
        Monomial monomial = new Monomial();
        assertNotNull(monomial);
    }

    @Test
    public void canCreateMonomialWithInitialValues() {
        Monomial monomial = new Monomial(1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantCreateMonomialWithNegativePower() {
        Monomial monomial = new Monomial(-1, 1);
    }

    @Test
    public void canGetPower() {
        Monomial monomial = new Monomial(POWER, COEFFICIENT);
        assertEquals(POWER, monomial.getPower());
    }

    @Test
    public void canGetCoefficient() {
        Monomial monomial = new Monomial(POWER, COEFFICIENT);
        assertEquals(COEFFICIENT, monomial.getCoefficient(), Double.MIN_VALUE);
    }

    @Test
    public void canAddMonomialWithSamePower() {
        Monomial monomial1 = new Monomial(POWER, 5.4);
        Monomial monomial2 = new Monomial(POWER, 3.2);

        Monomial summ = monomial1.add(monomial2);

        assertEquals(5.4 + 3.2, summ.getCoefficient(), Double.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantAddMonomialWithDifferentPower() {
        Monomial monomial1 = new Monomial(1, COEFFICIENT);
        Monomial monomial2 = new Monomial(2, COEFFICIENT);

        Monomial summ = monomial1.add(monomial2);
    }

    @Test
    public void canNegateMonomial() {
        Monomial positivemonomial = new Monomial(POWER, COEFFICIENT);
        Monomial negativeMonomial = new Monomial(POWER, -COEFFICIENT);

        assertEquals(negativeMonomial, positivemonomial.negate());
    }

    @Test
    public void canSubtractMonomialWithSamePower() {
        Monomial minuend = new Monomial(POWER, 5.4);
        Monomial subtrahend = new Monomial(POWER, 3.2);

        Monomial diff = minuend.subtract(subtrahend);

        assertEquals(5.4 - 3.2, diff.getCoefficient(), Double.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantSubtractMonomialWithDifferentPower() {
        Monomial minuend = new Monomial(1, COEFFICIENT);
        Monomial subtrahend = new Monomial(2, COEFFICIENT);

        Monomial diff = minuend.add(subtrahend);
    }

    @Test
    public void canMultiplyByMonomial() {
        Monomial multiplicand = new Monomial(5, 5.4);
        Monomial multiplier = new Monomial(3, 2.1);

        Monomial product = multiplicand.multiply(multiplier);

        assertEquals(product.getPower(), 5 + 3);
        assertEquals(product.getCoefficient(), 5.4 * 2.1, Double.MIN_VALUE);
    }

    @Test
    public void canMultiplyByDouble() {
        Monomial multiplicand = new Monomial(POWER, 5.4);
        double multiplier = 2.1;

        Monomial product = multiplicand.multiply(multiplier);

        assertEquals(5.4 * 2.1, product.getCoefficient(), Double.MIN_VALUE);
    }

    @Test
    public void canDivideByDouble() {
        Monomial dividend = new Monomial(POWER, 5.4);
        double divider = 2.1;

        Monomial quotient = dividend.divide(divider);

        assertEquals(5.4 / 2.1, quotient.getCoefficient(), Double.MIN_VALUE);
    }

    @Test
    public void canDivideByMonomial() {
        Monomial dividend = new Monomial(5, 5.4);
        Monomial divider = new Monomial(3, 2.1);

        Monomial quotient = dividend.divide(divider);

        assertEquals(5 - 3, quotient.getPower());
        assertEquals(5.4 / 2.1, quotient.getCoefficient(), Double.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantDivideByMonomialWithBiggerPower() {
        Monomial dividend = new Monomial(5, COEFFICIENT);
        Monomial divider = new Monomial(7, COEFFICIENT);

        Monomial quotient = dividend.divide(divider);
    }

    @Test
    public void isEqualMonomialsCompareCorrect() {
        Monomial monomial1 = new Monomial(POWER, COEFFICIENT);
        Monomial monomial2 = new Monomial(POWER, COEFFICIENT);

        assertEquals(monomial1, monomial2);
    }

    @Test
    public void isDifferentMonomialsCompareCorrect() {
        Monomial monomial1 = new Monomial(5, 5.4);
        Monomial monomial2 = new Monomial(3, 3.2);

        assertNotEquals(monomial1, monomial2);
    }

    @Test
    public void isDifferentMonomialsWithSamePowerCompareCorrect() {
        Monomial monomial1 = new Monomial(POWER, 5.4);
        Monomial monomial2 = new Monomial(POWER, 3.2);

        assertNotEquals(monomial1, monomial2);
    }

    @Test
    public void isCompareMonomialWithNullCorrect() {
        Monomial monomial1 = new Monomial(POWER, COEFFICIENT);
        Monomial monomial2 = null;

        assertNotEquals(monomial1, monomial2);
    }

    @Test
    public void areHashesSameForEqualMonomials() {
        Monomial monomial1 = new Monomial(POWER, COEFFICIENT);
        Monomial monomial2 = new Monomial(POWER, COEFFICIENT);

        assertEquals(monomial1.hashCode(), monomial2.hashCode());
    }

    @Test
    public void areHashesDifferentForDifferentMonomials() {
        Monomial monomial1 = new Monomial(5, 5.4);
        Monomial monomial2 = new Monomial(3, 3.2);

        assertNotEquals(monomial1.hashCode(), monomial2.hashCode());
    }

    @Test
    public void canConvertToString() {
        Monomial monomial = new Monomial(5, 5.4);
        assertEquals("5.4*x^5", monomial.toString());
    }

    @Test
    public void canCreateMonomialFromString() {
        assertEquals(new Monomial(5, 5.4), Monomial.fromString("5.4*x^5"));
    }

    @Test
    public void isDefaultInitCorrect() {
        Monomial defaultMonomial = new Monomial();
        Monomial monomial = new Monomial(0, 0);

        assertEquals(defaultMonomial, monomial);
    }

    @Test
    public void canCreateMonomialFromDouble() {
        assertNotNull(new Monomial(COEFFICIENT));
    }

    @Test
    public void canCreateMonomialFromStringWithoutVariable() {
        assertEquals(new Monomial(0, 5.4), Monomial.fromString("5.4"));
    }

    @Test
    public void canCreateMonomialFromStringWithoutCoefficient() {
        assertEquals(new Monomial(5, 1), Monomial.fromString("x^5"));
    }

    @Test
    public void canCreateNegativeMonomialFromString() {
        assertEquals(new Monomial(5, -5.4), Monomial.fromString("-5.4*x^5"));
    }

    @Test
    public void canCreateNegativeMonomialFromStringWithoutCoefficient() {
        assertEquals(new Monomial(5, -1), Monomial.fromString("-x^5"));
    }

    @Test
    public void canCreateNegativeMonomialFromStringWithoutVariable() {
        assertEquals(new Monomial(0, -5), Monomial.fromString("-5"));
    }

    @Test
    public void canCreatePositiveMonomialFromString() {
        assertEquals(new Monomial(5, 54), Monomial.fromString("+54*x^5"));
    }

    @Test
    public void canCreatePositiveMonomialFromStringWithoutCoefficient() {
        assertEquals(new Monomial(5, 1), Monomial.fromString("+x^5"));
    }

    @Test
    public void canCreatePositiveMonomialFromStringWithoutVariable() {
        assertEquals(new Monomial(0, 5), Monomial.fromString("+5"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantCreateMonomialWithWrongFormat() {
        Monomial.fromString("-+x^5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantCreateMonomialWithNegativePowerFromString() {
        Monomial.fromString("5.4*x^-5");
    }

    @Test
    public void canCreateMonomialFromStringWithoutPower() {
        assertEquals(new Monomial(1, 5.4), Monomial.fromString("5.4*x"));
    }

    @Test
    public void canCreateMonomialFromStringWithOnlyVariable() {
        assertEquals(new Monomial(1, 1), Monomial.fromString("x"));
    }

    @Test
    public void canCreateMonomialFromStringWithOnlyNegativeVariable() {
        assertEquals(new Monomial(1, -1), Monomial.fromString("-x"));
    }

    @Test
    public void canCreateMonomialFromStringWithOnlyPositiveVariable() {
        assertEquals(new Monomial(1, 1), Monomial.fromString("+x"));
    }
}
