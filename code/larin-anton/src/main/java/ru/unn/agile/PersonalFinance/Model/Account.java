package ru.unn.agile.PersonalFinance.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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

    public void transferTo(Account other, int amount, GregorianCalendar date) {
        if (amount < 0) {
            throw new IllegalArgumentException("Consider adding a transfer in opposite direction");
        }

        balance -= amount;
        other.balance += amount;
        Transfer transfer = new Transfer(amount, this, other, date);
        transactions.add(transfer);
        other.transactions.add(transfer);
    }

    public void transferTo(Account other, int amount) {
        transferTo(other, amount, null);
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

    public void replaceExternalTransaction(
            ExternalTransaction oldTransaction,
            ExternalTransaction newTransaction) {

        transactions.set(transactions.indexOf(oldTransaction), newTransaction);
        balance -= oldTransaction.getAmount() - newTransaction.getAmount();
    }

    public void replaceTransfer(Transfer oldTransfer, Transfer newTransfer) {
        int amountDelta = newTransfer.getAmount() - oldTransfer.getAmount();

        Account otherAccount;
        if (oldTransfer.getSource().equals(this)) {
            otherAccount = oldTransfer.getTarget();
            this.balance -= amountDelta;
            otherAccount.balance += amountDelta;
        } else {
            otherAccount = oldTransfer.getSource();
            this.balance += amountDelta;
            otherAccount.balance -= amountDelta;
        }
        List<Transaction> otherAccountTransactions = otherAccount.getTransactions();
        this.getTransactions().set(this.getTransactions().indexOf(oldTransfer),
                newTransfer);
        otherAccountTransactions.set(otherAccountTransactions.indexOf(oldTransfer),
                newTransfer);
    }

    public void changeName(String name) {
        this.name = name;
    }

    private int balance;
    private String name;
    private List<Transaction> transactions;
}
