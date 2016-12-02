package ru.unn.agile.MortgageCalculator.model;

public class MortrageData {
    private final double years;
    private final double percents;
    private final double debt;

    protected MortrageData(final double debt, final double years, final double percents) {
        this.years = years;
        this.percents = percents;
        this.debt = debt;
    }

    public double getDebt() {
        return debt;
    }

    public double getYears() {
        return years;
    }

    public double getPercents() {
        return percents;
    }

}
