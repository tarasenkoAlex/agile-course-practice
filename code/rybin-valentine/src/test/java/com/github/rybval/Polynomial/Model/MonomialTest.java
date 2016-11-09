package com.github.rybval.Polynomial.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MonomialTest {
    private static final int POWER = 5;
    private static final double COEFFICIENT = 5.4;
    private static final double EPSILON = 0.00001;

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
        assertEquals(COEFFICIENT, monomial.getCoefficient(), EPSILON);
    }

    @Test
    public void canAddMonomialWithSamePower() {
        Monomial monomial1 = new Monomial(POWER, 5.4);
        Monomial monomial2 = new Monomial(POWER, 3.2);

        Monomial summ = monomial1.add(monomial2);

        assertEquals(5.4 + 3.2, summ.getCoefficient(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantAddMonomialWithDifferentPower() {
        Monomial monomial1 = new Monomial(1, COEFFICIENT);
        Monomial monomial2 = new Monomial(2, COEFFICIENT);

        Monomial summ = monomial1.add(monomial2);
    }

    @Test
    public void canNegateMonomial() {
        Monomial monomial = new Monomial(POWER, COEFFICIENT);
        assertEquals(-COEFFICIENT, monomial.negate().getCoefficient(),
                     EPSILON);
    }

    @Test
    public void canSubtractMonomialWithSamePower() {
        Monomial minuend = new Monomial(POWER, 5.4);
        Monomial subtrahend = new Monomial(POWER, 3.2);

        Monomial diff = minuend.subtract(subtrahend);

        assertEquals(5.4 - 3.2, diff.getCoefficient(), EPSILON);
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
        assertEquals(product.getCoefficient(), 5.4 * 2.1, EPSILON);
    }

    @Test
    public void canMultiplyByDouble() {
        Monomial multiplicand = new Monomial(POWER, 5.4);
        double multiplier = 2.1;

        Monomial product = multiplicand.multiply(multiplier);

        assertEquals(5.4 * 2.1, product.getCoefficient(), EPSILON);
    }

    @Test
    public void canDivideByDouble() {
        Monomial dividend = new Monomial(POWER, 5.4);
        double divider = 2.1;

        Monomial quotient = dividend.divide(divider);

        assertEquals(5.4 / 2.1, quotient.getCoefficient(), EPSILON);
    }

    @Test
    public void canDivideByMonomial() {
        Monomial dividend = new Monomial(5, 5.4);
        Monomial divider = new Monomial(3, 2.1);

        Monomial quotient = dividend.divide(divider);

        assertEquals(5 - 3, quotient.getPower());
        assertEquals(5.4 / 2.1, quotient.getCoefficient(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantDivideByMonomialWithBiggerPower() {
        Monomial dividend = new Monomial(5, COEFFICIENT);
        Monomial divider = new Monomial(7, COEFFICIENT);

        Monomial quotient = dividend.divide(divider);
    }
}
