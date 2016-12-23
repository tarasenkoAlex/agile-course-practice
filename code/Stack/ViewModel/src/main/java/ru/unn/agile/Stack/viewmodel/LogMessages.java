package ru.unn.agile.Stack.viewmodel;

enum LogMessages {
    PUSHED("pushed ");

    private final String message;

    LogMessages(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
