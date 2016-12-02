package ru.unn.agile.MortgageCalculator.model;

import java.security.InvalidParameterException;

public final class MortrageDataBuilder {

    public static final double PERCENT_MAX = 100;
    private static final double ONE = 1;
    private double debt;
    private double years;
    private double percents;

    public MortrageDataBuilder() {
        debt = ONE;
        years = ONE;
        percents = ONE;
    }

    public MortrageDataBuilder setDebt(final double debt) {
        if (debt > 0) {
            this.debt = debt;
            return this;
        } else {
            throw new InvalidParameterException("Incorrect data input");
        }
    }

    public MortrageDataBuilder setYears(final double years) {
        if (years > 0) {
            this.years = years;
            return this;
        } else {
            throw new InvalidParameterException("Incorrect data input");
        }
    }

    public MortrageDataBuilder setPercents(final double percents) {
        if (percents > 0 && percents <= PERCENT_MAX) {
            this.percents = percents;
            return this;
        } else {
            throw new InvalidParameterException("Incorrect data input");
        }
    }

    public MortrageData build() {
        return  new MortrageData(debt, years, percents);
    }
}
