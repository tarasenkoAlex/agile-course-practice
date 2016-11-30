package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.PersonalFinance.Model.Account;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertContains;

public class WhenSavingAccount {
    private LedgerViewModel ledgerViewModel;
    private AccountViewModel accountViewModel;

    @Before
    public void setUp() throws Exception {
        ledgerViewModel = new LedgerViewModel();
        accountViewModel = new AccountViewModel(ledgerViewModel);
    }

    @Test
    public void andItIsAddedToAccountList() throws Exception {
        accountViewModel.save();

        List<AccountViewModel> accountVMs = ledgerViewModel.getAccounts();
        assertContains(accountVMs, accountViewModel);
    }

    @Test
    public void andModelAccountNameEqualsToViewModelAccountName() throws Exception {
        String accountName = "Cash";
        accountViewModel.setName(accountName);

        accountViewModel.save();

        Account account = accountViewModel.getModelAccount();
        assertEquals(account.getName(), accountName);
    }

    @Test
    public void andModelAccountBalanceEqualsToViewModelAccountBalance() throws Exception {
        int accountBalance = 10000;
        accountViewModel.setBalance(accountBalance);

        accountViewModel.save();

        Account account = accountViewModel.getModelAccount();
        assertEquals(account.getBalance(), accountBalance);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void andDoubleSavingCauseFail() throws Exception {
        accountViewModel.save();
        accountViewModel.save();
    }

    @Test
    public void andCanAddTransferPropertyChangesToTrueIfAtLeastTwoAccounts() throws Exception {
        AccountViewModel cacheAccount = new AccountViewModel(ledgerViewModel);
        AccountViewModel debitCardAccount = new AccountViewModel(ledgerViewModel);

        cacheAccount.save();
        debitCardAccount.save();

        boolean canAddTransfer = ledgerViewModel.getCanAddTransfer();
        assertEquals(true, canAddTransfer);
    }
}
