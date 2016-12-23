package ru.unn.agile.Stack.viewmodel;

enum LogMessages {
    PUSHED("pushed "),
    POP("pop "),
    TOP("top is "),
    IS_EMPTY("stack is empty"),
    IS_NOT_EMPTY("stack is not empty"),
    PRINTED("stack was printed");

    private final String message;

    LogMessages(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
