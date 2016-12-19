package ru.unn.agile.CurrencyConverter.infrastructure;

import ru.unn.agile.CurrencyConverter.viewmodel.ViewModel;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModelTests;

import java.io.IOException;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() throws IOException {
        TxtLogger txtLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.setExternalViewModel(new ViewModel(txtLogger));
    }
}
