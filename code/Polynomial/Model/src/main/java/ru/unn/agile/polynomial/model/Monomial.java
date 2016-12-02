package ru.unn.agile.polynomial.model;

import java.util.regex.Pattern;

public class Monomial {
    private final int power;
    private final double coefficient;

    public Monomial(final int power, final double coefficient) {
        if (power < 0) {
            throw new IllegalArgumentException();
        }
        this.power = power;
        this.coefficient = coefficient;
    }

    public Monomial(final double coefficient) {
        this(0, coefficient);
    }

    public Monomial() {
        this(0, 0);
    }

    public static Monomial fromString(final String string) {
        String coefficientPattern = "[\\+-]?[0-9]+(\\.[0-9]+)?";
        String powerPattern = "[0-9]+";

        if (Pattern.matches("^" + coefficientPattern + "\\*x\\^" + powerPattern + "$",
            string)) {

            String[] parts = string.split("\\*x\\^");
            return new Monomial(Integer.parseInt(parts[1]),
                                Double.parseDouble(parts[0]));
        } else if (Pattern.matches("^[\\+-]?x\\^" + powerPattern + "$", string)) {
            String[] parts = string.split("x\\^");
            Monomial monomial = new Monomial(Integer.parseInt(parts[1]), 1.0);
            if (parts[0].startsWith("-")) {
                monomial = monomial.negate();
            }
            return monomial;
        } else if (Pattern.matches("^" + coefficientPattern + "\\*x$", string)) {
            return new Monomial(1, Double.parseDouble(string.replaceAll("\\*x", "")));
        } else if ("x".equals(string) || "+x".equals(string)) {
            return new Monomial(1, 1.0);
        } else if ("-x".equals(string)) {
            return new Monomial(1, -1.0);
        } else if (Pattern.matches("^" + coefficientPattern + "$", string)) {
            return new Monomial(0, Double.parseDouble(string));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
         return String.format("%s*x^%d", coefficient, power);
    }

    int getPower() {
        return power;
    }

    double getCoefficient() {
        return coefficient;
    }

    Monomial negate() {
        return new Monomial(power, -coefficient);
    }

    Monomial add(final Monomial another) {
        if (another.power != power) {
            throw new IllegalArgumentException();
        }
        return new Monomial(power, another.coefficient + coefficient);
    }

    Monomial subtract(final Monomial subtrahend) {
        return this.add(subtrahend.negate());
    }

    Monomial multiply(final Monomial multiplier) {
        return new Monomial(power + multiplier.power,
                            coefficient * multiplier.coefficient);
    }

    Monomial multiply(final double multiplier) {
        return new Monomial(power, coefficient * multiplier);
    }

    Monomial divide(final double divider) {
        return new Monomial(power, coefficient / divider);
    }

    Monomial divide(final Monomial divider) {
        return new Monomial(power - divider.power,
                           coefficient / divider.coefficient);
    }

    public double eval(final double variableValue) {
        return coefficient * Math.pow(variableValue, power);
    }

    @Override
    public int hashCode() {
        return power ^ new Double(coefficient).hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof Monomial) {
            Monomial monomial = (Monomial) object;
            return monomial.power == power
                && monomial.coefficient == coefficient;
        } else {
            return false;
        }
    }
}
