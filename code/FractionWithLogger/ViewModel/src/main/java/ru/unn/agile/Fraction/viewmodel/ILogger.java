package ru.unn.agile.Fraction.viewmodel;

import java.io.IOException;
import java.util.List;

public interface ILogger {
    void toLog(String s) throws IOException;

    List<String> getLog() throws IOException;
}
