package ru.unn.agile.PersonalFinance.Model;

public class Transaction {
    public Transaction(int amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    private String description;
    private int amount;
}
