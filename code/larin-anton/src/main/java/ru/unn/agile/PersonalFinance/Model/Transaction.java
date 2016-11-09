package ru.unn.agile.PersonalFinance.Model;

public interface Transaction {
    public String getDescription();

    public int getAmount();

    public Account otherAccount();

    public boolean isExternal();
    public boolean isTransfer();
}
