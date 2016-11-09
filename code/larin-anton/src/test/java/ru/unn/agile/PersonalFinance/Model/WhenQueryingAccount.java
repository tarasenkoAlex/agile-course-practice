package ru.unn.agile.PersonalFinance.Model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenQueryingAccount {
    @Test
    public void transactionListShouldBeSortedByDateInReverseOrder() {
        Account cash = new Account(75, "Cash");
        Account debitCard = new Account(150, "Debit card");
        ExternalTransaction buyingGroceries = ExternalTransaction.expenseBuilder(25)
                .date(new GregorianCalendar(2015, Calendar.AUGUST, 17))
                .build();
        ExternalTransaction friendlyLoan = ExternalTransaction.incomeBuilder(50)
                .date(new GregorianCalendar(2015, Calendar.AUGUST, 19))
                .build();

        cash.addExternalTransaction(buyingGroceries);
        cash.addExternalTransaction(friendlyLoan);
        debitCard.transferTo(cash, 40, new GregorianCalendar(2015, Calendar.JULY, 31));
        Transfer cashWithdrawal = (Transfer) debitCard.getTransactions().get(0);

        assertEquals(friendlyLoan, cash.getTransactions().get(0));
        assertEquals(buyingGroceries, cash.getTransactions().get(1));
        assertEquals(cashWithdrawal, cash.getTransactions().get(2));
    }

    @Test
    public void datelessTransactionsShouldBeAtTheEndOfTheList() {
        Account cash = new Account(75, "Cash");
        Account debitCard = new Account(150, "Debit card");
        ExternalTransaction buyingGroceries = ExternalTransaction.expenseBuilder(25).build();
        ExternalTransaction friendlyLoan = ExternalTransaction.incomeBuilder(50)
                .date(new GregorianCalendar(2015, Calendar.AUGUST, 19))
                .build();

        cash.addExternalTransaction(buyingGroceries);
        cash.addExternalTransaction(friendlyLoan);
        debitCard.transferTo(cash, 40, new GregorianCalendar(2015, Calendar.JULY, 31));
        Transfer cashWithdrawal = (Transfer) debitCard.getTransactions().get(0);

        assertEquals(friendlyLoan, cash.getTransactions().get(0));
        assertEquals(cashWithdrawal, cash.getTransactions().get(1));
        assertEquals(buyingGroceries, cash.getTransactions().get(2));
    }

    @Test
    public void afterChangingDatesTransactionListShouldBeSorted() {
        Account cash = new Account(75, "Cash");
        ExternalTransaction buyingGroceries = ExternalTransaction.expenseBuilder(25).build();
        ExternalTransaction friendlyLoan = ExternalTransaction.incomeBuilder(50)
                .date(new GregorianCalendar(2015, Calendar.AUGUST, 19))
                .build();
        cash.addExternalTransaction(buyingGroceries);
        cash.addExternalTransaction(friendlyLoan);

        ExternalTransaction datedBuyingGroceries = ExternalTransaction.expenseBuilder(25)
                .date(new GregorianCalendar(2015, Calendar.AUGUST, 21))
                .build();
        cash.replaceExternalTransaction(buyingGroceries, datedBuyingGroceries);

        assertEquals(datedBuyingGroceries, cash.getTransactions().get(0));
        assertEquals(friendlyLoan, cash.getTransactions().get(1));
    }

    @Test
    public void afterChangingDateOfTransferTransactionListShouldBeSorted() {
        Account cash = new Account(75, "Cash");
        Account debitCard = new Account(150, "Debit card");
        ExternalTransaction friendlyLoan = ExternalTransaction.incomeBuilder(50)
                .date(new GregorianCalendar(2015, Calendar.AUGUST, 19))
                .build();
        cash.addExternalTransaction(friendlyLoan);
        debitCard.transferTo(cash, 40, new GregorianCalendar(2015, Calendar.JULY, 31));
        Transfer cashWithdrawal = (Transfer) debitCard.getTransactions().get(0);

        Transfer updatedCashWithdrawal = new Transfer(40, debitCard, cash,
                new GregorianCalendar(2015, Calendar.AUGUST, 20));
        cash.replaceTransfer(cashWithdrawal, updatedCashWithdrawal);

        assertEquals(updatedCashWithdrawal, cash.getTransactions().get(0));
        assertEquals(friendlyLoan, cash.getTransactions().get(1));
    }
}
