package ru.unn.agile.PersonalFinance.Model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenEditingTransaction {
    Account cash = new Account(75, "Cash");
    Account debitCard = new Account(150, "Debit card");
    GregorianCalendar oldDate = new GregorianCalendar(2015, Calendar.JULY, 4);
    GregorianCalendar newDate = new GregorianCalendar(2015, Calendar.MAY, 9);

    @Test
    public void amountAndItIsExpenseAccountsBalanceShouldBeAdjusted() {
        ExternalTransaction expense = ExternalTransaction.expenseBuilder(25).build();
        cash.addExpense(expense);
        ExternalTransaction editedExpense = ExternalTransaction.expenseBuilder(20).build();

        cash.replaceExternalTransaction(expense, editedExpense);

        assertEquals(55, cash.getBalance());
    }

    @Test
    public void amountAndItIsIncomeAccountsBalanceShouldBeAdjusted() {
        ExternalTransaction income = ExternalTransaction.incomeBuilder(25).build();
        cash.addIncome(income);
        ExternalTransaction editedIncome = ExternalTransaction.incomeBuilder(20).build();

        cash.replaceExternalTransaction(income, editedIncome);

        assertEquals(95, cash.getBalance());
    }

    @Test
    public void amountAndItIsTransferBothAccountsBalanceShouldBeAdjusted() {
        debitCard.transferTo(cash, 50);
        Transfer transfer = (Transfer) cash.getTransactions().get(0);

        cash.replaceTransfer(transfer, new Transfer(40, debitCard, cash));

        assertEquals(115, cash.getBalance());
        assertEquals(110, debitCard.getBalance());
    }

    @Test
    public void descriptionAndItIsExpenseDescriptionShouldChange() {
        ExternalTransaction expense = ExternalTransaction.expenseBuilder(25)
                .description("Water").build();
        cash.addExpense(expense);

        ExternalTransaction newExpense = ExternalTransaction.expenseBuilder(25)
                .description("Mineral water").build();
        cash.replaceExternalTransaction(expense, newExpense);

        assertEquals("Mineral water",
                ((ExternalTransaction) cash.getTransactions().get(0)).getDescription());
    }

    @Test
    public void descriptionAndItIsIncomeDescriptionShouldChange() {
        ExternalTransaction income = ExternalTransaction.incomeBuilder(50)
                .description("Salary").build();
        debitCard.addIncome(income);

        ExternalTransaction newIncome = ExternalTransaction.incomeBuilder(50)
                .description("2nd job salary").build();
        debitCard.replaceExternalTransaction(income, newIncome);

        assertEquals("2nd job salary",
                ((ExternalTransaction) debitCard.getTransactions().get(0)).getDescription());
    }

    @Test
    public void categoryAndItIsExpenseCategoryShouldChange() {
        ExternalTransaction expense = ExternalTransaction.expenseBuilder(25)
                .category(new Category("Groceries")).build();
        cash.addExpense(expense);

        Category newCategory = new Category("Restaurants");
        ExternalTransaction newExpense = ExternalTransaction.expenseBuilder(25)
                .category(newCategory).build();
        cash.replaceExternalTransaction(expense, newExpense);

        assertEquals(newCategory,
                ((ExternalTransaction) cash.getTransactions().get(0)).getCategory());
    }

    @Test
    public void categoryAndItIsIncomeCategoryShouldChange() {
        ExternalTransaction income = ExternalTransaction.incomeBuilder(25)
                .category(new Category("Groceries")).build();
        cash.addIncome(income);

        Category newCategory = new Category("Restaurants");
        ExternalTransaction newIncome = ExternalTransaction.incomeBuilder(25)
                .category(newCategory).build();
        cash.replaceExternalTransaction(income, newIncome);

        assertEquals(newCategory,
                ((ExternalTransaction) cash.getTransactions().get(0)).getCategory());
    }

    @Test
    public void dateAndItIsTransferDateShouldChange() {
        debitCard.transferTo(cash, 25, oldDate);
        Transfer transfer = (Transfer) debitCard.getTransactions().get(0);

        debitCard.replaceTransfer(transfer, new Transfer(25, debitCard, cash, newDate));

        assertEquals(newDate, ((Transfer) debitCard.getTransactions().get(0)).getDate());
        assertEquals(newDate, ((Transfer) cash.getTransactions().get(0)).getDate());
    }

    @Test
    public void dateAndItIsExpenseDateShouldChange() {
        ExternalTransaction oldExpense = ExternalTransaction.expenseBuilder(25)
                .date(oldDate).build();
        cash.addExpense(oldExpense);

        ExternalTransaction newExpense = ExternalTransaction.expenseBuilder(25)
                .date(newDate).build();
        cash.replaceExternalTransaction(oldExpense, newExpense);

        assertEquals(newDate, ((ExternalTransaction) cash.getTransactions().get(0)).getDate());
    }

    @Test
    public void dateAndItIsIncomeDateShouldChange() {
        ExternalTransaction oldIncome = ExternalTransaction.incomeBuilder(25)
                .date(oldDate).build();
        cash.addExpense(oldIncome);

        ExternalTransaction newIncome = ExternalTransaction.incomeBuilder(25)
                .date(newDate).build();
        cash.replaceExternalTransaction(oldIncome, newIncome);

        assertEquals(newDate, ((ExternalTransaction) cash.getTransactions().get(0)).getDate());
    }
}
