package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import ru.unn.agile.PersonalFinance.Model.Account;

public class WhenCreatingAccountViewModel {
    private Account sourceAccount;

    @Before
    public void setUp() throws Exception {
        sourceAccount = new Account(1000, "Cash");
    }
}
