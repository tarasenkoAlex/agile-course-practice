package ru.unn.agile.PersonalFinance.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenAddingTransactionToAccount {
    @Test
    public void andItIsExpenseBalanceShouldDecrease() {
        Account account = new Account(75);

        account.addExpenseTransaction(25);

        assertEquals(50, account.getBalance());
    }
}
