package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertContains;

public class WhenAddingTransactionToAccount {
    private AccountViewModel accountViewModel;

    @Before
    public void setUp() throws Exception {
        accountViewModel = new AccountViewModel();
    }

    @Test
    public void andItIsAddedToTransactionsList() throws Exception {
        TransactionViewModel transactionVM = new TransactionViewModel();

        accountViewModel.addTransaction(transactionVM);

        assertContains(accountViewModel.getTransactions(), transactionVM);
    }

    @Test
    public void andAccountBalanceChanges() throws Exception {
        TransactionViewModel transactionVM = new TransactionViewModel();
        transactionVM.setIntAmount(10);

        int balanceBeforeAddingTransaction = accountViewModel.getIntBalance();
        accountViewModel.addTransaction(transactionVM);

        assertNotEquals(accountViewModel.getIntBalance(), balanceBeforeAddingTransaction);
    }
}
