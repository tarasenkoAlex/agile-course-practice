package ru.unn.agile.Triangle.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class WhenCallMethodGetIncircleOrCircumscribedCircle {
    private final double delta = 0.001;
    private static final double NAN = Double.NaN;
    private static final Triangle SIMPLE_TRIANGLE = new Triangle(new Point2D(0, 0),
                new Point2D(0, 1),
                new Point2D(1, 0));
    private static final Triangle DIFFICULT_TRIANGLE = new Triangle(new Point2D(2, 0),
            new Point2D(-0.5, 0.4),
            new Point2D(0, -4));
    private static final Triangle DEGENERACY_TRIANGLE = new Triangle(new Point2D(2, 0),
            new Point2D(2, 0),
            new Point2D(0, 0));

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        List<Object[][]> list = Arrays.asList(
                getTestDataCircumscribedCircle(),
                getTestDataInscribedCircle()
        );

        List<Object[]> dataSet = new ArrayList<Object[]>();

        for (int i = 0; i < list.size(); i++) {
            Object[][] objects = list.get(0);
            for (int j = 0; j < objects[0].length; j++) {
                dataSet.add(objects[j]);
            }
        }

        return dataSet;
    }

    private static Object[][] getTestDataInscribedCircle() {
        return new Object[][]{
                {
                        getPoint(0.292, 0.292), 0.292, SIMPLE_TRIANGLE.getIncircleCircle()
                },
                {
                        getPoint(0.579, -0.729), 0.944, DIFFICULT_TRIANGLE.getIncircleCircle()
                },
                {
                        getPoint(2, 0), 0, DEGENERACY_TRIANGLE.getIncircleCircle()
                }
        };
    }

    private static Object[][] getTestDataCircumscribedCircle() {
        return new Object[][]{
                {
                        getPoint(0.5, 0.5), 0.707, SIMPLE_TRIANGLE.getCircCircle()
                },
                {
                        getPoint(0.442, -1.721), 2.321, DIFFICULT_TRIANGLE.getCircCircle()
                },
                {
                        getPoint(NAN, NAN), NAN, DEGENERACY_TRIANGLE.getCircCircle()
                }
        };
    }

    private static Point2D getPoint(final double x, final double y) {
        return new Point2D(x, y);
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
