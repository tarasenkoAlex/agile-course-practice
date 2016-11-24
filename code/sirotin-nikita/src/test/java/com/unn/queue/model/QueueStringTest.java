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
        assertEquals("First", testQueue.getHead());
    }

    @Test
    public void checkHeadIsNullInEmptyQueue() {
        assertNull(new Queue<>().getHead());
    }

    @Test
    public void checkSizeIsCorrect() {
        assertEquals(4, testQueue.getSize());
    }

    @Test
    public void checkSizeIsCorrectAfterEnqueue() {
        testQueue.enqueue("Fifth");
        assertEquals(5, testQueue.getSize());
    }

    @Test
    public void checkSizeIsCorrectAfterDequeue() {
        testQueue.dequeue();
        assertEquals(3, testQueue.getSize());
    }

    @Test
    public void canEnqueue() {
        testQueue.enqueue("Fifth");
        assertEquals(4, testQueue.searchElement("Fifth"));
    }

    @Test
    public void canDequeue() {
        testQueue.dequeue();
        assertEquals("Second", testQueue.getHead());
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
        assertEquals(2, testQueue.searchElement("Third"));
    }

    @Test(expected = NoSuchElementException.class)
    public void canNotSearchNonExistingElement() {
        testQueue.searchElement("Seventh");
    }

    @Test
    public void checkToStringForNotEmptyQueue() {
        String expectedString = "[First, Second, Third, Fourth]";
        assertEquals(expectedString, testQueue.toString());
    }

    @Test
    public void checkToStringForEmptyQueue() {
        assertEquals("[]", new Queue<>().toString());
    }

    @Test
    public void canAddedNewQueue() {
        Queue<String> newQueue = new Queue<>();
        newQueue.enqueue("Fifth");
        newQueue.enqueue("Sixth");
        newQueue.enqueue("Seventh");
        testQueue.addElements(newQueue);
        String expectedNewString = "[First, Second, Third, Fourth, Fifth, Sixth, Seventh]";
        assertEquals(expectedNewString, testQueue.toString());
    }
}
