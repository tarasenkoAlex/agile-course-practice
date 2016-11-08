package ru.unn.agile.PersonalFinance.Model;

import java.util.ArrayList;
import java.util.List;

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

    public void addIncomeTransaction(int amount) {
        this.balance += amount;
    }

    public void transferTo(Account other, int amount) {
        this.balance -= amount;
        other.balance += amount;
    }

    private int balance;
}
