package ru.unn.agile.PersonalFinance.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenCreatingTransaction {
    @Test(expected=IllegalArgumentException.class)
    public void andItIsExpenseNegativeAmountShouldCauseFailure() {
        ExternalTransaction expense = ExternalTransaction.expenseBuilder(-5).build();
    }

    @Test(expected=IllegalArgumentException.class)
    public void andItIsIncomeNegativeAmountShouldCauseFailure() {
        ExternalTransaction income = ExternalTransaction.incomeBuilder(-5).build();
    }
}
