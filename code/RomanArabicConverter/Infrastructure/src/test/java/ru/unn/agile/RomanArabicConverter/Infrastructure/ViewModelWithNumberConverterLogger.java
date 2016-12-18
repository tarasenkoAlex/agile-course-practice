package ru.unn.agile.RomanArabicConverter.Infrastructure;

import ru.unn.agile.RomanArabicConverter.viewmodel.ViewModel;
import ru.unn.agile.RomanArabicConverter.viewmodel.ViewModelTests;

/**
 * Created by ponom on 18.12.2016.
 */
public class ViewModelWithNumberConverterLogger extends ViewModelTests {
    @Override
    public void setUp() {
        NumberConverterLogger realLogger =
                new NumberConverterLogger("./ViewModel_with_NumberConverterLogger.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }

}

