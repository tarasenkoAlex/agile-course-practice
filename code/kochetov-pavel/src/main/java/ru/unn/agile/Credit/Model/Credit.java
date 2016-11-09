
package ru.unn.agile.Credit.Model;

import java.security.InvalidParameterException;

public class Credit {
    private final double s;
    private final double m;
    private final double p;

    Credit(final double s, final double m, final double p){
        if(s < 0){
            throw new InvalidParameterException("Sum must be positive!");
        }
        if(m < 0){
            throw new InvalidParameterException("Months must be positive!");
        }
        if((p < 0) || (p > 100)){
            throw new InvalidParameterException("Percents must be from 0 to 100!");
        }
        if(m == 0) {
            throw new InvalidParameterException("Months must be not null!");
        }
        if((s == 0) && (m == 0) && (p == 0)){
            throw new InvalidParameterException("Parameters are wrong!");
        }
        this.s = s;
        this.m = m;
        this.p = p;
    }

    public double countPayment() {
        double normalized_p = (p / 100) / 12;
        return s * (normalized_p + (normalized_p / (Math.pow(1 + normalized_p, m) - 1)));
    }

    public double countTotalSum() {
        double a = countPayment();
        return a * m;
    }

    public double countOverPayment() {
        double b = countTotalSum();
        return b - s;
    }
}