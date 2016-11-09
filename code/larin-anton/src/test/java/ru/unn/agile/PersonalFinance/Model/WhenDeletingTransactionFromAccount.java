package ru.unn.agile.PersonalFinance.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenDeletingTransactionFromAccount {
    @Test
    public void balanceShouldBeUpdated() {
        Account cash = new Account(75);
        cash.addExpenseTransaction(25, "Candy");

        cash.deleteTransaction(cash.getTransactions().get(0));

        assertEquals(75, cash.getBalance());
    }

    @Test
    public void andItIsTheOnlyOneTransactionListShouldBeEmpty() {
        Account cash = new Account(75);
        cash.addExpenseTransaction(25, "Candy");

        cash.deleteTransaction(cash.getTransactions().get(0));

        assertTrue(cash.getTransactions().isEmpty());
    }
}
