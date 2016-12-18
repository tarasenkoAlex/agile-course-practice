package ru.unn.agile.RomanArabicConverter.viewmodel;

import javafx.beans.value.ChangeListener;

import java.util.List;

/**
 * Created by ponom on 12.12.2016.
 */
public interface ILogger {

    void log(String log);

    List<String> getLog();

    void setListener(ChangeListener<Boolean> listener);

}

