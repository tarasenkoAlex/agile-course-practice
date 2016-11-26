package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertContains;

public class WhenAddingNewAccount {
    private LedgerViewModel ledgerVM;
    private AccountViewModel emptyAccount;

    @Before
    public void setUp() throws Exception {
        ledgerVM = new LedgerViewModel();
        emptyAccount = new AccountViewModel();
    }

    @Test
    public void andItIsAddedToAccountList() throws Exception {
        ledgerVM.addAccount(emptyAccount);

        List<AccountViewModel> accountVMs = ledgerVM.getAccounts();
        assertContains(accountVMs, emptyAccount);
    }
}
