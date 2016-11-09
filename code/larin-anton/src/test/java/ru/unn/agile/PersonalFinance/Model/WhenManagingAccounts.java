package ru.unn.agile.PersonalFinance.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class WhenManagingAccounts {
    @Test
    public void ledgerIsEmptyUponCreation() {
        Ledger ledger = new Ledger();

        assertTrue(ledger.isEmpty());
    }

    @Test
    public void andAccountIsAddedLedgerIsNotEmptyAnymore() {
        Ledger ledger = new Ledger();

        ledger.addAccount(new Account(0));

        assertFalse(ledger.isEmpty());
    }
}
