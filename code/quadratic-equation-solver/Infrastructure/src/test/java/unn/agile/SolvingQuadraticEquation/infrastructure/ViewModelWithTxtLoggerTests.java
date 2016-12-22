package unn.agile.SolvingQuadraticEquation.infrastructure;

import ru.unn.agile.SolvingQuadraticEquation.viewmodel.ViewModel;
import ru.unn.agile.SolvingQuadraticEquation.viewmodel.ViewModelTests;


public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
                new TxtLogger("./ViewModel_with_TxtLogger_Tests-lab3.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
