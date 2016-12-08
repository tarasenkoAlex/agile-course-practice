package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertContains;

public class WhenSavingTransfer {
    private ViewModelObjectsMaker maker;
    private AccountViewModel cacheAccount;
    private AccountViewModel debitCardAccount;

    @Before
    public void setUp() throws Exception {
        maker = new ViewModelObjectsMaker();
        cacheAccount = maker.makeAccountSaved("Cash");
        debitCardAccount = maker.makeAccountSaved("Debit card");
    }

    @Test
    public void andItIsAddedToTransactionsList() throws Exception {
        TransferViewModel transfer = maker.makeTransfer(cacheAccount, debitCardAccount);

        transfer.save();

        assertContains(cacheAccount.getTransactions(), transfer);
    }

    @Test
    public void andAccountsBalanceChanges() throws Exception {
        TransferViewModel transfer = maker.makeTransfer(cacheAccount, debitCardAccount);
        int transferAmount = 100;
        transfer.setAmount(transferAmount);
        int cacheBalanceBeforeTransaction = cacheAccount.getBalance();
        int cardBalanceBeforeTransaction = debitCardAccount.getBalance();

        transfer.save();

        int cacheBalanceAfterTransaction = cacheBalanceBeforeTransaction - transferAmount;
        int cardBalanceAfterTransaction = cardBalanceBeforeTransaction + transferAmount;
        assertEquals(cacheBalanceAfterTransaction, cacheAccount.getBalance());
        assertEquals(cardBalanceAfterTransaction, debitCardAccount.getBalance());
    }
}
