package ru.unn.agile.Triangle.Model;

public class Triangle {
    private final Point2D pointA;
    private final Point2D pointB;
    private final Point2D pointC;
    private final double lenghtAB;
    private final double lenghtBC;
    private final double lenghtAC;
    private static final int COEF_RADIUS = 4;

    public Triangle(final Point2D pointA, final Point2D pointB, final Point2D pointC) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;

        lenghtAB = getLenghtBtwTwoPoint(pointA, pointB);
        lenghtBC = getLenghtBtwTwoPoint(pointB, pointC);
        lenghtAC = getLenghtBtwTwoPoint(pointA, pointC);
    }

    public Point2D getA() {
        return pointA;
    }

    public Point2D getB() {
        return pointB;
    }

    public Point2D getC() {
        return pointC;
    }

    public double getAB() {
        return lenghtAB;
    }

    public double getBC() {
        return lenghtBC;
    }

    public double getAC() {
        return lenghtAC;
    }

    public double area() {
        double p = (lenghtAB + lenghtBC + lenghtAC) / 2;

        return Math.sqrt(p * (p - lenghtAB) * (p - lenghtBC) * (p - lenghtAC));
    }

    public double perimeter() {
        return lenghtAC + lenghtBC + lenghtAB;
    }

    public Circle getInscCircle() {
        double x0, y0, radius;
        double p = perimeter() / 2;

        y0 = (lenghtBC * pointA.getY()
              + lenghtAC * pointB.getY()
              + lenghtAB * pointC.getY())
              / (lenghtBC + lenghtAC + lenghtAB);

        x0 = (lenghtBC * pointA.getX()
              + lenghtAC * pointB.getX()
              + lenghtAB * pointC.getX())
              / (lenghtBC + lenghtAC + lenghtAB);

        radius = Math.sqrt((p - lenghtAB) * (p - lenghtBC) * (p - lenghtAC) / p);

        return new Circle(new Point2D(x0, y0), radius);
    }

    public Circle getCircCircle() {
        double x0, y0, radius;

        double d = 2 * (pointA.getX() * (pointB.getY() - pointC.getY())
                        + pointB.getX() * (pointC.getY() - pointA.getY())
                        + pointC.getX() * (pointA.getY() - pointB.getY()));

        double area = area();

        if (area == 0) {
            return new Circle(new Point2D(Double.NaN, Double.NaN), Double.NaN);
        }

        double aA = sumOfSquareNumber(pointA.getX(), pointA.getY());
        double bB = sumOfSquareNumber(pointB.getX(), pointB.getY());
        double cC = sumOfSquareNumber(pointC.getX(), pointC.getY());

        x0 = (aA * (pointB.getY() - pointC.getY())
              + bB * (pointC.getY() - pointA.getY())
              + cC * (pointA.getY() - pointB.getY())) / d;

        y0 = (aA * (pointC.getX() - pointB.getX())
              + bB * (pointA.getX() - pointC.getX())
              + cC * (pointB.getX() - pointA.getX())) / d;

        radius = lenghtAB * lenghtAC * lenghtBC / (COEF_RADIUS * area);

        return new Circle(new Point2D(x0, y0), radius);
    }

    private double sumOfSquareNumber(final double x, final double y) {
        return Math.pow(x, 2) + Math.pow(y, 2);
    }

    private double getLenghtBtwTwoPoint(final Point2D pointOne, final Point2D pointSec) {
        double a = Math.pow(pointOne.getX() - pointSec.getX(), 2);
        double b = Math.pow(pointOne.getY() - pointSec.getY(), 2);

        return Math.sqrt(a + b);
    }
}
