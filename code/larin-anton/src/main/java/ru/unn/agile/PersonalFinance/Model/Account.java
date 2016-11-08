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
        transactions.add(new Transaction(-amount, description));
    }

    public void addIncomeTransaction(int amount, String description) {
        balance += amount;
        transactions.add(new Transaction(amount, description));
    }

    public void transferTo(Account other, int amount) {
        balance -= amount;
        other.balance += amount;
        transactions.add(new Transaction(-amount, ""));
        other.transactions.add(new Transaction(amount, ""));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    private int balance;
    private List<Transaction> transactions;
}
