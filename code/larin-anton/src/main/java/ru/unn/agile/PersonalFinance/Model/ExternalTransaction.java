package ru.unn.agile.PersonalFinance.Model;

import java.util.GregorianCalendar;

public class ExternalTransaction implements Transaction {
    public ExternalTransaction(int amount, String description, Category category,
                               GregorianCalendar date) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public GregorianCalendar getDate() {
        return date;
    }


    public static Builder expenseBuilder(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Consider using opposite transaction type");
        }

        return new Builder(-amount);
    }

    public static Builder incomeBuilder(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Consider using opposite transaction type");
        }

        return new Builder(amount);
    }


    public static class Builder {
        public Builder(int amount) {
            this.transactionAmount = amount;
        }

        public Builder description(String description) {
            this.transactionDescription = description;
            return this;
        }

        public Builder category(Category category) {
            this.transactionCategory = category;
            return this;
        }

        public Builder date(GregorianCalendar date) {
            this.transactionDate = date;
            return this;
        }

        public ExternalTransaction build() {
            return new ExternalTransaction(
                    transactionAmount,
                    transactionDescription,
                    transactionCategory,
                    transactionDate);
        }

        private int transactionAmount;
        private String transactionDescription;
        private Category transactionCategory;
        private GregorianCalendar transactionDate;
    }

    private String description;
    private int amount;
    private Category category;
    private GregorianCalendar date;
}
