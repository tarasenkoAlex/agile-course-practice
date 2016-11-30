package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertContains;

public class WhenSavingNewAccount {
    private LedgerViewModel ledgerVM;
    private AccountViewModel emptyAccount;

    @Before
    public void setUp() throws Exception {
        ledgerVM = new LedgerViewModel();
        emptyAccount = new AccountViewModel(ledgerVM);
    }

    @Test
    public void andItIsAddedToAccountList() throws Exception {
        emptyAccount.save();

        List<AccountViewModel> accountVMs = ledgerVM.getAccounts();
        assertContains(accountVMs, emptyAccount);
    }
}
