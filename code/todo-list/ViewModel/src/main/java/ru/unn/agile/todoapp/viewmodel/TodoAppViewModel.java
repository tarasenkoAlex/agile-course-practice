package ru.unn.agile.todoapp.viewmodel;

import java.time.LocalDate;

public class TodoAppViewModel {
    private LocalDate newTaskDate = LocalDate.now();
    private String newTaskDescription = "";
    private boolean addNewTaskButtonEnabled = false;

    public LocalDate getNewTaskDate() {
        return newTaskDate;
    }

    public String getNewTaskDescription() {
        return newTaskDescription;
    }

    public boolean isAddNewTaskButtonEnabled() {
        return addNewTaskButtonEnabled;
    }
}
