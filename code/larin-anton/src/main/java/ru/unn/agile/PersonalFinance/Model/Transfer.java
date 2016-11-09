package ru.unn.agile.PersonalFinance.Model;

public class Transfer implements Transaction {
    public Transfer(int amount, Account otherAccount) {
        this.amount = amount;
        this.otherAccount = otherAccount;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public Account otherAccount() {
        return otherAccount;
    }

    @Override
    public boolean isExternal() {
        return false;
    }

    @Override
    public boolean isTransfer() {
        return true;
    }

    private int amount;
    private Account otherAccount;
}
