package ru.unn.agile.Stack.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {
    private Stack stk;
    // push and pop the same number
    private final Integer number = 12345;
    private final Integer[] numbers = new Integer[PUSHES_MAX];
    // minimum of pushes
    private static final int PUSHES_MIN = 10;
    // maximum of pushes
    private static final int PUSHES_MAX = 1000;

    public StackTest() {
        numbers[0] = 0;
        numbers[1] = Integer.MIN_VALUE;
        numbers[2] = Integer.MAX_VALUE;
        for (int i = 3; i < PUSHES_MAX / 2; i++) {
            numbers[i] = i;
        }
        for (int i = PUSHES_MAX / 2; i < PUSHES_MAX; i++) {
            numbers[i] = -1 * i;
        }
    }

    @Test
    public void testIsNewStackEmpty() {
        stk = new Stack();
        assertTrue(stk.isEmpty());
    }

    @Test
    public void testIsFullStackNotEmpty() {
        stk = new Stack();
        stk.push(number);
        assertFalse(stk.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPop() {
        stk = new Stack();
        stk.push(number);
        stk.pop();
        assertTrue(stk.isEmpty());
    }

    @Test
    public void testPushToEmptyStack() {
        stk = new Stack();
        stk.push(number);
        assertEquals(number, stk.top());
    }

    @Test
    public void testPopFromEmptyStack() {
        stk = new Stack();
        assertNull(stk.pop());
        assertTrue(stk.isEmpty());
    }

    @Test
    public void testTopInEmptyStack() {
        stk = new Stack();
        assertNull(stk.top());
        assertTrue(stk.isEmpty());
    }

    @Test
    public void testPushTwiceToStack() {
        stk = new Stack();
        stk.push(number);
        stk.push(number);
        assertEquals(number, stk.top());
        stk.pop();
        assertEquals(number, stk.top());
    }

    @Test
    public void testPopFromStack() {
        stk = new Stack();
        stk.push(number);
        assertEquals(number, stk.pop());
    }

    @Test
    public void testPushesThenPops() {
        stk = new Stack();
        for (int i = 0; i < PUSHES_MAX; i++) {
            stk.push(numbers[i]);
        }
        for (int i = PUSHES_MAX - 1; i >= 0; i--) {
            assertEquals(numbers[i], stk.pop());
        }
    }

    @Test
    public void testPushesAfterPops() {
        stk = new Stack();
        for (int i = 0; i < PUSHES_MAX; i++) {
            stk.push(numbers[i]);
            assertEquals(numbers[i].toString(), stk.top().toString());
            assertEquals(numbers[i].toString(), stk.pop().toString());
        }
        assertTrue(stk.isEmpty());
    }

    @Test
    public void testPrint() {
        stk = new Stack();
        assertEquals("Stack is empty! Nothing to print!", stk.print());
        stk.push(number);
        assertEquals(number.toString(), stk.print());
    }

    @Test
    public void testPrintMultiple() {
        stk = new Stack();
        String str = "";
        for (int i = 0; i < PUSHES_MIN; i++) {
            str += numbers[i].toString() + " ";
            stk.push(numbers[i]);
        }
        assertEquals(str, stk.print());
    }
}
