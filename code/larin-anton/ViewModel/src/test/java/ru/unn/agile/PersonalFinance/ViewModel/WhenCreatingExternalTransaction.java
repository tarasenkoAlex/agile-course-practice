package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WhenCreatingExternalTransaction {
    private ExternalTransactionViewModel transaction;

    @Before
    public void setUp() throws Exception {
        LedgerViewModel ledger = new LedgerViewModel();
        transaction = new ExternalTransactionViewModel(ledger);
    }

    @Test(expected = NullPointerException.class)
    public void andIfLedgerModelIsNullItCauseFail() throws Exception {
        new ExternalTransactionViewModel(null);
    }

    @Test
    public void andItCanBeSavedIfCounterpartyIsNormalString() throws Exception {
        transaction.setCounterparty("Tesla Motors");

        assertTrue(transaction.getIsIsAbleToSave());
    }

    @Test
    public void andItCanNotBeSavedIfCounterpartyIsNull() throws Exception {
        transaction.setCounterparty(null);

        assertFalse(transaction.getIsIsAbleToSave());
    }

    @Test
    public void andItCanNotBeSavedIfCounterpartyIsEmpty() throws Exception {
        transaction.setCounterparty("");

        assertFalse(transaction.getIsIsAbleToSave());
    }

    @Test
    public void andItCanNotBeSavedIfCounterpartyIsWhitespacesString() throws Exception {
        transaction.setCounterparty("    ");

        assertFalse(transaction.getIsIsAbleToSave());
    }

    @Test
    public void andItCanBeSavedIfAmountIsPositive() throws Exception {
        transaction.setAmount(100);

        assertTrue(transaction.getIsIsAbleToSave());
    }

    @Test
    public void andItCanNotBeSavedIfAmountIsNegative() throws Exception {
        transaction.setAmount(-100);

        assertFalse(transaction.getIsIsAbleToSave());
    }

    @Test
    public void andItCanNotBeSavedIfAmountIsZero() throws Exception {
        transaction.setAmount(0);

        assertFalse(transaction.getIsIsAbleToSave());
    }

    @Test
    public void andItCanNotBeSavedIfCategoryIsNull() throws Exception {
        transaction.setCategory(null);

        assertFalse(transaction.getIsIsAbleToSave());
    }
}
