package com.unn.queue.model;

import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

public class QueueStringTest {

    private Queue<String> testQueue;

    @Before
    public void beforeTest() {
        testQueue = new Queue<>();
        testQueue.enqueue("First");
        testQueue.enqueue("Second");
        testQueue.enqueue("Third");
        testQueue.enqueue("Fourth");
    }

    @Test
    public void canCreateQueue() {
        Queue newQueue = new Queue();
        assertNotNull(newQueue);
    }

    @Test
    public void checkHeadExists() {
        assertEquals(testQueue.getHead(), "First");
    }

    @Test
    public void checkHeadIsNullInEmptyQueue() {
        assertNull(new Queue<>().getHead());
    }

    @Test
    public void checkSizeIsCorrect() {
        assertEquals(testQueue.getSize(), 4);
    }

    @Test
    public void checkSizeIsCorrectAfterEnqueue() {
        testQueue.enqueue("Fifth");
        assertEquals(testQueue.getSize(), 5);
    }

    @Test
    public void checkSizeIsCorrectAfterDequeue() {
        testQueue.dequeue();
        assertEquals(testQueue.getSize(), 3);
    }

    @Test
    public void canEnqueue() {
        testQueue.enqueue("Fifth");
        assertEquals(testQueue.searchElement("Fifth"), 4);
    }

    @Test
    public void canDequeue() {
        testQueue.dequeue();
        assertEquals(testQueue.getHead(), "Second");
    }

    @Test
    public void canRemoveExistingElement() {
        assertTrue(testQueue.remove("Third"));
    }

    @Test
    public void canNotRemoveNonExistingElement() {
        assertFalse(testQueue.remove("Sixth"));
    }

    @Test
    public void queueIsEmptiness() {
        assertFalse(testQueue.isEmpty());
    }

    @Test
    public void canSearchExistingElement() {
        assertEquals(testQueue.searchElement("Third"), 2);
    }

    @Test(expected = NoSuchElementException.class)
    public void canNotSearchNonExistingElement() {
        testQueue.searchElement("Seventh");
    }

    @Test
    public void checkToStringForNotEmptyQueue() {
        String expectedString = "[First, Second, Third, Fourth]";
        assertEquals(testQueue.toString(), expectedString);
    }

    @Test
    public void checkToStringForEmptyQueue() {
        assertEquals(new Queue<>().toString(), "[]");
    }

    @Test
    public void canAddedNewQueue() {
        Queue<String> newQueue = new Queue<>();
        newQueue.enqueue("Fifth");
        newQueue.enqueue("Sixth");
        newQueue.enqueue("Seventh");
        testQueue.addElements(newQueue);
        String expectedNewString = "[First, Second, Third, Fourth, Fifth, Sixth, Seventh]";
        assertEquals(testQueue.toString(), expectedNewString);
    }
}
