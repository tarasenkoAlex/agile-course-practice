package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhenSelectingAccount {
    AccountViewModel accountViewModel;
    LedgerViewModel ledgerViewModel;

    @Before
    public void setUp() throws Exception {
        ledgerViewModel = new LedgerViewModel();
        accountViewModel = new AccountViewModel(ledgerViewModel);
        accountViewModel.save();
    }

    @Test
    public void andCanAddTransactionPropertyChangesToTrue() throws Exception {
        ledgerViewModel.setSelectedAccount(accountViewModel);

        boolean canAddTransaction = ledgerViewModel.getCanAddTransaction();
        assertEquals(true, canAddTransaction);
    }
}
