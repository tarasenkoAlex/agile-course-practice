package ru.unn.agile.SolvingQuadraticEquation.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> fakeLog = new ArrayList<>();

    @Override
    public void makeLog(final String s) {
        fakeLog.add(s);
    }

    public List<String> getLog() {
        return fakeLog;
    }
}
