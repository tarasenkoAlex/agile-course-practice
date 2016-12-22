package ru.unn.agile.ColorConverter.infrastructure_colorConverter;

import ru.unn.agile.ColorConverter.viewmodel.ViewModel;
import ru.unn.agile.ColorConverter.viewmodel.ViewModelTest;

public class ViewModelColorConverterWithTxtLoggerTests extends ViewModelTest {
    @Override
    public void setUp() {
        ColorConverterLogger realLogger =
                new ColorConverterLogger("./ViewModel_with_TxtLogger_ColorConverter.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
