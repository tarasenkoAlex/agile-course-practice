package ru.unn.agile.Credit.Model;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class CreditTest {
    private final double delta = 0.4;
    private final double sum = 10000;
    private final double months = 12;
    private final double percent = 20;

    @Test
    public void canCreateCredit() {
        Credit credit = new Credit(sum, months, percent);
        assertNotNull(credit);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountNegativeSum() {
        Credit credit = new Credit(-sum, months, percent);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountNegativeMonths() {
        Credit credit = new Credit(sum, -months, percent);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountWrongPercents() {
        Credit credit = new Credit(sum, months, -percent);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountCreditWhenMonthsAreNull() {
        Credit credit = new Credit(sum, 0, percent);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountCreditWhenParametersAreUndefined() {
        Credit credit = new Credit(0, 0, 0);
    }

    @Test
    public void canCountPayment() {
        Credit credit = new Credit(sum, months, percent);
        double payment = credit.countPayment();
        assertEquals(926, payment, delta);
    }

    @Test
    public void canCountTotalSum() {
        Credit credit = new Credit(sum, months, percent);
        double total_sum = credit.countTotalSum();
        assertEquals(11116, total_sum, delta);
    }

    @Test
    public void canCountOverpayment() {
        Credit credit = new Credit(sum, months, percent);
        double overpayment = credit.countOverpayment();
        assertEquals(1116, overpayment, delta);
    }
}
