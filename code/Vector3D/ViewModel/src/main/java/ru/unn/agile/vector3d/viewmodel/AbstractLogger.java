package ru.unn.agile.vector3d.viewmodel;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractLogger implements Iterable<String> {
    protected Set<LoggerListener> listeners = new LinkedHashSet<>();

    public interface LoggerListener {
        void onLogAdded(String message);
    }

    public abstract void putLog(String message, Object... args);

    public void addListener(LoggerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(LoggerListener listener) {
        listeners.remove(listener);
    }
}
