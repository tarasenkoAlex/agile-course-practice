package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Test;

public class WhenCreatingAccount {
    @Test(expected = NullPointerException.class)
    public void andIfLedgerModelIsNullItCauseFail() throws Exception {
        new AccountViewModel(null);
    }
}
