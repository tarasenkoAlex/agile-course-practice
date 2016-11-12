package ru.unn.agile.todoapp.model;

import java.util.Date;
import java.util.Objects;

public class Task {
    private final String description;
    private final Date expirationDate;
    private boolean isDoneFlag = false;

    public Task(final String description, final Date expirationDate)
            throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(description, "description is not able to be null");
        Objects.requireNonNull(expirationDate, "expirationDate is not able to be null");
        if (description.trim().length() == 0) {
            String message = "description is not able to be whitespaces string";
            throw new IllegalArgumentException(message);
        }
        this.description = description;
        this.expirationDate = expirationDate;
    }

    public final String getDescription() {
        return description;
    }

    public final Date getExpirationDate() {
        return expirationDate;
    }

    public final boolean isDone() {
        return isDoneFlag;
    }

    public void markAdDone() {
        // TODO
    }
}
