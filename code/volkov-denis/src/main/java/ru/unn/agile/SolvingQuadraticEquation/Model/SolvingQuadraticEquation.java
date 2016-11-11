package ru.unn.agile.SolvingQuadraticEquation.Model;

public class SolvingQuadraticEquation {
    private static final double EPS = 0.001;
    private static final double DETERMINANT_COEFFICIENT = 4;

    private double[] notQuadratic(final double b, final double c) {
        double[] result = new double[0];

        if (b == 0) {
            if (c == 0) {
                // infinite number of solutions
                result = new double[]{0, 0, 0};
            }
        } else {
            result = new double[]{-c / b};
        }

        return result;
    }

    public double[] calc(final double a, final double b, final double c) {
        double[] result = new double[0];

        if (a == 0) {
            result = notQuadratic(b, c);
            return result;
        }

        double d = b * b - DETERMINANT_COEFFICIENT * a * c;

        if (Math.abs(d) < EPS) {
            result = new double[]{-b / (2 * a)};
        }

        if (d > EPS) {
            result = new double[2];

            d = Math.sqrt(d);

            result[0] = (-b + d) / (2 * a);
            result[1] = (-b - d) / (2 * a);
        }

        return result;
    }
}
