package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WhenCreatingTransfer {
    private TransferViewModel transfer;
    private LedgerViewModel ledger;

    @Before
    public void setUp() throws Exception {
        ledger = new LedgerViewModel();
        transfer = new TransferViewModel(ledger);
    }

    @Test
    public void andItCanNotBeSavedIfSourceAndTargetAreSame() throws Exception {
        AccountViewModel account = new AccountViewModel(ledger);

        transfer.setAccountFrom(account);
        transfer.setAccountTo(account);

        assertFalse(transfer.getIsIsAbleToSave());
    }

    @Test
    public void andItCanNotBeSavedIsSourceAccountIsNull() throws Exception {
        transfer.setAccountFrom(null);

        assertFalse(transfer.getIsIsAbleToSave());
    }

    @Test
    public void andItCanNotBeSavedIsTargetAccountIsNull() throws Exception {
        transfer.setAccountTo(null);

        assertFalse(transfer.getIsIsAbleToSave());
    }

    @Test
    public void andItCanBeSavedIfSourceAndTargetAccountsAreDifferent() throws Exception {
        AccountViewModel sourceAccount = new AccountViewModel(ledger);
        AccountViewModel targetAccount = new AccountViewModel(ledger);

        transfer.setAccountFrom(sourceAccount);
        transfer.setAccountTo(targetAccount);

        assertTrue(transfer.getIsIsAbleToSave());
    }

    @Test
    public void andItCanNotBeSavedIfAmountIsNegative() throws Exception {
        transfer.setAmount(-100);

        assertFalse(transfer.getIsIsAbleToSave());
    }

    @Test
    public void andItCanNotBeSavedIfAmountIsZero() throws Exception {
        transfer.setAmount(0);

        assertFalse(transfer.getIsIsAbleToSave());
    }

    @Test
    public void andItCanBeSavedIfAmountIsPositive() throws Exception {
        transfer.setAmount(100);

        assertTrue(transfer.getIsIsAbleToSave());
    }
}
