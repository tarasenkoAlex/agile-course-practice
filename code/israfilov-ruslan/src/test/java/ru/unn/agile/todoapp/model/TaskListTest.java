package ru.unn.agile.todoapp.model;

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

    @Test(expected = UnsupportedOperationException.class)
    public void cantClearGetAllCollection() throws Exception {
        taskList.add(taskListItem);
        List<Task> allTasks = taskList.getAll();

        allTasks.clear();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cantAddGetAllCollection() throws Exception {
        taskList.add(taskListItem);
        List<Task> allTasks = taskList.getAll();

        allTasks.add(new Task("New event", makeDate("19/04/2017")));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cantRemoveGetAllCollection() throws Exception {
        taskList.add(taskListItem);
        List<Task> allTasks = taskList.getAll();

        allTasks.remove(taskListItem);
    }

    @Test
    public void hasLinkBetweenGetAllCollectionAndSourceList() throws Exception {
        List<Task> allTasks = taskList.getAll();
        taskList.add(taskListItem);

        assertListEquals(allTasks, taskList.getAll());
    }

    @Test
    public void isSizeEqualsTo1After2AddAnd1Remove() throws Exception {
        taskList.add(taskListItem);
        taskList.add(new Task("New event", makeDate("19/04/2017")));
        taskList.remove(taskListItem);

        assertEquals(taskList.getSize(), 1);
    }

    @Test(expected = NullPointerException.class)
    public void failToAddNullTask() throws Exception {
        taskList.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToAddDuplication() throws Exception {
        taskList.add(taskListItem);
        taskList.add(taskListItem);
    }
}
