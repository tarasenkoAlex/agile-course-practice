package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.PersonalFinance.Model.Account;

import static org.junit.Assert.assertEquals;

public class WhenCreatingAccountViewModel {
    private Account sourceAccount;
    private LedgerViewModel parentLedgerVM;

    @Before
    public void setUp() throws Exception {
        sourceAccount = new Account(1000, "Cash");
        parentLedgerVM = new LedgerViewModel();
    }

    @Test
    public void andItsNameEqualsToTheSourceName() throws Exception {
        AccountViewModel accountVM = new AccountViewModel(parentLedgerVM, sourceAccount);

        assertEquals(accountVM.getName(), sourceAccount.getName());
    }

    @Test
    public void andItsBalanceEqualsToTheSourceBalance() throws Exception {
        AccountViewModel accountVM = new AccountViewModel(parentLedgerVM, sourceAccount);

        assertEquals(accountVM.getBalance(), sourceAccount.getBalance());
    }

    @Test(expected = NullPointerException.class)
    public void andItCausesFailureIfSourceAccountIsNull() throws Exception {
        new AccountViewModel(parentLedgerVM, null);
    }
}
