package ru.unn.agile.CurrencyConverter.model;

public enum Constants {
    RUBLE(1),
    DOLLAR(62.6200),
    EURO(69.1254),
    HRYVNA(24.3255),
    YEN(60.4592),
    FRANC(63.5256);
    private final double value;

    Constants(final double value) {
        this.value = value;
    }
    public double getValue() {
        return value;
    }
}
