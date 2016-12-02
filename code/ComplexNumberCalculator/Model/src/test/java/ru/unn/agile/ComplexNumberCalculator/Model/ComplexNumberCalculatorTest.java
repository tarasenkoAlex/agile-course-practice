package ru.unn.agile.ComplexNumberCalculator.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Дарья on 06.11.2016.
 */
public class ComplexNumberCalculatorTest {
    private float delta = 0.0001f;

    @Test
    public void testCanAddTwoComplexNumbers() {
        ComplexNum complexNumberA = new ComplexNum(3.5f, 1.02f);
        ComplexNum complexNumberB = new ComplexNum(4.3f, 0);
        ComplexNum result = new ComplexNum(7.8f, 1.02f);
        assertEquals(result, CalculatorComplexNumber.add(complexNumberA, complexNumberB));
    }

    @Test
    public void testCanAddTwoNegativeComplexNumbers() {
        ComplexNum complexNumberA = new ComplexNum(1.8f, 1);
        ComplexNum complexNumberB = new ComplexNum(-3.24f, -3.67f);
        ComplexNum result = new ComplexNum(-1.44f, -2.67f);
        assertEquals(result, CalculatorComplexNumber.add(complexNumberA, complexNumberB));
    }

    @Test
    public void testCanSubtractTwoComplexNumbers() {
        ComplexNum complexNumberA = new ComplexNum(1, 1);
        ComplexNum complexNumberB = new ComplexNum(4, 0.56f);
        ComplexNum result = new ComplexNum(-3, 0.44f);
        assertEquals(result, CalculatorComplexNumber.sub(complexNumberA, complexNumberB));
    }

    @Test
    public void testCanMultTwoComplexNumbers() {
        ComplexNum complexNumberA = new ComplexNum(2, 1.2f);
        ComplexNum complexNumberB = new ComplexNum(0, -1);
        ComplexNum result = new ComplexNum(1.2f, -2);
        assertEquals(result, CalculatorComplexNumber.mult(complexNumberA, complexNumberB));
    }

    @Test
    public void testCanMultByZero() {
        ComplexNum complexNumberA = new ComplexNum(2, 1.2f);
        ComplexNum complexNumberB = new ComplexNum(0, 0);
        ComplexNum result = new ComplexNum(0, 0);
        assertEquals(result, CalculatorComplexNumber.mult(complexNumberA, complexNumberB));
    }

    @Test
    public void testCanDivisionTwoComplex() {
        ComplexNum complexNumberA = new ComplexNum(2, 1);
        ComplexNum complexNumberB = new ComplexNum(1, 1);
        ComplexNum result = new ComplexNum(1.5f, -0.5f);
        assertEquals(result, CalculatorComplexNumber.div(complexNumberA, complexNumberB));
    }

    @Test(expected = ArithmeticException.class)
    public void testCanNotDivisionByZero() {
        ComplexNum complexNumberA = new ComplexNum(2, 1);
        ComplexNum complexNumberB = new ComplexNum(0, 0);
        CalculatorComplexNumber.div(complexNumberA, complexNumberB);
    }

    @Test
    public void testCanAbsComplex() {
        ComplexNum complexNumber = new ComplexNum(8, 6);
        float result = 10f;
        assertEquals(result, CalculatorComplexNumber.abs(complexNumber), delta);
    }

    @Test
    public void testCanAbsNegativeComplex() {
        ComplexNum complexNumber = new ComplexNum(-8, -6);
        float result = 10f;
        assertEquals(result, CalculatorComplexNumber.abs(complexNumber), delta);
    }

    @Test
    public void testCanGetArgumentComplex() {
        ComplexNum complexNumber = new ComplexNum(4, 3);
        double result = 0.643501853;
        assertEquals(result, CalculatorComplexNumber.getArgument(complexNumber), delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCanThrowExceptionOnNullArgument() {
        ComplexNum complexNumberA = new ComplexNum(2, 1);
        CalculatorComplexNumber.div(complexNumberA, null);
    }
}
