package ru.unn.agile.PersonalFinance.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenManagingAccounts {
    @Test
    public void ledgerIsEmptyUponCreation() {
        Ledger ledger = new Ledger();

        assertTrue(ledger.getAccounts().isEmpty());
    }

    @Test
    public void andAccountIsAddedLedgerIsNotEmptyAnymore() {
        Ledger ledger = new Ledger();

        ledger.addAccount(new Account(0, ""));

        assertFalse(ledger.getAccounts().isEmpty());
    }

    @Test
    public void andAccountIsAddedItIsLastInLedgersAccountsList() {
        Ledger ledger = new Ledger();
        Account cash = new Account(75, "Cash");

        ledger.addAccount(cash);

        assertEquals(75, ledger.getAccounts().get(0).getBalance());
        assertEquals("Cash", ledger.getAccounts().get(0).getName());
    }
}
