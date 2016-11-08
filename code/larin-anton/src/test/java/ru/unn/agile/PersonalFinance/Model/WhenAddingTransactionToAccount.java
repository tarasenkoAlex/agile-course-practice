package ru.unn.agile.PersonalFinance.Model;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenAddingTransactionToAccount {
    @Test
    public void andItIsExpenseBalanceShouldDecrease() {
        Account account = new Account(75);

        account.addExpenseTransaction(25, "Candy");

        assertEquals(50, account.getBalance());
    }

    @Test
    public void andItIsIncomeBalanceShouldIncrease() {
        Account account = new Account(75);

        account.addIncomeTransaction(50, "Salary");

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

        cash.addExpenseTransaction(25, "Candy");
        List<Transaction> transactions = cash.getTransactions();
        Transaction last = transactions.get(transactions.size() - 1);

        assertEquals(-25, last.getAmount());
    }

    @Test
    public void andItIsIncomeItIsSavedToTransactionList() {
        Account debitCard = new Account(75);

        debitCard.addIncomeTransaction(50, "Salary");
        List<Transaction> transactions = debitCard.getTransactions();
        Transaction last = transactions.get(transactions.size() - 1);

        assertEquals(50, last.getAmount());
    }

    @Test
    public void andItIsTransferItIsSavedToBothAccountsLists() {
        Account cash = new Account(75);
        Account debitCard = new Account(250);

        debitCard.transferTo(cash, 50);
        Transaction cashLast = cash.getTransactions().get(
                cash.getTransactions().size() - 1);
        Transaction debitCardLast = debitCard.getTransactions().get(
                debitCard.getTransactions().size() - 1);

        assertEquals(50, cashLast.getAmount());
        assertEquals(-50, debitCardLast.getAmount());
    }

    @Test
    public void andItIsExpenseDescriptionIsSaved() {
        Account cash = new Account(75);

        cash.addExpenseTransaction(25, "Candy");
        Transaction lastExpense = cash.getTransactions().get(
                cash.getTransactions().size() - 1);

        assertEquals("Candy", lastExpense.getDescription());
    }

    @Test
    public void andItIsIncomeDescriptionIsSaved() {
        Account debitCard = new Account(75);

        debitCard.addIncomeTransaction(50, "Salary");
        Transaction lastIncome = debitCard.getTransactions().get(
                debitCard.getTransactions().size() - 1);

        assertEquals("Salary", lastIncome.getDescription());
    }
}
