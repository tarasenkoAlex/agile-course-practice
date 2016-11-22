package ru.unn.agile.todoapp.viewmodel;

public class TaskListCellViewModel {
    private boolean isDoneCheckboxChecked = false;

    public boolean isDoneCheckboxChecked() {
        return isDoneCheckboxChecked;
    }

    public void clickIsDoneCheckBox() {
        this.isDoneCheckboxChecked = true;
    }
}
