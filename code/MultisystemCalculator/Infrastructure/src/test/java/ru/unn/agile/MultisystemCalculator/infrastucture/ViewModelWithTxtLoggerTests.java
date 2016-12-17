package ru.unn.agile.MultisystemCalculator.infrastucture;

import ru.unn.agile.MultisystemCalculator.viewmodel.CalculatorViewModel;
import ru.unn.agile.MultisystemCalculator.viewmodel.MultisystemCalculatorViewModelTest;

import java.io.IOException;

public class ViewModelWithTxtLoggerTests extends MultisystemCalculatorViewModelTest {
    @Override
    public void setUp() throws IOException {
        TxtLogger realLogger =
            new TxtLogger("./ViewModel_with_TxtLogger_Tests-lab3.log");
        super.setExternalViewModel(new CalculatorViewModel(realLogger));
    }
}
