package ru.unn.agile.todoapp.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static ru.unn.agile.todoapp.model.AssertHelper.*;
import static ru.unn.agile.todoapp.model.DateHelper.*;

public class TaskListTest {
    private Task taskListItem;

    @Before
    public void instanceTask() throws Exception {
        taskListItem = new Task(
            "Work lunch with the dudes",
            makeDate("04/08/2016")
        );
    }

    @After
    public void clearTask() {
        taskListItem = null;
    }

    @Test
    public void canAddNewTask() throws Exception {
        TaskList taskList = new TaskList();

        taskList.add(taskListItem);

        assertContains(taskList.getAll(), taskListItem);
    }
}
