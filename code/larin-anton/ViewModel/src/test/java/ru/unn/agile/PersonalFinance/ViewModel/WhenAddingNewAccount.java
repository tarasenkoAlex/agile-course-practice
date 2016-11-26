package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WhenAddingNewAccount {
    private LedgerViewModel ledgerVM;

    @Before
    public void setUp() throws Exception {
        ledgerVM = new LedgerViewModel();
    }

    @Test
    public void andItIsNotNull() throws Exception {
        ledgerVM.createNewAccount();

        AccountViewModel account = ledgerVM.getNewAccount();
        assertNotNull(account);
    }
}
