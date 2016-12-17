package ru.unn.agile.MultisystemCalculator.viewmodel;

import java.io.IOException;
import java.util.List;

public interface ILogger {
    void log(String s) throws IOException;

    List<String> getLog() throws IOException;
}
