package ru.unn.agile.todoapp.viewmodel;

import ru.unn.agile.todoapp.model.Task;
import ru.unn.agile.todoapp.model.TaskList;

import java.time.LocalDate;
import java.util.List;

public class TodoAppViewModel {
    private LocalDate newTaskDueDate = LocalDate.now();
    private String newTaskDescription = "";
    private boolean addNewTaskButtonEnabled = false;
    private TaskList tasks = new TaskList();

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
        tasks.add(new Task(this.newTaskDescription, this.newTaskDueDate));
        this.newTaskDescription = "";
        this.newTaskDueDate = LocalDate.now();
    }

    public List<Task> getTasks() {
        return tasks.getAll();
    }
}
