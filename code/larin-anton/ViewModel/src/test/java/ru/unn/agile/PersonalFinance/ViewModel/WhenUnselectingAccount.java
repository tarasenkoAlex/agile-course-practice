package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhenUnselectingAccount {
    AccountViewModel account;
    LedgerViewModel ledger;

    @Before
    public void setUp() throws Exception {
        ViewModelObjectsMaker maker = new ViewModelObjectsMaker();
        ledger = maker.getLedger();
        account = maker.makeAccountSaved();
        ledger.setSelectedAccount(account);
    }

    @Test
    public void andCanAddTransactionPropertyChangesToFalse() throws Exception {
        ledger.setSelectedAccount(null);

        boolean canAddTransaction = ledger.getCanAddTransaction();
        assertEquals(false, canAddTransaction);
    }
}
