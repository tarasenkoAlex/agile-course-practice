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

    public void addExpenseTransaction(int amount) {
        balance -= amount;
        transactions.add(-amount);
    }

    public void addIncomeTransaction(int amount) {
        balance += amount;
        transactions.add(amount);
    }

    public void transferTo(Account other, int amount) {
        balance -= amount;
        other.balance += amount;
    }

    public List<Integer> getTransactions() {
        return transactions;
    }

    private int balance;
    private List<Integer> transactions;
}
