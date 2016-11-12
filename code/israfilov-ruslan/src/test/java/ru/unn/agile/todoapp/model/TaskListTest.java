package ru.unn.agile.todoapp.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void cantModifyGetAllCollection() throws Exception {
        taskList.add(taskListItem);
        List<Task> allTasks = taskList.getAll();

        try {
            allTasks.clear();
            allTasks.add(taskListItem);
            allTasks.add(new Task("New event", makeDate("19/04/2017")));
            allTasks.remove(taskListItem);
        }
        catch (Exception ex) { }

        assertEquals(allTasks.size(), 1);
        assertContains(allTasks, taskListItem);
    }

    @Test
    public void hasLinkBetweenGetAllCollectionAndSourceList() throws Exception {
        List<Task> allTasks = taskList.getAll();
        taskList.add(new Task("New event", makeDate("19/04/2017")));

        assertListEquals(allTasks, taskList.getAll());
    }

    @Test(expected = NullPointerException.class)
    public void failToAddNullTask() throws Exception {
        taskList.add(null);
    }
}
