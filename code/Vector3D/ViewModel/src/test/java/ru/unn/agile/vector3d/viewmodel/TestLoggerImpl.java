package ru.unn.agile.vector3d.viewmodel;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class TestLoggerImpl implements AbstractLogger {
    private final Collection<String> records;

    public TestLoggerImpl() {
        records = new LinkedList<String>();
    }

    @Override
    public void putLog(final String message, final Object... args) {
        records.add(MessageFormat.format(message, args));
    }

    @Override
    public Iterator<String> iterator() {
        return records.iterator();
    }
}
