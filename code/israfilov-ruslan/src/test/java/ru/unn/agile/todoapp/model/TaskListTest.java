package ru.unn.agile.todoapp.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static ru.unn.agile.todoapp.model.AssertHelper.*;
import static ru.unn.agile.todoapp.model.DateHelper.*;

public class TaskListTest {
    private Task taskListItem;
    private TaskList taskList;

    @Before
    public void instanceTask() throws Exception {
        taskListItem = new Task(
            "Work lunch with the dudes",
            makeDate("04/08/2016")
        );
        taskList = new TaskList();
    }

    @Test
    public void canAddNewTask() throws Exception {
        taskList.add(taskListItem);

        assertContains(taskList.getAll(), taskListItem);
    }

    @Test
    public void canRemoveExistingTask() throws Exception {
        taskList.add(taskListItem);

        boolean isFound = taskList.remove(taskListItem);

        assertTrue(isFound);
        assertNotContains(taskList.getAll(), taskListItem);
    }

    @Test
    public void cantRemoveNotExistingTask() throws Exception {
        boolean isFound = taskList.remove(taskListItem);

        assertFalse(isFound);
        assertNotContains(taskList.getAll(), taskListItem);
    }
}
