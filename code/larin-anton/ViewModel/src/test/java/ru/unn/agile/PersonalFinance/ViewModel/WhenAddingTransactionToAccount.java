package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.PersonalFinance.Model.Account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertContains;

public class WhenAddingTransactionToAccount {
    private AccountViewModel accountViewModel;
    @Before
    public void setUp() throws Exception {
        accountViewModel = new AccountViewModel();
    }

    @Test
    public void andItIsAddedToTransactionsList() throws Exception {
        TransactionViewModel transactionVM = new TransactionViewModel();

        accountViewModel.addTransaction(transactionVM);

        assertContains(accountViewModel.getTransactions(), transactionVM);
    }

    @Test
    public void andAccountBalanceChanges() throws Exception {
        TransactionViewModel transactionVM = new TransactionViewModel();
        transactionVM.setAmount(10);

        int balanceBeforeAddingTransaction = accountViewModel.getBalance();
        accountViewModel.addTransaction(transactionVM);

        assertNotEquals(accountViewModel.getBalance(), balanceBeforeAddingTransaction);
    }

    @Test
    public void andBalanceOfOtherAccountChangesIfTransactionIsTransfer() throws Exception {
        int transferAmount = 1000;
        int startingCashBalance = 10000;
        int startingDebitCardBalance = 50000;
        Account cashAccount = new Account(startingCashBalance, "Cash");
        Account debitCardAccount = new Account(startingDebitCardBalance, "Debit card");
        LedgerViewModel ledgerVM = new LedgerViewModel();
        AccountViewModel cashAccountVM = new AccountViewModel(ledgerVM, cashAccount);
        AccountViewModel debitCardAccountVM = new AccountViewModel(ledgerVM, debitCardAccount);
        ledgerVM.addAccount(cashAccountVM);
        ledgerVM.addAccount(debitCardAccountVM);

        TransactionViewModel newTransaction = new TransactionViewModel();
        newTransaction.setIsTransfer(true);
        newTransaction.setAmount(transferAmount);
        newTransaction.setAccountFrom(cashAccountVM);
        newTransaction.setAccountTo(debitCardAccountVM);
        cashAccountVM.addTransaction(newTransaction);

        assertEquals(debitCardAccountVM.getBalance(), startingDebitCardBalance + transferAmount);
        assertEquals(cashAccountVM.getBalance(), startingCashBalance - transferAmount);
    }
}
