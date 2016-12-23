package ru.unn.agile.ColorConverter.viewmodel;

import java.util.List;

public interface IColorConverterLogger {
    void log(String s);

    List<String> getLog();
}
