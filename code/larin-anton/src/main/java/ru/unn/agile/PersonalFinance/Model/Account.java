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

    public void addExpense(ExternalTransaction expense) {
        balance += expense.getAmount();
        transactions.add(expense);
    }

    public void addIncome(ExternalTransaction income) {
        balance += income.getAmount();
        transactions.add(income);
    }

    public void transferTo(Account other, int amount) {
        balance -= amount;
        other.balance += amount;
        Transfer transfer = new Transfer(amount, this, other);
        transactions.add(transfer);
        other.transactions.add(transfer);
    }

    public List<Transaction> getTransactions() {
        return transactions;
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

    private int balance;
    private List<Transaction> transactions;
}
