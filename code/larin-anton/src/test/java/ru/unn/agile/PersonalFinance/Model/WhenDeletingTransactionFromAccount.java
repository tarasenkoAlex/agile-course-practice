package ru.unn.agile.PersonalFinance.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenDeletingTransactionFromAccount {
    @Test
    public void balanceShouldBeUpdated() {
        Account cash = new Account(75);
        cash.addExpense(ExternalTransaction.expenseBuilder(25).build());

        cash.deleteTransaction(cash.getTransactions().get(0));

        assertEquals(75, cash.getBalance());
    }

    @Test
    public void andItIsTheOnlyOneTransactionListShouldBeEmpty() {
        Account cash = new Account(75);
        cash.addExpense(ExternalTransaction.expenseBuilder(25).build());

        cash.deleteTransaction(cash.getTransactions().get(0));

        assertTrue(cash.getTransactions().isEmpty());
    }

    @Test
    public void andItIsTransferBothAccountsBalancesShouldBeRestored() {
        Account cash = new Account(75);
        Account debitCard = new Account(150);
        debitCard.transferTo(cash, 50);

        debitCard.deleteTransaction(debitCard.getTransactions().get(0));

        assertEquals(75, cash.getBalance());
        assertEquals(150, debitCard.getBalance());
    }

    @Test
    public void andThereIsOnlyOneTransferInEachAccountBothAccountsListsShouldBeEmpty() {
        Account cash = new Account(75);
        Account debitCard = new Account(150);
        debitCard.transferTo(cash, 50);

        cash.deleteTransaction(cash.getTransactions().get(0));

        assertTrue(cash.getTransactions().isEmpty());
        assertTrue(debitCard.getTransactions().isEmpty());
    }
}
