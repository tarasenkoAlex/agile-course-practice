package ru.unn.agile.Credit.Model;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class CreditTest {
    private final double DELTA = 0.4;
    private final double SUM = 10000;
    private final double MONTHS = 12;
    private final double PERCENT = 20;

    @Test
    public void canCreateCredit() {
        Credit credit = new Credit(SUM, MONTHS, PERCENT);
        assertNotNull(credit);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountNegativeSum() {
        Credit credit = new Credit(-SUM, MONTHS, PERCENT);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountNegativeMonths() {
        Credit credit = new Credit(SUM, -MONTHS, PERCENT);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountWrongPercents() {
        Credit credit = new Credit(SUM, MONTHS, -PERCENT);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountCreditWhenMonthsAreNull() {
        Credit credit = new Credit(SUM, 0, PERCENT);
    }

    @Test(expected = InvalidParameterException.class)
    public void canCountCreditWhenParametersAreUndefined() {
        Credit credit = new Credit(0, 0, 0);
    }

    @Test
    public void canCountPayment() {
        Credit credit = new Credit(SUM, MONTHS, PERCENT);
        double payment = credit.countPayment();
        assertEquals(926, payment, DELTA);
    }

    @Test
    public void canCountTotalSum() {
        Credit credit = new Credit(SUM, MONTHS, PERCENT);
        double totalSum = credit.countTotalSum();
        assertEquals(11116, totalSum, DELTA);
    }

    @Test
    public void canCountOverpayment() {
        Credit credit = new Credit(SUM, MONTHS, PERCENT);
        double overpayment = credit.countOverpayment();
        assertEquals(1116, overpayment, DELTA);
    }
}
