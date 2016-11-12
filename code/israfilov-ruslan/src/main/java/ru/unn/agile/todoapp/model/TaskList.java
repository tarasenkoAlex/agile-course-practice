package ru.unn.agile.todoapp.model;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public List<Task> getAll() {
        return tasks;
    }

    public void add(final Task task) {
        tasks.add(task);
    }
}
