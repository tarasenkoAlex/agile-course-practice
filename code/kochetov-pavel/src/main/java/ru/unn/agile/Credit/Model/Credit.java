package ru.unn.agile.Credit.Model;

import java.security.InvalidParameterException;

public class Credit {
    private final double SUM;
    private final double MONTHS;
    private final double PERCENT;
    private static final double PERCENT_MAX_VALUE = 100;
    private static final double MONTHS_IN_THE_YEAR = 12;

    Credit(final double sum, final double months, final double percent) {
        if (sum < 0) {
            throw new InvalidParameterException("Sum must be positive!");
        }
        if (months < 0) {
            throw new InvalidParameterException("Months must be positive!");
        }
        if (percent < 0 || percent > PERCENT_MAX_VALUE) {
            throw new InvalidParameterException("Percents must be from 0 to 100!");
        }
        if (months == 0) {
            throw new InvalidParameterException("Months must be not null!");
        }
        if (sum == 0 && months == 0 && percent == 0) {
            throw new InvalidParameterException("Parameters are wrong!");
        }
        this.SUM = sum;
        this.MONTHS = months;
        this.PERCENT = percent;
    }

    public double countPayment() {
        double normalPercent = (PERCENT / PERCENT_MAX_VALUE) / MONTHS_IN_THE_YEAR;
        double fractionCountPay = normalPercent / (Math.pow(1 + normalPercent, MONTHS) - 1);
        return SUM * (normalPercent + fractionCountPay);
    }

    public double countTotalSum() {
        double payment = countPayment();
        return payment * MONTHS;
    }

    public double countOverpayment() {
        double totalSum = countTotalSum();
        return totalSum - SUM;
    }
}
