package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.PersonalFinance.Model.Account;

import static org.junit.Assert.assertEquals;

public class WhenCreatingAccountViewModel {
    private Account sourceAccount;

    @Before
    public void setUp() throws Exception {
        sourceAccount = new Account(1000, "Cash");
    }

    @Test
    public void andItsNameEqualsToTheSourceName() throws Exception {
        AccountViewModel accountVM = new AccountViewModel(sourceAccount);

        assertEquals(accountVM.getName(), sourceAccount.getName());
    }

    @Test
    public void andItsBalanceEqualsToTheSourceBalance() throws Exception {
        AccountViewModel accountVM = new AccountViewModel(sourceAccount);

        assertEquals(accountVM.getBalance(), sourceAccount.getBalance());
    }

    @Test(expected = NullPointerException.class)
    public void andItCausesFailureIfSourceAccountIsNull() throws Exception {
        new AccountViewModel(null);
    }
}
