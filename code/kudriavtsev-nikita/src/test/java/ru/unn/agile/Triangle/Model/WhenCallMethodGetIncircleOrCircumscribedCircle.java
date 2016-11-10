package ru.unn.agile.Triangle.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class WhenCallMethodGetIncircleOrCircumscribedCircle {
    private final double delta = 0.001;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Triangle simpleTriangle = new Triangle(new Point2D(0, 0),
                                               new Point2D(0, 1),
                                               new Point2D(1, 0));

        Triangle difficultTriangle = new Triangle(new Point2D(2, 0),
                                                  new Point2D(-0.5, 0.4),
                                                  new Point2D(0, -4));

        Triangle degeneracyTriangle = new Triangle(new Point2D(2, 0),
                                                   new Point2D(2, 0),
                                                   new Point2D(0, 0));

        return Arrays.asList(new Object[][] {
                { new Point2D(0.5, 0.5), 0.70710678, simpleTriangle.getCircCircle()},
                { new Point2D(0.44259259, -1.7212963), 2.32128817, difficultTriangle.getCircCircle() },
                { new Point2D(Double.NaN, Double.NaN), Double.NaN, degeneracyTriangle.getCircCircle() },
                { new Point2D(0.29289321, 0.29289321), 0.29289321, simpleTriangle.getIncircleCircle() },
                { new Point2D(0.57911321, -0.72936959), 0.94469579, difficultTriangle.getIncircleCircle() },
                { new Point2D(2, 0), 0, degeneracyTriangle.getIncircleCircle() }
        });
    }

    private Point2D expectCenter;
    private double expectRadius;
    private Circle actualCircle;

    public WhenCallMethodGetIncircleOrCircumscribedCircle(final Point2D expectCenter,
                                                          final double expectRadius,
                                                          final Circle actualCircle) {
        this.expectCenter = expectCenter;
        this.expectRadius = expectRadius;
        this.actualCircle = actualCircle;
    }

    @Test
    public void weGetValidNumberOfObjectCircle() {
        double expectX = expectCenter.getX();
        double expectY = expectCenter.getY();

        Point2D actualCenter = actualCircle.getCenter();

        assertEquals(expectX, actualCenter.getX(), delta);
        assertEquals(expectY, actualCenter.getY(), delta);
        assertEquals(expectRadius, actualCircle.getRadius(), delta);
    }
}
