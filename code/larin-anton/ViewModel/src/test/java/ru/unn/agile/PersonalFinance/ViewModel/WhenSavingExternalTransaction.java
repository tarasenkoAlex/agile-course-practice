package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertContains;

public class WhenSavingExternalTransaction {
    private static final String INITIAL_COUNTERPARTY_NAME = "Initial counterparty";
    private static final String INITIAL_CATEGORY_NAME = "Initial category";
    private static final String INITIAL_DESCRIPTION = "Initial description";

    private LedgerViewModel ledger;
    private AccountViewModel account;
    private CategoryViewModel initialCategory;

    @Before
    public void setUp() throws Exception {
        ledger = new LedgerViewModel();
        account = new AccountViewModel(ledger);
        initialCategory = new CategoryViewModel(INITIAL_CATEGORY_NAME);

        account.save();
        ledger.setSelectedAccount(account);
    }

    @Test
    public void andItIsAddedToTransactionsList() throws Exception {
        TransactionViewModel transaction = createExternalTransaction();

        transaction.save();

        assertContains(account.getTransactions(), transaction);
    }

    @Test
    public void andAccountBalanceChanges() throws Exception {
        TransactionViewModel transaction = createExternalTransaction();
        transaction.setAmount(10);
        int balanceBeforeTransaction = account.getBalance();

        transaction.save();

        assertNotEquals(account.getBalance(), balanceBeforeTransaction);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void andIfAccountWasNotSavedItCauseFail() throws Exception {
        AccountViewModel unsavedAccount = new AccountViewModel(ledger);
        ledger.setSelectedAccount(unsavedAccount);
        TransactionViewModel transaction = createExternalTransaction();

        transaction.save();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void andIfAccountWasNotSelectedItCauseFail() throws Exception {
        ledger.setSelectedAccount(null);
        TransactionViewModel transactionVM = createExternalTransaction();

        transactionVM.save();
    }

    private TransactionViewModel createExternalTransaction() {
        ExternalTransactionViewModel transaction =
                new ExternalTransactionViewModel(ledger);
        transaction.setCounterparty(INITIAL_COUNTERPARTY_NAME);
        transaction.setDescription(INITIAL_DESCRIPTION);
        transaction.setCategory(initialCategory);
        return transaction;
    }
}
