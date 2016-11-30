package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertContains;

public class WhenSavingTransfer {
    private LedgerViewModel ledgerViewModel;
    private AccountViewModel cacheAccount;
    private AccountViewModel debitCardAccount;

    @Before
    public void setUp() throws Exception {
        ledgerViewModel = new LedgerViewModel();
        cacheAccount = new AccountViewModel(ledgerViewModel);
        debitCardAccount = new AccountViewModel(ledgerViewModel);
        cacheAccount.save();
        debitCardAccount.save();
    }

    @Test
    public void andItIsAddedToTransactionsList() throws Exception {
        TransferViewModel transfer =
                new TransferViewModel(ledgerViewModel);
        transfer.setAccountFrom(cacheAccount);
        transfer.setAccountTo(debitCardAccount);

        transfer.save();

        assertContains(cacheAccount.getTransactions(), transfer);
    }

    @Test
    public void andAccountsBalanceChanges() throws Exception {
        TransferViewModel transfer =
                new TransferViewModel(ledgerViewModel);
        transfer.setAccountFrom(cacheAccount);
        transfer.setAccountTo(debitCardAccount);
        transfer.setAmount(10);
        int cacheBalanceBeforeTransaction = cacheAccount.getBalance();
        int debitCardBalanceBeforeTransaction = debitCardAccount.getBalance();

        transfer.save();

        assertNotEquals(cacheBalanceBeforeTransaction, cacheAccount.getBalance());
        assertNotEquals(debitCardBalanceBeforeTransaction, debitCardAccount.getBalance());
    }
}
