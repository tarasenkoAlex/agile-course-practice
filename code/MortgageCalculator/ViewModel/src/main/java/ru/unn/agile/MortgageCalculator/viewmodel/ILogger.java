package ru.unn.agile.MortgageCalculator.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String str);

    List<String> getLogList();
}
