package ru.unn.agile.vector3d.viewmodel;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class TestLoggerImpl extends AbstractLogger {
    private final Collection<String> records;

    public TestLoggerImpl() {
        records = new LinkedList<String>();
    }

    @Override
    public void putLog(final String message, final Object... args) {
        String msg = MessageFormat.format(message, args);
        records.add(msg);
        for (LoggerListener listener : getListeners()) {
            listener.onLogAdded(msg);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return records.iterator();
    }
}
