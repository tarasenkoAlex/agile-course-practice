package ru.unn.agile.PersonalFinance.Model;

public class Transaction {
    public Transaction(int amount, String description, Account otherAccount) {
        this.amount = amount;
        this.description = description;
        this.otherAccount = otherAccount;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public Account otherAccount() {
        return otherAccount;
    }

    private String description;
    private int amount;
    private Account otherAccount;
}
