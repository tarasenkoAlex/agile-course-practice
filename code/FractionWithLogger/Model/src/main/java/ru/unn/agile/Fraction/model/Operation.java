package ru.unn.agile.Fraction.model;

import static ru.unn.agile.Fraction.model.Fraction.*;

public enum Operation {
    ADD("Add") {
        public Fraction apply(final Fraction l, final Fraction r) {
            return sum(l, r);
        }
    },
    MULTIPLY("Mul") {
        public Fraction apply(final Fraction l, final Fraction r) {
            return multiply(l, r);
        }
    },
    SUBTRACTION("Sub") {
        public Fraction apply(final Fraction l, final Fraction r) {
            return subtract(l, r);
        }
    },
    DIVISION("Div") {
        public Fraction apply(final Fraction l, final Fraction r) {
            return divide(l, r);
        }
    };

    private final String name;
    Operation(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract Fraction apply(Fraction l, Fraction r);
}
