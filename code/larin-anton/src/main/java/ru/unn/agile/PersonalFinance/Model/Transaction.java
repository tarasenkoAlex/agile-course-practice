package ru.unn.agile.PersonalFinance.Model;

public interface Transaction {
    public int getAmount();

    public boolean isExternal();
    public boolean isTransfer();
}
