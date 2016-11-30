package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhenCreatingLedger {
    @Test
    public void andCanAddTransactionPropertyIsFalse() throws Exception {
        LedgerViewModel ledger = new LedgerViewModel();

        boolean canAddTransaction = ledger.getCanAddTransaction();

        assertEquals(false, canAddTransaction);
    }
}
