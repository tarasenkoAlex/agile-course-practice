package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhenUnselectingAccount {
    AccountViewModel accountViewModel;
    LedgerViewModel ledgerViewModel;

    @Before
    public void setUp() throws Exception {
        ledgerViewModel = new LedgerViewModel();
        accountViewModel = new AccountViewModel(ledgerViewModel);
        accountViewModel.save();
        ledgerViewModel.setSelectedAccount(accountViewModel);
    }

    @Test
    public void andCanAddTransactionPropertyChangesToFalse() throws Exception {
        ledgerViewModel.setSelectedAccount(null);

        boolean canAddTransaction = ledgerViewModel.getCanAddTransaction();
        assertEquals(false, canAddTransaction);
    }
}
