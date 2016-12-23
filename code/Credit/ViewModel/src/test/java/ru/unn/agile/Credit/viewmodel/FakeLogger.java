package ru.unn.agile.Credit.viewmodel;


import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> logArrayList = new ArrayList<>();

    @Override
    public void log(final String s) {
        logArrayList.add(s);
    }

    @Override
    public List<String> getLog() {
        return logArrayList;
    }
}
