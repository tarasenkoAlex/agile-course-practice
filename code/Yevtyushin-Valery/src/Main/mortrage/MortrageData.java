package mortrage;

/**
 * Created by Yevtyushin Valery on 15.11.2016.
 */
public class MortrageData {

    private final double years;
    private final double percents;
    private final double debt;

    protected MortrageData(final double debt, final double years, final double percents) {
        this.years = years;
        this.debt = debt;
        this.percents = percents;
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
