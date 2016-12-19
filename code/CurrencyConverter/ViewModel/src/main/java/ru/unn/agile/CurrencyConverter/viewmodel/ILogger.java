package ru.unn.agile.CurrencyConverter.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String s);
    List<String> getLog();
}
