package ru.unn.agile.polynomial.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> logData = new ArrayList<>();

    @Override
    public void log(final String s) {
        logData.add(s);
    }

    @Override
    public List<String> getLog() {
        return logData;
    }
}
