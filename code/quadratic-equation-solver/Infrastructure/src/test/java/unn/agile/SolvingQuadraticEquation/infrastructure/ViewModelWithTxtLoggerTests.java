package unn.agile.SolvingQuadraticEquation.infrastructure;

import ru.unn.agile.SolvingQuadraticEquation.viewmodel.ViewModel;
import ru.unn.agile.SolvingQuadraticEquation.viewmodel.ViewModelTests;


public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        FileLogger logger =
                new FileLogger("./ViewModel_with_TxtLogger_Tests-lab3.makeLog");
        super.setExteriorlViewModel(new ViewModel(logger));
    }
}
