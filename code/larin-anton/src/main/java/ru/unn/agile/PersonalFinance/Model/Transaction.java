package ru.unn.agile.PersonalFinance.Model;

public interface Transaction {
    int getAmount();

    boolean isExternal();
    boolean isTransfer();
}
