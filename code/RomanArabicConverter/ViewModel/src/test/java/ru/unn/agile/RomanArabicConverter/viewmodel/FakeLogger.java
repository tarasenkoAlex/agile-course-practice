package ru.unn.agile.RomanArabicConverter.viewmodel;

import javafx.beans.value.ChangeListener;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> logs;

    public FakeLogger() {
        logs = new ArrayList<String>();
    }

    @Override
    public void log(final String log) {
        logs.add(log);
    }

    @Override
    public List<String> getLog() {
        return logs;
    }

    @Override
    public void setListener(final ChangeListener<Boolean> listener) {

    }

}
