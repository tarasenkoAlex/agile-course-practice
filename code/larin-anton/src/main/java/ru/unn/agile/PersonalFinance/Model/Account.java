package ru.unn.agile.PersonalFinance.Model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public Account(int startingBalance) {
        this.balance = startingBalance;
        this.transactions = new ArrayList<>();
    }

    public int getBalance() {
        return balance;
    }

    public void addExpenseTransaction(int amount, String description) {
        balance -= amount;
        transactions.add(new ExternalTransaction(-amount, description));
    }

    public void addIncomeTransaction(int amount, String description) {
        balance += amount;
        transactions.add(new ExternalTransaction(amount, description));
    }

    public void transferTo(Account other, int amount) {
        balance -= amount;
        other.balance += amount;
        transactions.add(new Transfer(-amount, other));
        other.transactions.add(new Transfer(amount, this));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deleteTransaction(Transaction transaction) {
        balance -= transaction.getAmount();
        transactions.remove(transaction);

        if (transaction.otherAccount() != null) {
            transaction.otherAccount().balance += transaction.getAmount();
        }
    }

    private int balance;
    private List<Transaction> transactions;
}
