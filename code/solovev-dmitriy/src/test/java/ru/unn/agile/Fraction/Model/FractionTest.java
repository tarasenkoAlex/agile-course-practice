package ru.unn.agile.Fraction.Model;

import static ru.unn.agile.Fraction.Model.Fraction.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class FractionTest {
    @Test
    public void canCreateFractionWithDefaultValue() {
        Fraction number = new Fraction();
        assertNotNull(number);
    }

    @Test
    public void canCreateFractionWithInitialIntegerValue() {
        Fraction number = new Fraction(123);
        assertNotNull(number);
    }

    @Test
    public void canCreateIrreducibleFraction() {
        Fraction number = new Fraction(1, 2);
        assertNotNull(number);
    }

    @Test
    public void canCreateReducibleFraction() {
        Fraction number = new Fraction(2, 4);
        assertNotNull(number);
    }

    @Test
    public void canCreateFractionWithNegativeInteger() {
        Fraction number = new Fraction(-10);
        assertNotNull(number);
    }

    @Test
    public void canCreateFractionWithNegativesNumAndDenom() {
        Fraction number = new Fraction(-310, -890);
        assertNotNull(number);
    }

    @Test
    public void canCreateFractionWithNegativeNum() {
        Fraction number = new Fraction(-310, 890);
        assertNotNull(number);
    }

    @Test
    public void canCreateFractionWithNegativeDenom() {
        Fraction number = new Fraction(310, -890);
        assertNotNull(number);
    }

    @Test
    public void isFractionWithDefaultValueEqualsZero() {
        Fraction number = new Fraction();
        assertEquals(number, 0);
        assertEquals(number.getNumerator(), 0);
        assertEquals(number.getDenominator(), 1);
    }

    @Test
    public void canSetInitialIntegerValue() {
        Fraction number = new Fraction(123);
        assertEquals(number, 123);
        assertEquals(number.getNumerator(), 123);
        assertEquals(number.getDenominator(), 1);
    }

    @Test
    public void canSetInitialIrreducibleFraction1div2() {
        Fraction number = new Fraction(1, 2);
        assertEquals(number.getNumerator(), 1);
        assertEquals(number.getDenominator(), 2);
    }

    @Test
    public void canSetInitialReducibleFraction2div4() {
        Fraction number = new Fraction(2, 4);
        assertEquals(number.getNumerator(), 1);
        assertEquals(number.getDenominator(), 2);
    }

    @Test
    public void canSetInitialReducibleFraction4div2() {
        Fraction number = new Fraction(4, 2);
        assertEquals(number.getNumerator(), 2);
        assertEquals(number.getDenominator(), 1);
    }

    @Test
    public void isEqualFractionsEqual() {
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(1, 2);
        assertEquals(a, b);
    }

    @Test
    public void isNotEqualFractionsNotEqualByDenominator() {
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(1, 3);
        assertNotEquals(a, b);
    }

    @Test
    public void isNotEqualFractionsNotEqualByNumerator() {
        Fraction a = new Fraction(4, 7);
        Fraction b = new Fraction(5, 7);
        assertNotEquals(a, b);
    }

    @Test
    public void canMultiply() {
        Fraction a = new Fraction(3, 5);
        Fraction b = new Fraction(740, 26);
        Fraction r = multiply(a, b);
        assertEquals(new Fraction(222, 13), r);
    }

    @Test
    public void canDivide() {
        Fraction a = new Fraction(3, 5);
        Fraction b = new Fraction(740, 26);
        Fraction r = divide(a, b);
        assertEquals(new Fraction(39, 1850), r);
    }

    @Test
    public void canAdd() {
        Fraction a = new Fraction(3, 5);
        Fraction b = new Fraction(740, 26);
        Fraction r = sum(a, b);
        assertEquals(new Fraction(1889, 65), r);
    }

    @Test
    public void canSubtract() {
        Fraction a = new Fraction(3, 5);
        Fraction b = new Fraction(740, 26);
        Fraction r = subtract(a, b);
        assertEquals(new Fraction(-1811, 65), r);
    }

    @Test(expected = ArithmeticException.class)
    public void canNotCreateDenominatorEqualZero() {
        new Fraction(1, 0);
    }

    @Test(expected = ArithmeticException.class)
    public void canNotDivideByZero() {
        Fraction zero = new Fraction(0);
        Fraction a = new Fraction(3, 4);
        a = divide(a, zero);
    }
}
