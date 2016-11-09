package ru.unn.agile.PersonalFinance.Model;

import java.util.ArrayList;
import java.util.List;

public class Ledger {
    public static final Account DELETED_ACCOUNT = new Account(0, "<Deleted account>");

    public Ledger() {
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void deleteAccount(Account account) {
        accounts.remove(account);

        for (Account oneOfTheRest : accounts) {
            for (Transaction transaction : oneOfTheRest.getTransactions()) {
                if (transaction.isTransfer()) {
                    Transfer transfer = (Transfer) transaction;
                    if (transfer.getSource().equals(account)) {
                        transfer.setSource(DELETED_ACCOUNT);
                    }
                    if (transfer.getTarget().equals(account)) {
                        transfer.setTarget(DELETED_ACCOUNT);
                    }
                }
            }
        }
    }

    private List<Account> accounts;
}
