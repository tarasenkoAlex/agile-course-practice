package ru.unn.agile.todoapp.model;

import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void doesDescriptionEqualToTheInitial() throws Exception {
        String initialDescription = "Buy pizza on the way to work";
        Task task = new Task(initialDescription, DateUtils.make("10/10/2016"));

        String actualDescription = task.getDescription();

        assertEquals(initialDescription, actualDescription);
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateWithNullDescription() throws Exception {
        new Task(null, DateUtils.make("10/10/2016"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateWithWhitespacesDescription() throws Exception {
        new Task("  ", DateUtils.make("10/10/2016"));
    }

    @Test
    public void doesExpirationDateEqualToTheInitial() throws Exception {
        Date initialDate = DateUtils.make("05/05/2016");
        Task task = new Task("Work lunch with the dudes", initialDate);

        Date actualDate = task.getExpirationDate();

        assertEquals(initialDate.getTime(), actualDate.getTime());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateWithNullExpirationDate() throws Exception {
        new Task("Work lunch with the dudes", null);
    }
}
