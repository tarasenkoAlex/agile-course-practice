import java.security.InvalidParameterException;

public class Mortrage {
    private final double month;
    private final double percents;
    private final double summ;
    private static final double PERCENT_MAX_VALUE = 100;
    private static final double MONTH_IN_THE_YEAR = 12;

    public Mortrage(final double summ, final double month, final double percents) {

        if (month <= 0) {
            throw new InvalidParameterException("WRONG INPUT DATA!");
        }

        if (summ <= 0) {
            throw new InvalidParameterException("WRONG INPUT DATA!");
        }

        if (percents <= 0 || percents > PERCENT_MAX_VALUE) {
            throw new InvalidParameterException("WRONG INPUT DATA!");
        }

        this.summ = summ;
        this.month = month;
        this.percents = percents;
    }

    public double countPayment() {
        double mortragePercent =
                (percents / PERCENT_MAX_VALUE) / MONTH_IN_THE_YEAR;
        double fractionCountPay = mortragePercent / (Math.pow(1 + mortragePercent, month) - 1);
        return summ * (mortragePercent + fractionCountPay);
    }

    public double countTotalSum() {
        double payment = countPayment();
        return payment * month;
    }

    public double countOverpayment() {
        double totalSum = countTotalSum();
        return totalSum - summ;
    }
}
