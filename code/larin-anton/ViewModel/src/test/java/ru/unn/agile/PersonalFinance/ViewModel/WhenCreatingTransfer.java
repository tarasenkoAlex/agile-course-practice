package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WhenCreatingTransfer {
    private static final String SOURCE_ACCOUNT_NAME = "Debit card";
    private static final String TARGET_ACCOUNT_NAME = "Cash";

    private ViewModelObjectsMaker maker;
    private TransferViewModel transfer;

    @Before
    public void setUp() throws Exception {
        maker = new ViewModelObjectsMaker();
        transfer = maker.makeTransfer();
    }

    @Test
    public void andItCanNotBeSavedIfSourceAndTargetAreSame() throws Exception {
        AccountViewModel account = maker.makeAccount();

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
        AccountViewModel sourceAccount = maker.makeAccount(SOURCE_ACCOUNT_NAME);
        AccountViewModel targetAccount = maker.makeAccount(TARGET_ACCOUNT_NAME);

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
