package ru.unn.agile.CurrencyConverter.infrastructure;

import ru.unn.agile.CurrencyConverter.viewmodel.ViewModel;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger txtLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.setExternalViewModel(new ViewModel(txtLogger));
    }
}
