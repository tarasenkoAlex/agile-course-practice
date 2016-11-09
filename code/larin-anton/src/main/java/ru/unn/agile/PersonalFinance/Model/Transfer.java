package ru.unn.agile.PersonalFinance.Model;

import java.util.GregorianCalendar;

public class Transfer implements Transaction {
    private int amount;
    private Account source;
    private Account target;
    private GregorianCalendar date;

    public Transfer(final int amount,
                    final Account source,
                    final Account target,
                    final GregorianCalendar date) {

        this.amount = amount;
        this.source = source;
        this.target = target;
        this.date = date;
    }

    public Transfer(
            final int amount,
            final Account source,
            final Account target) {
        this(amount, source, target, null);
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public Account getSource() {
        return source;
    }

    public Account getTarget() {
        return target;
    }

    @Override
    public boolean isExternal() {
        return false;
    }

    @Override
    public boolean isTransfer() {
        return true;
    }

    public void setSource(final Account source) {
        this.source = source;
    }

    public void setTarget(final Account target) {
        this.target = target;
    }

    public GregorianCalendar getDate() {
        return date;
    }
}
