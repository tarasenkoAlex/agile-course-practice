package ru.unn.agile.Stack.viewmodel;

class FakeLogger implements ILogger {
    private String log = "";

    @Override
    public void log(final String s) {
        log += s;
        log += '\n';
    }

    @Override
    public String getLog() {
        return log;
    }
}
