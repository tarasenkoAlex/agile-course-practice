package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class WhenCreatingAccount {
    private final static String EXISTING_ACCOUNT_NAME = "Debit card";
    private final static String ANOTHER_ACCOUNT_NAME = "Yet another debit card";

    private LedgerViewModel ledger;

    @Before
    public void setUp() throws Exception {
        ledger = new LedgerViewModel();

        AccountViewModel account = new AccountViewModel(ledger);
        account.setName(EXISTING_ACCOUNT_NAME);
        account.save();
    }

    @Test(expected = NullPointerException.class)
    public void andIfLedgerModelIsNullItCauseFail() throws Exception {
        new AccountViewModel(null);
    }

    @Test
    public void andItCanNotBeSavedIfAccountWithSameNameAlreadyExists() throws Exception {
        AccountViewModel accountWithExistingName = new AccountViewModel(ledger);
        accountWithExistingName.setName(EXISTING_ACCOUNT_NAME);

        assertFalse(accountWithExistingName.getIsIsAbleToSave());
    }

    @Test
    public void andItCanBeSavedIfAccountWithSameNameDoesNotExist() throws Exception {
        AccountViewModel accountWithNewName = new AccountViewModel(ledger);
        accountWithNewName.setName(ANOTHER_ACCOUNT_NAME);

        assertTrue(accountWithNewName.getIsIsAbleToSave());
    }
}
