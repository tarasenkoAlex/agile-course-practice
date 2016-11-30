package main.mortrage;

/**
 * Created by Yevtyushin Valery on 15.11.2016.
 */
public class MortrageData {

    private double years;
    private double percents;
    private double debt;

    protected MortrageData() {
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
