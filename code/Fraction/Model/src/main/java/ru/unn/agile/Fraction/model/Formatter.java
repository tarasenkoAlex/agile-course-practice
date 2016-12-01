package ru.unn.agile.Fraction.model;

final class Formatter {
    private Formatter() { }

    static String formatToString(final Fraction f) {
        if (f.getDenominator() == 1) {
            return String.valueOf(f.getNumerator());
        }
        return f.getNumerator() + "/" + f.getDenominator();
    }

    static Fraction formatFromString(final String s) {
        if (s.contains("/")) {
            int i = s.indexOf("/");
            int n = Integer.valueOf(s.substring(0, i));
            int d = Integer.valueOf(s.substring(i + 1));
            return new Fraction(n, d);
        } else {
            return new Fraction(Integer.valueOf(s));
        }
    }
}
