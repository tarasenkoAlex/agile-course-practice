package ru.unn.agile.polynomial.infrastructure;

import ru.unn.agile.polynomial.viewmodel.ViewModel;
import ru.unn.agile.polynomial.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./ViewModel_with_TxtLogger_Tests-lab3.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
