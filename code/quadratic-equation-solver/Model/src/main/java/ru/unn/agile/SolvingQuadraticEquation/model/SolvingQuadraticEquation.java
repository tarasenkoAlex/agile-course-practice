package ru.unn.agile.SolvingQuadraticEquation.model;

public final class SolvingQuadraticEquation {
    private static final double EPS = 0.001;
    private static final double DISCRIMINANT_COEFFICIENT = 4;
    private SolvingQuadraticEquation() { }
    private static double[] notQuadratic(final double b, final double c) {
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

    public static double[] calc(final String aStr, final String bStr, final String cStr) {
        return calc(Double.parseDouble(aStr), Double.parseDouble(bStr), Double.parseDouble(cStr));
    }

    public static double[] calc(final double a, final double b, final double c) {
        double[] result = new double[0];

        if (a == 0) {
            result = notQuadratic(b, c);
            return result;
        }

        double discriminant = b * b - DISCRIMINANT_COEFFICIENT * a * c;

        if (Math.abs(discriminant) < EPS) {
            result = new double[]{-b / (2 * a)};
        }

        if (discriminant > EPS) {
            result = new double[2];

            result[0] = (-b + Math.sqrt(discriminant)) / (2 * a);
            result[1] = (-b - Math.sqrt(discriminant)) / (2 * a);
        }

        return result;
    }

}
