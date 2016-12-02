package ru.unn.agile.Stack.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private final Integer number = 12345;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.txtinputProperty().get());
        assertEquals("", viewModel.txtprintProperty().get());
        assertEquals("", viewModel.txtlogProperty().get());
        assertEquals("", viewModel.txtmsgProperty().get());
    }

    @Test
    public void badTyping() {
        viewModel.txtinputProperty().set("a");
        viewModel.push();
        assertEquals("Bad input!", viewModel.txtlogProperty().get());
    }

    @Test
    public void canPush() {
        viewModel.txtinputProperty().set("1");
        viewModel.push();
        assertEquals(1, (int) viewModel.getStack().top());
    }

    @Test
    public void canPushTwice() {
        viewModel.txtinputProperty().set("1");
        viewModel.push();
        viewModel.push();
        assertEquals(1, (int) viewModel.getStack().top());
        viewModel.pop();
        assertEquals(1, (int) viewModel.getStack().top());
    }

    @Test
    public void canPop() {
        viewModel.txtinputProperty().set("1");
        viewModel.push();
        assertEquals(1, (int) viewModel.getStack().pop());
    }

    @Test
    public void canPopFromEmptyStack() {
        viewModel.pop();
        assertEquals("Stack is empty! Cannot pop!", viewModel.txtlogProperty().get());
    }

    @Test
    public void canTop() {
        viewModel.txtinputProperty().set("1");
        viewModel.push();
        assertEquals(1, (int) viewModel.getStack().top());
    }

    @Test
    public void canTopFromEmptyStack() {
        viewModel.top();
        assertEquals("Stack is empty! Cannot top!", viewModel.txtlogProperty().get());
    }

    @Test
    public void testIsEmpty() {
        viewModel.isEmpty();
        assertEquals("Stack is empty!", viewModel.txtmsgProperty().get());
    }

    @Test
    public void testIsEmptyAfterPushThenPop() {
        viewModel.push();
        viewModel.pop();
        viewModel.isEmpty();
        assertEquals("Stack is empty!", viewModel.txtmsgProperty().get());
    }

    @Test
    public void testIsNotEmpty() {
        viewModel.isNotEmpty();
        assertEquals("Stack is not empty!", viewModel.txtmsgProperty().get());
    }

    @Test
    public void testPrint() {
        viewModel.print();
        assertTrue(viewModel.getStack().isEmpty());
        viewModel.getStack().push(number);
        assertEquals(number, viewModel.getStack().top());
    }
}
