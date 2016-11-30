package main.mortrage;

/**
 * Created by Yevtyushin Valery on 15.11.2016.
 */
public class MortrageData {
    private static final double ONE = 1;
    private double years;
    private double percents;
    private double debt;

    protected MortrageData() {
        years = ONE;
        percents = ONE;
        debt = ONE;
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

    protected void setDebt(final double debt) {
        this.debt = debt;
    }

    protected void setYears(final double years) {
        this.years = years;
    }

    protected void setPercents(final double percents) {
        this.percents = percents;
    }
}
