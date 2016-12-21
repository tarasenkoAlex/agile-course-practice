package ru.unn.agile.Fraction.infrastructure;

import ru.unn.agile.Fraction.viewmodel.FractionViewModel;
import ru.unn.agile.Fraction.viewmodel.FractionViewModelTests;

import java.io.IOException;

public class FractionViewModelWithFractionLoggerTests extends FractionViewModelTests {
    @Override
    public void setUp() throws IOException {
        FractionLogger realLogger =
            new FractionLogger("./FractionViewModelWithFractionLoggerTests.log");
        super.setFractionViewModel(new FractionViewModel(realLogger));
    }
}
