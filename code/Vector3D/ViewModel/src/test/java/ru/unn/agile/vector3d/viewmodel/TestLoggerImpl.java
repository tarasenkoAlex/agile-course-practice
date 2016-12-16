package ru.unn.agile.vector3d.viewmodel;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class TestLoggerImpl implements AbstractLogger {
    Collection<String> records;

    public TestLoggerImpl() {
        records = new LinkedList<String>();
    }

    @Override
    public void putLog(String message) {
        records.add(message);
    }

    @Override
    public Iterator<String> iterator() {
        return records.iterator();
    }
}
