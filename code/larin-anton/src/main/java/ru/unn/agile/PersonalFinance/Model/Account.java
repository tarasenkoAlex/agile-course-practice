package ru.unn.agile.PersonalFinance.Model;

public class Account {
    public Account(int startingBalance) {
        this.balance = startingBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void addExpenseTransaction(int amount) {
        this.balance -= amount;
    }

    private int balance;
}
