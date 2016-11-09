package ru.unn.agile.PersonalFinance.Model;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenAddingTransactionToAccount {
    Account cash = new Account(75, "Cash");
    Account debitCard = new Account(150, "Debit card");

    @Test
    public void andItIsExpenseBalanceShouldDecrease() {
        cash.addExpense(ExternalTransaction.expenseBuilder(25).build());

        assertEquals(50, cash.getBalance());
    }

    @Test
    public void andItIsIncomeBalanceShouldIncrease() {
        cash.addIncome(ExternalTransaction.incomeBuilder(50).build());

        assertEquals(125, cash.getBalance());
    }

    @Test
    public void andItIsTransferAccountsBalancesAdjust() {
        debitCard.transferTo(cash, 50);

        assertEquals(125, cash.getBalance());
        assertEquals(100, debitCard.getBalance());
    }

    @Test
    public void andItIsExpenseItIsSavedToTransactionList() {
        cash.addExpense(ExternalTransaction.expenseBuilder(25).build());
        List<Transaction> transactions = cash.getTransactions();
        Transaction last = transactions.get(transactions.size() - 1);

        assertEquals(-25, last.getAmount());
    }

    @Test
    public void andItIsIncomeItIsSavedToTransactionList() {
        debitCard.addIncome(ExternalTransaction.incomeBuilder(50).build());
        List<Transaction> transactions = debitCard.getTransactions();
        Transaction last = transactions.get(transactions.size() - 1);

        assertEquals(50, last.getAmount());
    }

    @Test
    public void andItIsTransferItIsSavedToBothAccountsLists() {
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
        cash.addExpense(ExternalTransaction.expenseBuilder(25)
                .description("Candy")
                .build());
        Transaction lastExpense = cash.getTransactions().get(
                cash.getTransactions().size() - 1);

        assertEquals("Candy", ((ExternalTransaction) lastExpense).getDescription());
    }

    @Test
    public void andItIsIncomeDescriptionIsSaved() {
        debitCard.addIncome(ExternalTransaction.incomeBuilder(50)
                .description("Salary")
                .build());
        Transaction lastIncome = debitCard.getTransactions().get(
                debitCard.getTransactions().size() - 1);

        assertEquals("Salary", ((ExternalTransaction) lastIncome).getDescription());
    }

    @Test
    public void andItIsExpenseCategoryIsSaved() {
        Category food = new Category("Food");
        cash.addExpense(ExternalTransaction.expenseBuilder(25)
                .category(food)
                .build());
        Transaction lastExpense = cash.getTransactions().get(0);

        assertEquals(food, ((ExternalTransaction) lastExpense).getCategory());
    }

    @Test
    public void andItIsIncomeCategoryIsSaved() {
        Category taxReturn = new Category("Tax return");
        cash.addIncome(ExternalTransaction.incomeBuilder(50)
                .category(taxReturn)
                .build());
        Transaction lastIncome = cash.getTransactions().get(0);

        assertEquals(taxReturn, ((ExternalTransaction) lastIncome).getCategory());
    }

    @Test(expected=IllegalArgumentException.class)
    public void andItIsTransferNegativeAmountShouldCauseFailure() {
        debitCard.transferTo(cash, -20);
    }
}
