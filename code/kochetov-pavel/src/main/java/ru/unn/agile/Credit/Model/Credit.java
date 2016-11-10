package ru.unn.agile.Credit.Model;

import java.security.InvalidParameterException;

public class Credit {
    private final double sum;
    private final double months;
    private final double percent;
    private final double percent_max_value = 100;
    private final double months_in_the_year = 12;

    Credit(final double sum, final double months, final double percent) {
        if(sum < 0) {
            throw new InvalidParameterException("Sum must be positive!");
        }
        if(months < 0) {
            throw new InvalidParameterException("Months must be positive!");
        }
        if((percent < 0) || (percent > 100)) {
            throw new InvalidParameterException("Percents must be from 0 to 100!");
        }
        if(months == 0) {
            throw new InvalidParameterException("Months must be not null!");
        }
        if((sum == 0) && (months == 0) && (percent == 0)) {
            throw new InvalidParameterException("Parameters are wrong!");
        }
        this.sum = sum;
        this.months = months;
        this.percent = percent;
    }

    public double countPayment() {
        double normalized_percent = (percent / percent_max_value) / months_in_the_year;
        double fraction_count_pay = normalized_percent / (Math.pow(1 + normalized_percent, months) - 1);
        return sum * (normalized_percent + fraction_count_pay);
    }

    public double countTotalSum() {
        double payment = countPayment();
        return payment * months;
    }

    public double countOverpayment() {
        double totalSum = countTotalSum();
        return totalSum - sum;
    }
}
