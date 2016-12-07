package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertNotContains;

public class WhenDeletingAccount {
    private final static String INITIAL_ACCOUNT_NAME = "Initial account name";
    private final static int INITIAL_ACCOUNT_BALANCE = 10000;

    private LedgerViewModel ledger;
    private AccountViewModel account;

    @Before
    public void setUp() throws Exception {
        ledger = new LedgerViewModel();
        account = new AccountViewModel(ledger);
        account.setName(INITIAL_ACCOUNT_NAME);
        account.setBalance(INITIAL_ACCOUNT_BALANCE);
        account.save();
    }

    @Test
    public void andItIsDeletedFromAccountList() throws Exception {
        account.delete();

        assertNotContains(ledger.getAccounts(), account);
    }

    @Test
    public void andItIsMarkedAsDeleted() throws Exception {
        account.delete();

        assertTrue(account.isDeleted());
    }
}
