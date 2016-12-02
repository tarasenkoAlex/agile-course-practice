package ru.unn.agile.CurrencyConverter.model;

public class Converter {
    public double execute(final double amount, final double fromCurrency, final double toCurrency) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive number");
        }
        return amount * fromCurrency / toCurrency;
    }
}
