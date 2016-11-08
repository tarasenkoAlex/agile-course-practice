package ru.unn.agile.Fraction.Model;

import static java.lang.Math.abs;

public class Fraction {
    private int numerator, denominator;

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Fraction() {
        numerator = 0;
        denominator = 1;
    }

    public Fraction(final int integer) {
        numerator = integer;
        denominator = 1;
    }

    public Fraction(final int numerator, final int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("/ by zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        correctSign();
        reduce();
    }

    public static Fraction multiply(final Fraction a, final Fraction b) {
        return new Fraction(
                a.numerator * b.numerator,
                a.denominator * b.denominator
        );
    }

    public static Fraction divide(final Fraction a, final Fraction b) {
        return new Fraction(
                a.numerator * b.denominator,
                a.denominator * b.numerator
        );
    }

    public static Fraction sum(final Fraction a, final Fraction b) {
        int lcm = lcm(a.denominator, b.denominator);
        return new Fraction(
                a.numerator * (lcm / a.denominator) + b.numerator * (lcm / b.denominator),
                lcm
        );
    }

    public static Fraction subtract(final Fraction a, final Fraction b) {
        int lcm = lcm(a.denominator, b.denominator);
        return new Fraction(
                a.numerator * (lcm / a.denominator) - b.numerator * (lcm / b.denominator),
                lcm
        );
    }





    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Integer) {
            return denominator == 1  &&  numerator == (int) obj;
        } else if (obj instanceof Fraction) {
            return
                    denominator == ((Fraction) obj).denominator
                    &&
                    numerator == ((Fraction) obj).numerator;
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return numerator ^ denominator;
    }

    @Override
    public String toString() {
        return String.valueOf(numerator) + '/' + String.valueOf(denominator);
    }






    private void correctSign() {
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    private void reduce() {
        int d = gcd(abs(numerator), denominator);
        numerator /= d;
        denominator /= d;
    }


    private static int gcd(final int number1, final int number2) {
        int tmp, a = number1, b = number2;
        while (a != 0) {
            tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

    private static int lcm(final int number1, final int number2) {
        return number1 / gcd(number1, number2) * number2;
    }
}
