package ru.unn.agile.Credit.infrastructure;

import ru.unn.agile.Credit.viewmodel.ViewModel;
import ru.unn.agile.Credit.viewmodel.ViewModelLogTests;

public class ViewModelWithTxtLoggerTests extends ViewModelLogTests {
    @Override
    public void setUp() {
        ViewModel viewModel = new ViewModel();
        TxtLogger realLogger =
            new TxtLogger("./ViewModelWithTxtLoggerTests-lab3-legacy.log");
        viewModel.setLogger(realLogger);
        super.setViewModelWithLogger(viewModel, realLogger);
    }
}
