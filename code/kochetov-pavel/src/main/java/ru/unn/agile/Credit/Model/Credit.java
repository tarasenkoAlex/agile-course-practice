
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

/*package ru.unn.agile.ComplexNumber.Model;

public class ComplexNumber {
    private double re;
    private double im;

    public ComplexNumber(final double real, final double imaginary) {
        this.re = real;
        this.im = imaginary;
    }

    @Override
    public int hashCode() {
        final int shift = 32;

        long temp = Double.doubleToLongBits(re);
        int result = (int) (temp ^ (temp >>> shift));
        temp = Double.doubleToLongBits(im);
        result = (shift - 1) * result + (int) (temp ^ (temp >>> shift));
        return result;
    }

    @Override
    public boolean equals(final Object object) {
        ComplexNumber number = (ComplexNumber) object;
        return number.getReal() == getReal()
                && number.getImaginary() == getImaginary();
    }

    public ComplexNumber add(final ComplexNumber other) {
        return new ComplexNumber(other.getReal() + getReal(),
                other.getImaginary() + getImaginary());
    }

    public ComplexNumber multiply(final ComplexNumber other) {
        return new ComplexNumber(
                other.getReal() * getReal() - other.getImaginary() * getImaginary(),
                other.getReal() * getImaginary() + other.getImaginary() * getReal());
    }

    public void setReal(final double real) {
        this.re = real;
    }

    public double getReal() {
        return re;
    }

    public void setImaginary(final double imaginary) {
        this.im = imaginary;
    }

    public double getImaginary() {
        return im;
    }

    public String toString() {
        return Formatter.getFormatted(this);
    }
}
*/