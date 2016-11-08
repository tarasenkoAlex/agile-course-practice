package ru.unn.agile.PersonalFinance.Model;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenAddingTransactionToAccount {
    @Test
    public void andItIsExpenseBalanceShouldDecrease() {
        Account account = new Account(75);

        account.addExpenseTransaction(25);

        assertEquals(50, account.getBalance());
    }

    @Test
    public void andItIsIncomeBalanceShouldIncrease() {
        Account account = new Account(75);

        account.addIncomeTransaction(50);

        assertEquals(125, account.getBalance());
    }

    @Test
    public void andItIsTransferAccountsBalancesAdjust() {
        Account cash = new Account(75);
        Account debitCard = new Account(250);

        debitCard.transferTo(cash, 50);

        assertEquals(125, cash.getBalance());
        assertEquals(200, debitCard.getBalance());
    }

    @Test
    public void andItIsExpenseItIsSavedToTransactionList() {
        Account cash = new Account(75);

        cash.addExpenseTransaction(25);
        List<Integer> transactions = cash.getTransactions();
        int last = transactions.get(transactions.size() - 1);

        assertEquals(-25, last);
    }

    @Test
    public void andItIsIncomeItIsSavedToTransactionList() {
        Account debitCard = new Account(75);

        debitCard.addIncomeTransaction(50);
        List<Integer> transactions = debitCard.getTransactions();
        int last = transactions.get(transactions.size() - 1);

        assertEquals(50, last);
    }
}
