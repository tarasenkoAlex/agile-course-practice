package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhenEditingAccount {
    private final static String INITIAL_ACCOUNT_NAME = "Initial account name";
    private final static String CHANGED_ACCOUNT_NAME = "Changed account name";

    private AccountViewModel account;

    @Before
    public void setUp() throws Exception {
        LedgerViewModel ledger = new LedgerViewModel();
        account = new AccountViewModel(ledger);
        account.setName(INITIAL_ACCOUNT_NAME);
        account.setBalance(10000);
        account.save();

        account.startEditing();
    }

    @Test
    public void andModelAccountNameChangedIfItWasSaved() throws Exception {
        account.setName(CHANGED_ACCOUNT_NAME);
        account.save();

        String modelAccountName = account.getModelAccount().getName();
        assertEquals(modelAccountName, CHANGED_ACCOUNT_NAME);
    }

    @Test
    public void andItsNameRecoversIfChangesWasNotApplied() throws Exception {
        account.setName(CHANGED_ACCOUNT_NAME);
        account.cancelEditing();

        assertEquals(account.getName(), INITIAL_ACCOUNT_NAME);
    }

    @Test
    public void andModelAccountNameRecoversIfChangesWasNotApplied() throws Exception {
        account.setName(CHANGED_ACCOUNT_NAME);
        account.cancelEditing();

        String modelAccountName = account.getModelAccount().getName();
        assertEquals(modelAccountName, INITIAL_ACCOUNT_NAME);
    }

    @Test
    public void andBalanceCanNotBeChanged() throws Exception {
        // TODO
    }
}
