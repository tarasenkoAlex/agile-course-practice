package ru.unn.agile.Fraction.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OperationTest {
    private Fraction l, r;

    @Before
    public void before() {
        l = new Fraction(1, 2);
        r = new Fraction(3, 4);
    }

    @Test
    public void canGetName() {
        assertNotNull(Operation.ADD.toString());
    }

    @Test
    public void canAdd() {
        Operation o = Operation.ADD;
        Fraction res = o.apply(l, r);
        assertEquals(new Fraction(5, 4), res);
    }

    @Test
    public void canSub() {
        Operation o = Operation.SUBTRACTION;
        Fraction res = o.apply(l, r);
        assertEquals(new Fraction(-1, 4), res);
    }

    @Test
    public void canMul() {
        Operation o = Operation.MULTIPLY;
        Fraction res = o.apply(l, r);
        assertEquals(new Fraction(3, 8), res);
    }

    @Test
    public void canDiv() {
        Operation o = Operation.DIVISION;
        Fraction res = o.apply(l, r);
        assertEquals(new Fraction(2, 3), res);
    }
}
