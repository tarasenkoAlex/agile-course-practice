package ru.unn.agile.RomanArabicConverter.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> logs;
    private BooleanProperty addedNewLogProperty = new SimpleBooleanProperty();

    public FakeLogger() {
        logs = new ArrayList<String>();
    }

    @Override
    public void log(final String log) {
        addedNewLogProperty.set(false);
        logs.add(log);
        addedNewLogProperty.set(true);
    }

    @Override
    public List<String> getLog() {
        return logs;
    }

    @Override
    public void setListener(final ChangeListener<Boolean> listener) {
        addedNewLogProperty.addListener(listener);
    }

}
