package ru.unn.agile.MyDeque.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> logList = new ArrayList<>();

    @Override
    public void log(final String data) {
        logList.add(data);
        }

    @Override
    public List<String> getLog() {
            return logList;
    }
}
