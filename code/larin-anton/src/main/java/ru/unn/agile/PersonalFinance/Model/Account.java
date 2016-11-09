package ru.unn.agile.PersonalFinance.Model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public Account(int startingBalance, String name) {
        this.balance = startingBalance;
        this.name = name;
        this.transactions = new ArrayList<>();
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addExpense(ExternalTransaction expense) {
        balance += expense.getAmount();
        transactions.add(expense);
    }

    public void addIncome(ExternalTransaction income) {
        balance += income.getAmount();
        transactions.add(income);
    }

    public void transferTo(Account other, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Consider adding a transfer in opposite direction");
        }

        balance -= amount;
        other.balance += amount;
        Transfer transfer = new Transfer(amount, this, other);
        transactions.add(transfer);
        other.transactions.add(transfer);
    }

    public void deleteTransaction(Transaction transaction) {
        if (transaction.isExternal()) {
            balance -= transaction.getAmount();
            transactions.remove(transaction);
        } else {
            Transfer transfer = (Transfer) transaction;
            transfer.getSource().balance += transfer.getAmount();
            transfer.getTarget().balance -= transfer.getAmount();
            transactions.remove(transfer);
            if (transfer.getSource() == this) {
                transfer.getTarget().getTransactions().remove(transfer);
            } else {
                transfer.getSource().getTransactions().remove(transfer);
            }
        }
    }

    public void changeName(String name) {
        this.name = name;
    }

    private int balance;
    private String name;
    private List<Transaction> transactions;
}
