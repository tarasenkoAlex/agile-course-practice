package ru.unn.agile.TreeSort.Model;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import java.util.Collection;
import java.util.Iterator;

public class TreeTest {
    @Test
    public void testTreeCreate() {
        Tree tree = new Tree(10);
        assertEquals(Integer.valueOf(10), tree.getKey());
    }

    @Test
    public void testInsertLess() {
        Tree tree = new Tree(10);
        assertNull(tree.getLeft());

        tree.insert(5);
        assertNotNull(tree.getLeft());
        assertEquals(Integer.valueOf(5), tree.getLeft().getKey());
    }

    @Test
    public void testInsertGreat() {
        Tree tree = new Tree(20);
        assertNull(tree.getRight());

        tree.insert(30);
        assertNotNull(tree.getRight());
        assertEquals(Integer.valueOf(30), tree.getRight().getKey());
    }

    @Test
    public void testInsertLessAndGreat() {
        Tree tree = new Tree(20);
        assertEquals(Integer.valueOf(20), tree.getKey());

        assertNull(tree.getLeft());
        assertNull(tree.getRight());

        tree.insert(10);
        tree.insert(30);

        assertNotNull(tree.getLeft());
        assertNotNull(tree.getRight());

        assertEquals(Integer.valueOf(10), tree.getLeft().getKey());
        assertEquals(Integer.valueOf(30), tree.getRight().getKey());
    }

    @Test
    public void testExtractCollectionWithSingleElement() {
        Tree tree = new Tree(10);

        Collection<Integer> extractedValues = tree.extractValues();
        assertNotNull(extractedValues);
        assertEquals(1, extractedValues.size());
        assertEquals(Integer.valueOf(10), extractedValues.iterator().next());
    }

    @Test
    public void testExtractCollectionWithEqualsElements() {
        Tree tree = new Tree(10);
        tree.insert(10);
        tree.insert(10);
        tree.insert(10);

        Collection<Integer> extractedValues = tree.extractValues();
        assertNotNull(extractedValues);
        assertEquals(4, extractedValues.size());

        for (Integer val : extractedValues) {
            assertEquals(Integer.valueOf(10), val);
        }
    }

    @Test
    public void testExtractCollectionWithMultipleElements() {
        Tree tree = new Tree(20);
        tree.insert(30);
        tree.insert(10);

        Collection<Integer> extractedValues = tree.extractValues();
        assertNotNull(extractedValues);
        assertEquals(3, extractedValues.size());
        Iterator<Integer> it = extractedValues.iterator();
        assertEquals(Integer.valueOf(10), it.next());
        assertEquals(Integer.valueOf(20), it.next());
        assertEquals(Integer.valueOf(30), it.next());
    }
}
