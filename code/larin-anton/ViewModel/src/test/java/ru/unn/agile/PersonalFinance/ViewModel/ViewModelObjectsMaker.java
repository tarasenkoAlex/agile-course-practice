package ru.unn.agile.PersonalFinance.ViewModel;

final class ViewModelObjectsMaker {
    private static final String DEFAULT_ACCOUNT_NAME = "Default account";
    private static final String DEFAULT_CATEGORY_NAME = "Default category";
    private static final String DEFAULT_COUNTERPARTY_NAME = "Default counterparty";
    private static final String DEFAULT_DESCRIPTION = "Default description";
    private static final String DEFAULT_SOURCE_ACCOUNT_NAME = "Default source account";
    private static final String DEFAULT_TARGET_ACCOUNT_NAME = "Default target account";

    private static final int DEFAULT_ACCOUNT_BALANCE = 10000;
    private static final int DEFAULT_AMOUNT = 1000;

    private int accountsCounter = 0;
    private int transferCounter = 0;

    private static final CategoryViewModel DEFAULT_CATEGORY =
            new CategoryViewModel(DEFAULT_CATEGORY_NAME);

    private final LedgerViewModel ledger = new LedgerViewModel();

    LedgerViewModel getLedger() {
        return ledger;
    }

    AccountViewModel makeAccount(final String name) {
        AccountViewModel account = new AccountViewModel(ledger);
        account.setName(name);
        account.setBalance(DEFAULT_ACCOUNT_BALANCE);
        return account;
    }

    AccountViewModel makeAccount() {
        accountsCounter++;
        return makeAccount(DEFAULT_ACCOUNT_NAME + " " + accountsCounter);
    }

    AccountViewModel makeAccountSaved(final String name) {
        AccountViewModel account = makeAccount(name);
        account.save();
        return account;
    }

    AccountViewModel makeAccountSaved() {
        AccountViewModel account = makeAccount();
        account.save();
        return account;
    }

    ExternalTransactionViewModel makeExternalTransaction(
            final AccountViewModel account) {
        ExternalTransactionViewModel transaction =
                new ExternalTransactionViewModel(account);
        transaction.setCategory(DEFAULT_CATEGORY);
        transaction.setCounterparty(DEFAULT_COUNTERPARTY_NAME);
        transaction.setDescription(DEFAULT_DESCRIPTION);
        transaction.setAmount(DEFAULT_AMOUNT);
        return transaction;
    }

    ExternalTransactionViewModel makeExternalTransactionSaved(
            final AccountViewModel account) {
        ExternalTransactionViewModel transaction = makeExternalTransaction(account);
        transaction.save();
        return transaction;
    }

    TransferViewModel makeTransfer(final AccountViewModel sourceAccount,
                                   final AccountViewModel targetAccount) {
        TransferViewModel transfer = new TransferViewModel();
        transfer.setAmount(DEFAULT_AMOUNT);
        transfer.setAccountFrom(sourceAccount);
        transfer.setAccountTo(targetAccount);
        return transfer;
    }

    TransferViewModel makeTransfer() {
        transferCounter++;
        return makeTransfer(
                makeAccount(DEFAULT_SOURCE_ACCOUNT_NAME + " " + transferCounter),
                makeAccount(DEFAULT_TARGET_ACCOUNT_NAME + " " + transferCounter));
    }

    TransferViewModel makeTransferSaved(final AccountViewModel sourceAccount,
                                        final AccountViewModel targetAccount) {
        TransferViewModel transfer = makeTransfer(sourceAccount, targetAccount);
        transfer.save();
        return transfer;
    }

    TransferViewModel makeTransferSaved() {
        TransferViewModel transfer = makeTransfer();
        transfer.save();
        return transfer;
    }
}
