package ru.unn.agile.SolvingQuadraticEquation.model;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static ru.unn.agile.SolvingQuadraticEquation.model.SolvingQuadraticEquation.*;

public class SolvingQuadraticEquationTest {

    private double a;
    private double b;
    private double c;
    private double[] expected;
    private double[] actual;

    private void inputABC(final double a, final double b, final double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private void initialization(final double a, final double b, final double c) {
        inputABC(a, b, c);

        this.expected = new double[0];
    }

    private void initialization(final double a, final double b, final double c, final double x) {
        inputABC(a, b, c);

        this.expected = new double[1];

        this.expected[0] = x;
    }

    private void initialization(final double a, final double b, final double c,
                                final double x1, final double x2) {
        inputABC(a, b, c);

        this.expected = new double[2];

        this.expected[0] = x1;
        this.expected[1] = x2;
    }

    private void initialization(final double a, final double b, final double c,
                                final double x1, final double x2, final double x3) {
        inputABC(a, b, c);

        this.expected = new double[3];

        this.expected[0] = x1;
        this.expected[1] = x2;
        this.expected[2] = x3;
    }

    @Test
    public void noRootTest() {
        initialization(1, 4, 5);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void oneRootTest() {
        initialization(1, -4, 4, 2);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void twoRootsTest() {
        initialization(2, -5, 2, 2, 0.5);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void aIsZero() {
        initialization(0, 4, 8, -2);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void bIsZeroTwoRoots() {
        initialization(2, 0, -8, 2, -2);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void bIsZeroNoRoot() {
        initialization(2, 0, 8);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void cIsZero() {
        initialization(3, 6, 0, 0, -2);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void aIsZeroBIsZero() {
        initialization(0, 0, 8);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void aIsZeroCIsZero() {
        initialization(0, 6, 0, 0);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void bIsZeroCIsZero() {
        initialization(4, 0, 0, 0);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }

    @Test
    public void allAreZero() {
        initialization(0, 0, 0, 0, 0, 0);

        this.actual = calc(a, b, c);

        assertArrayEquals(expected, actual, 0);
    }
}
