package ru.unn.agile.vec3;

import org.junit.Test;

import static org.junit.Assert.*;

public class Vec3Test {
    private Vector3 firstVec;
    private Vector3 secondVec;

    @Test
    public void canInitializeFromValues() {
        firstVec = new Vector3(6.0, 6.0, 6.0);

        assert firstVec.equals(6.0, 6.0, 6.0);
    }

    @Test
    public void canInitializeFromArray() {
        firstVec = new Vector3(new double[]{9.0, 9.0, 9.0});

        assert firstVec.equals(9.0, 9.0, 9.0);
    }

    @Test
    public void isEqualTwoVectors() {
        firstVec  = new Vector3(new double[] {9.0, 9.0, 9.0});
        secondVec = new Vector3(9.0, 9.0, 9.0);

        assertEquals(firstVec, secondVec);
    }

    @Test
    public void isCorrectDotProduct() {
        firstVec  = new Vector3(8.0, 6.0, 6.0);
        secondVec = new Vector3(5.0, 6.0, 4.0);

        final double expectedDotProduct = 100.0;
        final double actualDotProduct   = firstVec.dot(secondVec);

        assertEquals(expectedDotProduct, actualDotProduct, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectNorm() {
        firstVec = new Vector3(9.0, 2.0, 6.0);

        final double expectedNorm = 11.0;
        final double actualNorm   = firstVec.getNorm();

        assertEquals(expectedNorm, actualNorm, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectNormalize() {
        final double invNorm = 1.0 / Math.sqrt(14.0);
        firstVec             = new Vector3(1.0, 2.0, 3.0);
        secondVec            = new Vector3(1.0 * invNorm, 2.0 * invNorm, 3.0 * invNorm);

        Vector3 normalized = firstVec.normalize();

        assertEquals(normalized, secondVec);
    }

    @Test
    public void normIsEqualZero() {
        firstVec = new Vector3(0.0, 0.0, 0.0);

        firstVec.normalize();

        assertEquals(firstVec, new Vector3(0.0, 0.0, 0.0));
    }

    @Test
    public void isCorrectCrossProduct() {
        firstVec  = new Vector3(1.0, 0.0, 0.0);
        secondVec = new Vector3(0.0, 1.0, 0.0);

        final Vector3 anAxisZ = firstVec.cross(secondVec);

        assert anAxisZ.equals(0.0, 0.0, 1.0);
    }

    @Test
    public void crossProductOfCollinearVectorsReturnZeroVector() {
        firstVec = new Vector3(1.0, 0.0, 0.0);
        secondVec = new Vector3(0.1, 0.0, 0.0);

        final Vector3 vector = firstVec.cross(secondVec);

        assertEquals(vector, new Vector3(0.0, 0.0, 0.0));
    }

    @Test (expected = NullPointerException.class)
    public void isCorrectEqualsNotNullObjectWithNullObject() {
        firstVec = new Vector3();

        assert firstVec.equals(null);
    }

    @Test
    public void isCorrectEqualsVectorWithNotVector() {
        final double value = 666.0;
        firstVec = new Vector3();

        assertNotEquals(firstVec, value);
    }

    @Test
    public void isCorrectEqualsVectorWithObject() {
        firstVec  = new Vector3(6.0, 6.0, 6.0);
        Object vector = new Vector3(6.0, 6.0, 6.0);

        assertEquals(firstVec, vector);
    }

    @Test
    public void checkCorrectHashOfTwoSameVectors() {
        firstVec  = new Vector3(6.0, 6.0, 6.0);
        secondVec = new Vector3(6.0, 6.0, 6.0);

        assertEquals(firstVec.hashCode(), secondVec.hashCode());
    }

    @Test
    public void isCorrectToStringForVector() {
        firstVec  = new Vector3(6.0, 6.0, 6.0);

        String actual = firstVec.toString();
        String expected = "(6.0000, 6.0000, 6.0000)";

        assertEquals(expected, actual);
    }
}
