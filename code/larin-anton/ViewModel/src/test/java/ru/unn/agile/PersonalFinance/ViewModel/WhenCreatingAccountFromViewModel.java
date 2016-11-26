package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.PersonalFinance.Model.Account;

import static org.junit.Assert.assertEquals;

public class WhenCreatingAccountFromViewModel {
    private AccountViewModel accountVM;
    private String accountName = "Cash";
    private int accountBalance = 10000;

    @Before
    public void setUp() throws Exception {
        accountVM = new AccountViewModel();
        accountVM.setName(accountName);
        accountVM.setBalance(accountBalance);
    }

    @Test
    public void andAccountNameEqualsToTheNamePropertyValue() throws Exception {
        Account account = accountVM.getAccount();

        assertEquals(account.getName(), accountName);
    }

    @Test
    public void andAccountNameEqualsToTheBalancePropertyValue() throws Exception {
        Account account = accountVM.getAccount();

        assertEquals(account.getBalance(), accountBalance);
    }
}
