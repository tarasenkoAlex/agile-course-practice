package com.github.rybval.Polynomial.Model;

public class Monomial {
    private final int power;
    private final double coefficient;

    Monomial(final int power, final double coefficient) {
        if (power < 0) {
            throw new IllegalArgumentException();
        }
        this.power = power;
        this.coefficient = coefficient;
    }

    Monomial() {
        this(0, 0);
    }

    Monomial(final String string) {
        String[] parts = string.split("\\*x\\^");
        // Want like this:
        //this(Integer.parseInt(parts[1]), Double.parseDouble(parts[0]));

        // But now this:
        if (Integer.parseInt(parts[1]) < 0) {
            throw new IllegalArgumentException();
        }
        power = Integer.parseInt(parts[1]);
        coefficient = Double.parseDouble(parts[0]);
    }

    int getPower() {
        return power;
    }

    double getCoefficient() {
        return coefficient;
    }

    Monomial add(final Monomial another) {
        if (another.power != power) {
            throw new IllegalArgumentException();
        }
        return new Monomial(power, another.coefficient + coefficient);
    }

    Monomial negate() {
        return new Monomial(power, -coefficient);
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

    @Override
    public int hashCode() {
        return new Integer(power).hashCode()
             ^ new Double(coefficient).hashCode();
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

    @Override
    public String toString() {
         return String.format("%s*x^%d", coefficient, power);
    }
}
