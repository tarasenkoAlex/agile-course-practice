package ru.unn.agile.MyDeque.infrastructure;


import ru.unn.agile.MyDeque.viewmodel.ViewModel;
import ru.unn.agile.MyDeque.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./ViewModelTxtLoglab3.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
