package ru.unn.agile.Triangle.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {
    private final double delta = 0.001;
    private Point2D simpleA;
    private Point2D simpleB;
    private Point2D simpleC;
    private Triangle simpleTriangle;
    private Triangle difficultTriangle;
    private Triangle degeneracyTriangle;

    @Before
    public void initializeSimpleTriangle() {
        simpleA = new Point2D(0, 0);
        simpleB = new Point2D(0, 1);
        simpleC = new Point2D(1, 0);
        simpleTriangle = new Triangle(simpleA, simpleB, simpleC);

        difficultTriangle = new Triangle(new Point2D(2, 0),
                                         new Point2D(-0.5, 0.4),
                                         new Point2D(0, -4));

        degeneracyTriangle = new Triangle(new Point2D(2, 0),
                                          new Point2D(2, 0),
                                          new Point2D(0, 0));
    }
    @Test
    public void canCreateTriangleWithInitialValues() {
        assertNotNull(simpleTriangle);
    }

    @Test
    public void canSetInitialValueOfTrianglePoints() {
        assertEquals(simpleA, simpleTriangle.getA());
        assertEquals(simpleB, simpleTriangle.getB());
        assertEquals(simpleC, simpleTriangle.getC());
    }

    @Test
    public void canSetInitialValueOfLeghtOfSidesForSimpleTriangle() {
        double expectAB = 1;
        double expectBC = 1.41421356237;
        double expectAC = 1;

        assertEquals(expectAB, simpleTriangle.getAB(), delta);
        assertEquals(expectBC, simpleTriangle.getBC(), delta);
        assertEquals(expectAC, simpleTriangle.getAC(), delta);
    }

    @Test
    public void canSetInitialValueOfLeghtOfSidesForDegeneracyTriangle() {
        double expectAB = 0;
        double expectBC = 2;
        double expectAC = 2;

        assertEquals(expectAB, degeneracyTriangle.getAB(), delta);
        assertEquals(expectBC, degeneracyTriangle.getBC(), delta);
        assertEquals(expectAC, degeneracyTriangle.getAC(), delta);
    }

    @Test
    public void canReturnValueOfPerimeterForSimpleTriangle() {
        double expected = 3.41421356237;

        double actual = simpleTriangle.perimeter();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReturnValueOfPerimeterForDifficultTriangle() {
        double expected = 11.4322517003309187;

        double actual = difficultTriangle.perimeter();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReturnValueOfPerimeterForDegeneracyTriangle() {
        double expected = 4;

        double actual = degeneracyTriangle.perimeter();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReturnValueOfAreaForSimpleTriangle() {
        double expected = 0.5;

        double actual = simpleTriangle.area();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReturnValueOfAreaForDifficultTriangle() {
        double expected = 5.4000000000000008655148303848697;

        double actual = difficultTriangle.area();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReurnValueOfAreaForDegeneracyTriangle() {
        double expected = 0;

        double actual = degeneracyTriangle.area();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReturnIncircleCircleForSimpleTriangle() {
        double expectedX, expectedY, expectedRadius;
        expectedX = 0.29289321881281147000422260516823;
        expectedY = 0.29289321881281147000422260516823;
        expectedRadius = 0.29289321881281147000422260516823;

        Circle actual = simpleTriangle.getIncircleCircle();
        double actualX = actual.getCenter().getX();
        double actualY = actual.getCenter().getY();
        double actualRadius = actual.getRadius();

        assertEquals(expectedX, actualX, delta);
        assertEquals(expectedY, actualY, delta);
        assertEquals(expectedRadius, actualRadius, delta);
    }

    @Test
    public void canReturnIncircleCircleForDifficultTriangle() {
        double expectedX, expectedY, expectedRadius;
        expectedX = 0.57911321;
        expectedY = -0.72936959;
        expectedRadius = 0.94469579;

        Circle actual = difficultTriangle.getIncircleCircle();
        double actualX = actual.getCenter().getX();
        double actualY = actual.getCenter().getY();
        double actualRadius = actual.getRadius();

        assertEquals(expectedX, actualX, delta);
        assertEquals(expectedY, actualY, delta);
        assertEquals(expectedRadius, actualRadius, delta);
    }

    @Test
    public void canReturnIncircleCircleForDegeneracyTriangle() {
        double expectedX, expectedY, expectedRadius;
        expectedX = 2;
        expectedY = 0;
        expectedRadius = 0;

        Circle actual = degeneracyTriangle.getIncircleCircle();
        double actualX = actual.getCenter().getX();
        double actualY = actual.getCenter().getY();
        double actualRadius = actual.getRadius();

        assertEquals(expectedX, actualX, delta);
        assertEquals(expectedY, actualY, delta);
        assertEquals(expectedRadius, actualRadius, delta);
    }

    @Test
    public void canReturnCircumscribedCircleForSimpleTriangle() {
        double expectedX, expectedY, expectedRadius;
        expectedX = 0.5;
        expectedY = 0.5;
        expectedRadius = 0.70710678118;

        Circle actual = simpleTriangle.getCircCircle();
        double actualX = actual.getCenter().getX();
        double actualY = actual.getCenter().getY();
        double actualRadius = actual.getRadius();

        assertEquals(expectedX, actualX, delta);
        assertEquals(expectedY, actualY, delta);
        assertEquals(expectedRadius, actualRadius, delta);
    }

    @Test
    public void canReturnCircumscribedCircleForDifficultTriangle() {
        double expectedX, expectedY, expectedRadius;
        expectedX = 0.44259259;
        expectedY = -1.7212963;
        expectedRadius = 2.32128817;

        Circle actual = difficultTriangle.getCircCircle();
        double actualX = actual.getCenter().getX();
        double actualY = actual.getCenter().getY();
        double actualRadius = actual.getRadius();

        assertEquals(expectedX, actualX, delta);
        assertEquals(expectedY, actualY, delta);
        assertEquals(expectedRadius, actualRadius, delta);
    }

    @Test
    public void canReturnCircumscribedCircleForDegeneracyTriangle() {
        double expectedX, expectedY, expectedRadius;
        expectedX = Double.NaN;
        expectedY = Double.NaN;
        expectedRadius = Double.NaN;

        Circle actual = degeneracyTriangle.getCircCircle();
        double actualX = actual.getCenter().getX();
        double actualY = actual.getCenter().getY();
        double actualRadius = actual.getRadius();

        assertEquals(expectedX, actualX, delta);
        assertEquals(expectedY, actualY, delta);
        assertEquals(expectedRadius, actualRadius, delta);
    }
}
