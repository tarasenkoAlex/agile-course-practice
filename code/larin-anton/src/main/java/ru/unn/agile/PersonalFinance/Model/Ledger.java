package ru.unn.agile.PersonalFinance.Model;

import java.util.ArrayList;
import java.util.List;

public class Ledger {
    public static final Account DELETED_ACCOUNT = new Account(0, "<Deleted account>");

    public Ledger() {
        this.accounts = new ArrayList<>();
        this.categories = new ArrayList<>();
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

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void deleteCategory(Category category) {
        categories.remove(category);

        for (Account account : accounts) {
            for (Transaction transaction : account.getTransactions()) {
                if (transaction.isExternal()) {
                    ExternalTransaction externalTransaction =
                            (ExternalTransaction) transaction;
                    if (externalTransaction.getCategory().equals(category)) {
                        externalTransaction.setCategory(null);
                    }
                }
            }
        }
    }

    private List<Account> accounts;
    private List<Category> categories;
}
