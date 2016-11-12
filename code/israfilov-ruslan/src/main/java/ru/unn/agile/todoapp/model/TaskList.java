package ru.unn.agile.todoapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public List<Task> getAll() {
        return Collections.unmodifiableList(tasks);
    }

    public void add(final Task task) throws IllegalArgumentException {
        Objects.requireNonNull(task, "task is not able to be null");
        if (tasks.contains(task)) {
            String message = "Same task already exists in the list";
            throw new IllegalArgumentException(message);
        }
        tasks.add(task);
    }

    public boolean remove(final Task task) {
        return tasks.remove(task);
    }

    public final int getSize() {
        return tasks.size();
    }
}
