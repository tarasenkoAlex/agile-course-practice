
package ru.unn.agile.Credit.Model;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class CreditTest {
    private final double delta = 0.4;

    public Credit inputValues(){
        double s = 10000;
        double m = 12;
        double p = 20;

        return new Credit(s, m, p);
    }

    @Test
    public void canCreateCredit() {
        Credit cred = inputValues();
        assertNotNull(cred);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountNegativeSum(){
        double s = -10000;
        double m = 12;
        double p = 20;
        Credit cred = new Credit(s, m, p);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountNegativeMonths(){
        double s = 10000;
        double m = -12;
        double p = 20;
        Credit cred = new Credit(s, m, p);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountWrongPercents(){
        double s = 10000;
        double m = 12;
        double p = -20;
        Credit cred = new Credit(s, m, p);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountCreditWhenMonthsAreNull(){
        double s = 10000;
        double m = 0;
        double p = 20;
        Credit cred = new Credit(s, m, p);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountCreditWhenParametersAreUndefined() {
        double s = 0;
        double m = 0;
        double p = 0;
        Credit cred = new Credit(s, m, p);
    }

    @Test
    public void canCountPayment() {
        Credit cred = inputValues();
        double payment = cred.countPayment();
        assertEquals(926, payment, delta);
    }

    @Test
    public void canCountTotalSum() {
        Credit cred = inputValues();
        double total_sum = cred.countTotalSum();
        assertEquals(11116, total_sum, delta);
    }

    @Test
    public void canCountOverPayment() {
        Credit cred = inputValues();
        double overpayment = cred.countOverPayment();
        assertEquals(1116, overpayment, delta);
    }
}