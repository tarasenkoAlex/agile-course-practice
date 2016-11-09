package ru.unn.agile.PersonalFinance.Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Account {
    private int balance;
    private String name;
    private final List<Transaction> transactions;

    public Account(final int startingBalance, final String name) {
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

    public void addExternalTransaction(final ExternalTransaction expense) {
        balance += expense.getAmount();
        addTransactionKeepingOrder(transactions, expense);
    }

    public void transferTo(final Account other, final int amount,
                           final GregorianCalendar date) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Consider adding a transfer in opposite direction");
        }

        balance -= amount;
        other.balance += amount;
        Transfer transfer = new Transfer(amount, this, other, date);
        addTransactionKeepingOrder(transactions, transfer);
        addTransactionKeepingOrder(other.transactions, transfer);
    }

    public void transferTo(final Account other, final int amount) {
        transferTo(other, amount, null);
    }

    public void deleteTransaction(final Transaction transaction) {
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
            final ExternalTransaction oldTransaction,
            final ExternalTransaction newTransaction) {

        balance -= oldTransaction.getAmount() - newTransaction.getAmount();
        transactions.remove(transactions.indexOf(oldTransaction));
        addTransactionKeepingOrder(transactions, newTransaction);
    }

    public void replaceTransfer(final Transfer oldTransfer, final Transfer newTransfer) {
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
        transactions.remove(this.getTransactions().indexOf(oldTransfer));
        addTransactionKeepingOrder(transactions, newTransfer);
        otherAccountTransactions.remove(otherAccountTransactions.indexOf(oldTransfer));
        addTransactionKeepingOrder(otherAccountTransactions, newTransfer);
    }

    public void changeName(final String name) {
        this.name = name;
    }

    private void addTransactionKeepingOrder(
            final List<Transaction> transactions,
            final Transaction replacement) {

        int insertionIndex = transactions.size() - 1;
        while (insertionIndex >= 0
                && transactions.get(insertionIndex).compareTo(replacement) == 1) {
            insertionIndex--;
        }

        transactions.add(insertionIndex + 1, replacement);
    }
}
