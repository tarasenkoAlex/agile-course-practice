package ru.unn.agile.CurrencyConverter.viewmodel;

import java.io.IOException;
import java.util.List;

public interface ILogger {
    void logIt(String s) throws IOException;
    List<String> getLog() throws IOException;
}
