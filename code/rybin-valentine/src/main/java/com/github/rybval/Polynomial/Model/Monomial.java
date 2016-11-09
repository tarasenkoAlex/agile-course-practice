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
        power = 0;
        coefficient = 0;
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
}
