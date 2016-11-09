package com.github.rybval.Polynomial.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MonomialTest {
    public static final double EPSILON = 0.00001;

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
        int power = 5;
        double coefficient = 5.4;
        Monomial monomial = new Monomial(power, coefficient);

        assertEquals(power, monomial.getPower());
    }

    @Test
    public void canGetCoefficient() {
        int power = 5;
        double coefficient = 5.4;
        Monomial monomial = new Monomial(power, coefficient);

        assertEquals(coefficient, monomial.getCoefficient(), EPSILON);
    }

    @Test
    public void canAddMonomialWithSamePower() {
        int power = 5;
        Monomial monomial1 = new Monomial(power, 5.4);
        Monomial monomial2 = new Monomial(power, 3.2);

        Monomial monomialsumm = monomial1.add(monomial2);

        assertEquals(5.4 + 3.2, monomialsumm.getCoefficient(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantAddMonomialWithDifferentPower() {
        Monomial monomial1 = new Monomial(1, 1);
        Monomial monomial2 = new Monomial(2, 1);

        Monomial monomialsumm = monomial1.add(monomial2);
    }

    @Test
    public void canNegateMonomial() {
        int power = 5;
        double coefficient = 5.4;
        Monomial monomial = new Monomial(power, coefficient);

        assertEquals(-coefficient, monomial.negate().getCoefficient(),
                     EPSILON);
    }

    @Test
    public void canSubtractMonomialWithSamePower() {
        int power = 5;
        Monomial minuend = new Monomial(power, 5.4);
        Monomial subtrahend = new Monomial(power, 3.2);

        Monomial diff = minuend.subtract(subtrahend);

        assertEquals(5.4 - 3.2, diff.getCoefficient(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantSubtractMonomialWithDifferentPower() {
        Monomial minuend = new Monomial(1, 1);
        Monomial subtrahend = new Monomial(2, 1);

        Monomial diff = minuend.add(subtrahend);
    }
}
