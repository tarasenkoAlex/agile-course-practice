package ru.unn.agile.PersonalFinance.Model;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenAddingTransactionToAccount {
    @Test
    public void andItIsExpenseBalanceShouldDecrease() {
        Account account = new Account(75);

        account.addExpense(ExternalTransaction.expenseBuilder(25).build());

        assertEquals(50, account.getBalance());
    }

    @Test
    public void andItIsIncomeBalanceShouldIncrease() {
        Account account = new Account(75);

        account.addIncome(ExternalTransaction.incomeBuilder(50).build());

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

        cash.addExpense(ExternalTransaction.expenseBuilder(25).build());
        List<Transaction> transactions = cash.getTransactions();
        Transaction last = transactions.get(transactions.size() - 1);

        assertEquals(-25, last.getAmount());
    }

    @Test
    public void andItIsIncomeItIsSavedToTransactionList() {
        Account debitCard = new Account(75);

        debitCard.addIncome(ExternalTransaction.incomeBuilder(50).build());
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
        assertEquals(50, debitCardLast.getAmount());
    }

    @Test
    public void andItIsExpenseDescriptionIsSaved() {
        Account cash = new Account(75);

        cash.addExpense(ExternalTransaction.expenseBuilder(25)
                .description("Candy")
                .build());
        Transaction lastExpense = cash.getTransactions().get(
                cash.getTransactions().size() - 1);

        assertEquals("Candy", ((ExternalTransaction) lastExpense).getDescription());
    }

    @Test
    public void andItIsIncomeDescriptionIsSaved() {
        Account debitCard = new Account(75);

        debitCard.addIncome(ExternalTransaction.incomeBuilder(50)
                .description("Salary")
                .build());
        Transaction lastIncome = debitCard.getTransactions().get(
                debitCard.getTransactions().size() - 1);

        assertEquals("Salary", ((ExternalTransaction) lastIncome).getDescription());
    }

    @Test
    public void andItIsExpenseCategoryIsSaved() {
        Account cash = new Account(75);

        Category food = new Category("Food");
        cash.addExpense(ExternalTransaction.expenseBuilder(25)
                .category(food)
                .build());
        Transaction lastExpense = cash.getTransactions().get(0);

        assertEquals(food, ((ExternalTransaction) lastExpense).getCategory());
    }

    @Test
    public void andItIsIncomeCategoryIsSaved() {
        Account cash = new Account(75);

        Category taxReturn = new Category("Tax return");
        cash.addIncome(ExternalTransaction.incomeBuilder(50)
                .category(taxReturn)
                .build());
        Transaction lastIncome = cash.getTransactions().get(0);

        assertEquals(taxReturn, ((ExternalTransaction) lastIncome).getCategory());
    }

    @Test(expected=IllegalArgumentException.class)
    public void andItIsTransferNegativeAmountShouldCauseFailure() {
        Account cash = new Account(75);
        Account debitCard = new Account(150);

        debitCard.transferTo(cash, -20);
    }
}
