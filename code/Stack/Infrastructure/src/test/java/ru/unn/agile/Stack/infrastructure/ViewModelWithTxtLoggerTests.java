package ru.unn.agile.Stack.infrastructure;

import ru.unn.agile.Stack.viewmodel.ILogger;
import ru.unn.agile.Stack.viewmodel.LoggerTests;

public class ViewModelWithTxtLoggerTests extends LoggerTests {
    @Override
    public ILogger createLogger() {
        return new TxtLogger("./ViewModel_with_TxtLogger_Tests-lab3.log");
    }
}
