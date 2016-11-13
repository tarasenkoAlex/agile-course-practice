package ru.unn.agile.TreeSort.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void testTreeCreate() {
        Integer testKey0 = 10;

        Tree tree = new Tree(testKey0);
        assertEquals("Key in Tree must be equals to original value", testKey0, tree.getKey());
    }

    @Test
    public void testLeftTree() {
        Integer testKey0 = 10;
        Integer testKey1 = 5;

        Tree tree = new Tree(testKey0);
        assertEquals("Key in Tree must be equals to original value", testKey0, tree.getKey());

        assertNull("Left tree must be null", tree.getLeft());

        tree.insert(testKey1);
        assertNotNull("Left tree must be not null", tree.getLeft());
        assertEquals("Key in left tree must be equals to original value testKey1", testKey1, tree.getLeft().getKey());
    }

    @Test
    public void testRightTree() {
        Integer testKey0 = 10;
        Integer testKey1 = 15;

        Tree tree = new Tree(testKey0);
        assertEquals("Key in Tree must be equals to original value", testKey0, tree.getKey());

        assertNull("Left tree must be null", tree.getRight());

        tree.insert(testKey1);
        assertNotNull("Left tree must be not null", tree.getRight());
        assertEquals("Key in right tree must be equals to original value testKey1", testKey1, tree.getRight().getKey());
    }
}
