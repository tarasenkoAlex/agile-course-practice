package ru.unn.agile.VolumeCalculator.infrastructure;

import ru.unn.agile.VolumeCalculator.viewModel.VolumeCalculatorViewModel;
import ru.unn.agile.VolumeCalculator.viewModel.VolumeCalculatorViewModelTest;

/**
 * Created by Elena on 12/18/2016.
 */
public class ViewModelWithTxtLoggerTests extends VolumeCalculatorViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger =
                new TxtLogger("./ViewModel_with_TxtLogger_Tests-lab3.log");
        super.setExternalViewModel(new VolumeCalculatorViewModel(realLogger));
    }
}
