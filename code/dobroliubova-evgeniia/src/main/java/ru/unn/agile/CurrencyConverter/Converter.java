package ru.unn.agile.CurrencyConverter;


/**
 * Created by Jane on 03.11.2016.
 */
public class Converter {
    static final double RUBLE = 1;
    static final double DOLLAR = 62.6200;
    static final double EURO = 69.1254;
    static final double HRYVNA = 24.3255;
    static final double YEN = 60.4592;
    static final double FRANC = 63.5256;

    public double execute(final double amount, final double fromCurrency, final double toCurrency) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive number");
        }
        return amount * fromCurrency / toCurrency;
    }
}
