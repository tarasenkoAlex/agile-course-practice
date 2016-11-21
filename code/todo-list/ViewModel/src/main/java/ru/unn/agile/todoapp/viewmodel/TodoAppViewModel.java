package ru.unn.agile.todoapp.viewmodel;

import java.time.LocalDate;

public class TodoAppViewModel {
    private LocalDate newTaskDueDate = LocalDate.now();
    private String newTaskDescription = "";
    private boolean addNewTaskButtonEnabled = false;

    public LocalDate getNewTaskDueDate() {
        return newTaskDueDate;
    }

    public String getNewTaskDescription() {
        return newTaskDescription;
    }

    public boolean isAddNewTaskButtonEnabled() {
        return addNewTaskButtonEnabled;
    }

    public void setNewTaskDescription(String newTaskDescription) {
        this.newTaskDescription = newTaskDescription;

        this.addNewTaskButtonEnabled = !newTaskDescription.isEmpty();
    }

    public void setDueDate(LocalDate dueDate) {
        this.newTaskDueDate = dueDate;
    }

    public void pressAddNewTaskButton() {
        this.newTaskDescription = "";
        this.newTaskDueDate = LocalDate.now();
    }
}
