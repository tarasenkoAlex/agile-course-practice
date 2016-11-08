package test

import org.junit.Test

import static org.junit.Assert.*;
import main.Stack

/**
 * Created by dkolistr on 08.11.2016.
 */

public class StackTest {
    protected Stack stk;
    private Integer number;
    private Random rand;

    public StackTest() {
        rand = new Random(System.currentTimeMillis());
        number = (int)(rand.nextInt() * (Integer.MAX_VALUE - Integer.MIN_VALUE) + Integer.MIN_VALUE);
    }

    @Test
    public void testIsNewStackEmpty() {
        stk = new Stack();
        assertTrue(stk.isEmpty());
    }

    @Test
    public void testPushToEmptyStack(){
        stk = new Stack();
        stk.push(number);
        assertFalse(stk.isEmpty());
        assertEquals(number, stk.top());
    }

    @Test
    public void testPopFromEmptyStack(){
        stk = new Stack();
        assertNull(stk.pop());
        assertTrue(stk.isEmpty());
    }

    @Test
    public void testPushTwiceToStack(){
        stk = new Stack();
        stk.push(number);
        stk.push(number);
        assertEquals(number, stk.top());
        stk.pop()
        assertEquals(number, stk.top())
    }

    @Test
    public void testPopFromStack(){
        stk = new Stack();
        stk.push(number);
        assertEquals(number, stk.pop())
    }

    @Test
    public void testPushesThenPops(){
        stk = new Stack();
        int pushes = (int)(Math.random() * (1000 - 10) + 10);
        Integer[] pushed = new Integer[pushes];
        for (int i = 0; i < pushes; i++) {
            pushed[i] = rand.nextInt() * (Integer.MAX_VALUE - Integer.MIN_VALUE) + Integer.MIN_VALUE;
            stk.push(pushed[i]);
        }
        for (int i = pushes-1; i >= 0; i--) {
            assertEquals(pushed[i], stk.pop());
        }
    }

    @Test
    public void testPushesAfterPops(){
        stk = new Stack();
        int pushes = (int)(Math.random() * (1000 - 10) + 10);
        for (int i = 0; i < pushes; i++) {
            int pushed = rand.nextInt() * (Integer.MAX_VALUE - Integer.MIN_VALUE) + Integer.MIN_VALUE;
            stk.push(pushed);
            assertEquals(pushed, stk.top());
            assertEquals(pushed, stk.pop());
        }
        assertTrue(stk.isEmpty());
    }

    @Test
    public void testPrint(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        stk = new Stack();
        stk.print();
        assertEquals("", outContent.toString());
        stk.push(number);
        stk.print();
        assertEquals(number.toString(), outContent.toString());
        System.setOut(null);
    }

    @Test
    public void testPrintMultiple(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        stk = new Stack();
        StringBuilder str = new StringBuilder();
        int pushes = (int)(Math.random() * (100 - 20) + 20);
        for (int i = 0; i < pushes; i++) {
            int pushed = rand.nextInt() * (Integer.MAX_VALUE - Integer.MIN_VALUE) + Integer.MIN_VALUE;
            str.append(pushed + " ");
            stk.push(pushed);
        }
        stk.print();
        assertEquals(str.toString(), outContent.toString());
        System.setOut(null);
    }
}
