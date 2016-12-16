package ru.unn.agile.vector3d.viewmodel;

public interface AbstractLogger extends Iterable<String> {
    void putLog(final String message);
}
