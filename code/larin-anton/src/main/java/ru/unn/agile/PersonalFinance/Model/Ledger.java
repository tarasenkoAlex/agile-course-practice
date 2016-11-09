package ru.unn.agile.PersonalFinance.Model;

import java.util.ArrayList;
import java.util.List;

public class Ledger {
    public Ledger() {
        this.accounts = new ArrayList<>();
    }

    public boolean isEmpty() {
        return accounts.isEmpty();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    private List<Account> accounts;
}
