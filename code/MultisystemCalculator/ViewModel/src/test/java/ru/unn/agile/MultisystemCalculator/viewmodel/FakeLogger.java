package ru.unn.agile.MultisystemCalculator.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> logs = new ArrayList<>();

    @Override
    public void log(final String str) {
        logs.add(str);
    }

    @Override
    public List<String> getLog() {
        return logs;
    }
}
