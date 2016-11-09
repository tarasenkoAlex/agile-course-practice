package ru.unn.agile.PersonalFinance.Model;

import java.util.GregorianCalendar;

public interface Transaction extends Comparable<Transaction> {
    int getAmount();
    GregorianCalendar getDate();

    boolean isExternal();
    boolean isTransfer();
}
