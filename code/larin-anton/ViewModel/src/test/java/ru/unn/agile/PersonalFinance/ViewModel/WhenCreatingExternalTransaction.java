package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Test;

public class WhenCreatingExternalTransaction {
    @Test(expected = NullPointerException.class)
    public void andIfLedgerModelIsNullItCauseFail() throws Exception {
        new ExternalTransactionViewModel(null);
    }
}
