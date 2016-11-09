package ru.unn.agile.PersonalFinance.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenEditingTransactionAmount {
    @Test
    public void andItIsExpenseAccountsBalanceShouldBeAdjusted() {
        Account cash = new Account(75, "Cash");
        ExternalTransaction expense = ExternalTransaction.expenseBuilder(25).build();
        cash.addExpense(expense);
        ExternalTransaction editedExpense = ExternalTransaction.expenseBuilder(20).build();

        cash.replaceExternalTransaction(expense, editedExpense);

        assertEquals(55, cash.getBalance());
    }

    @Test
    public void andItIsIncomeBalanceShouldBeAdjusted() {
        Account cash = new Account(75, "Cash");
        ExternalTransaction income = ExternalTransaction.incomeBuilder(25).build();
        cash.addIncome(income);
        ExternalTransaction editedIncome = ExternalTransaction.incomeBuilder(20).build();

        cash.replaceExternalTransaction(income, editedIncome);

        assertEquals(95, cash.getBalance());
    }

    @Test
    public void andItIsTransferBothAccountsBalanceShouldBeAdjusted() {
        Account cash = new Account(75, "Cash");
        Account debitCard = new Account(150, "Debit card");
        debitCard.transferTo(cash, 50);
        Transfer transfer = (Transfer) cash.getTransactions().get(0);

        cash.changeTransferAmount(transfer, 40);

        assertEquals(115, cash.getBalance());
        assertEquals(110, debitCard.getBalance());
    }
}
