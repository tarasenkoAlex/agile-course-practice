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

        public ExternalTransaction build() {
            return new ExternalTransaction(
                    transactionAmount,
                    transactionDescription,
                    transactionCategory);
        }

        private int transactionAmount;
        private String transactionDescription;
        private Category transactionCategory;
    }

    private String description;
    private int amount;
    private Category category;
}
