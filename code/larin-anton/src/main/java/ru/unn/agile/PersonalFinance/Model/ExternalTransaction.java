package ru.unn.agile.PersonalFinance.Model;

public class ExternalTransaction implements Transaction {
    public ExternalTransaction(int amount, String description, Category category) {
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean isExternal() {
        return true;
    }

    @Override
    public boolean isTransfer() {
        return false;
    }

    public Category getCategory() {
        return category;
    }

    private String description;
    private int amount;
    private Category category;
}
