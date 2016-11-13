package ru.unn.agile.todoapp.model;

import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;
import static ru.unn.agile.todoapp.model.DateHelper.*;

public class TaskTest {
    @Test
    public void doesDescriptionEqualToTheInitial() throws Exception {
        String initialDescription = "Buy pizza on the way to work";
        Task task = new Task(initialDescription, makeDate("10/10/2016"));

        String actualDescription = task.getDescription();

        assertEquals(initialDescription, actualDescription);
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateWithNullDescription() throws Exception {
        new Task(null, makeDate("10/10/2016"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateWithWhitespacesDescription() throws Exception {
        new Task("  ", makeDate("10/10/2016"));
    }

    @Test
    public void doesExpirationDateEqualToTheInitial() throws Exception {
        Date initialDate = makeDate("05/05/2016");
        Task task = new Task("Work lunch with the dudes", initialDate);

        Date actualDate = task.getDueDate();

        assertEquals(initialDate.getTime(), actualDate.getTime());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateWithNullExpirationDate() throws Exception {
        new Task("Work lunch with the dudes", null);
    }

    @Test
    public void isAbleToMarkTaskAsDone() throws Exception {
        Task task = new Task("Meeting with the customers", makeDate("03/15/2016"));

        task.markAsDone();

        assertTrue(task.isDone());
    }
}
