package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertContains;

public class WhenSavingExternalTransaction {
    private LedgerViewModel ledgerViewModel;
    private AccountViewModel accountViewModel;

    @Before
    public void setUp() throws Exception {
        ledgerViewModel = new LedgerViewModel();
        accountViewModel = new AccountViewModel(ledgerViewModel);
        accountViewModel.save();
        ledgerViewModel.setSelectedAccount(accountViewModel);
    }

    @Test
    public void andItIsAddedToTransactionsList() throws Exception {
        ExternalTransactionViewModel transactionVM =
                new ExternalTransactionViewModel(ledgerViewModel);

        transactionVM.save();

        assertContains(accountViewModel.getTransactions(), transactionVM);
    }

    @Test
    public void andAccountBalanceChanges() throws Exception {
        ExternalTransactionViewModel transactionVM =
                new ExternalTransactionViewModel(ledgerViewModel);
        transactionVM.setAmount(10);
        int balanceBeforeTransaction = accountViewModel.getBalance();

        transactionVM.save();

        assertNotEquals(accountViewModel.getBalance(), balanceBeforeTransaction);
    }
}
