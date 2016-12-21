package ru.unn.agile.VolumeCalculator.infrastructure;

import ru.unn.agile.VolumeCalculator.viewModel.VolumeCalculatorViewModel;
import ru.unn.agile.VolumeCalculator.viewModel.VolumeCalculatorViewModelTest;

/**
 * Created by Elena on 12/18/2016.
 */
public class ViewModelWithVolumeCalculatorLoggerTests extends VolumeCalculatorViewModelTest {
    @Override
    public void setUp() {
        VolumeCalculatorLogger realVolumeCalculatorLogger =
                new VolumeCalculatorLogger("./lab3-Volume-Calculator-Logger-ViewModel.log");
        super.setExternalViewModel(new VolumeCalculatorViewModel(realVolumeCalculatorLogger));
    }
}
