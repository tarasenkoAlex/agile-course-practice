package ru.unn.agile.todoapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Task {
    private final String description;
    private final Date dueDate;
    private boolean isDoneFlag = false;

    public Task(final String description, final Date dueDate) {
        Objects.requireNonNull(description, "description is not able to be null");
        Objects.requireNonNull(dueDate, "dueDate is not able to be null");
        if (description.trim().length() == 0) {
            String message = "description is not able to be whitespaces string";
            throw new IllegalArgumentException(message);
        }
        this.description = description;
        this.dueDate = dueDate;
    }

    public final String getDescription() {
        return description;
    }

    public final Date getDueDate() {
        return dueDate;
    }

    public final boolean isDone() {
        return isDoneFlag;
    }

    public void markAsDone() {
        isDoneFlag = true;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return description + ", " + dateFormat.format(dueDate);
    }
}
