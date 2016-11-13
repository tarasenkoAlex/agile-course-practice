package ru.unn.agile.TreeSort.Model;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import java.util.Collection;
import java.util.Iterator;

public class TreeTest {
    @Test
    public void testTreeCreate() {
        Integer testKey0 = 10;

        Tree tree = new Tree(testKey0);
        assertEquals("Key in Tree must be equals to original value",
                testKey0, tree.getKey());
    }

    @Test
    public void testInsertLess() {
        Integer testKey0 = 10;
        Integer testKey1 = 5;

        Tree tree = new Tree(testKey0);
        assertEquals("Key in Tree must be equals to original value",
                testKey0, tree.getKey());

        assertNull("Left tree must be null", tree.getLeft());

        tree.insert(testKey1);
        assertNotNull("Left tree must be not null", tree.getLeft());
        assertEquals("Key in left tree must be equals to original value testKey1",
                testKey1, tree.getLeft().getKey());
    }

    @Test
    public void testInsertGreat() {
        Integer testKey0 = 10;
        Integer testKey1 = 15;

        Tree tree = new Tree(testKey0);
        assertEquals("Key in Tree must be equals to original value",
                testKey0, tree.getKey());

        assertNull("Right tree must be null", tree.getRight());

        tree.insert(testKey1);
        assertNotNull("Right tree must be not null", tree.getRight());
        assertEquals("Key in right tree must be equals to original value testKey1",
                testKey1, tree.getRight().getKey());
    }

    @Test
    public void testInsertLessAndGreat() {
        Tree tree = new Tree(Integer.valueOf(20));
        assertEquals("Key in Tree must be equals to original value",
                Integer.valueOf(20), tree.getKey());

        assertNull("Left tree must be null", tree.getLeft());
        assertNull("Right tree must be null", tree.getRight());

        tree.insert(Integer.valueOf(10));
        tree.insert(Integer.valueOf(30));

        assertNotNull("Left tree must be not null", tree.getLeft());
        assertNotNull("Right tree must be not null", tree.getRight());

        assertEquals("Key in left tree must be equals to original value testKeyL",
                Integer.valueOf(10), tree.getLeft().getKey());
        assertEquals("Key in right tree must be equals to original value testKeyG",
                Integer.valueOf(30), tree.getRight().getKey());
    }

    @Test
    public void testExtractCollectionWithSingleElement() {
        Tree tree = new Tree(Integer.valueOf(10));
        assertEquals("Key in Tree must be equals to original value",
                Integer.valueOf(10), tree.getKey());

        assertNull("Left tree must be null", tree.getLeft());
        assertNull("Right tree must be null", tree.getRight());

        Collection<Integer> extractedValues = tree.extractValues();
        assertNotNull("Extracted collection must be not null", extractedValues);
        assertEquals("Extracted collection must contain single element",
                1, extractedValues.size());
        assertEquals("Extracted collection must contain original value testKey0",
                Integer.valueOf(10), extractedValues.iterator().next());
    }

    @Test
    public void testExtractCollectionWithEqualsElements() {
        Tree tree = new Tree(Integer.valueOf(10));
        assertEquals("Key in Tree must be equals to original value",
                Integer.valueOf(10), tree.getKey());
        assertNull("Left tree must be null", tree.getLeft());
        assertNull("Right tree must be null", tree.getRight());
        tree.insert(Integer.valueOf(10));
        tree.insert(Integer.valueOf(10));
        tree.insert(Integer.valueOf(10));

        Collection<Integer> extractedValues = tree.extractValues();
        assertNotNull("Extracted collection must be not null", extractedValues);
        assertEquals("Extracted collection must contain 4 elements",
                4, extractedValues.size());

        for (Integer val : extractedValues) {
            assertEquals("Extracted collection must contain only original value testKey0",
                    Integer.valueOf(10), val);
        }
    }

    @Test
    public void testExtractCollectionWithMultipleElements() {
        Tree tree = new Tree(20);
        assertEquals("Key in Tree must be equals to 30",
                Integer.valueOf(20), tree.getKey());
        tree.insert(30);
        tree.insert(10);

        Collection<Integer> extractedValues = tree.extractValues();
        assertNotNull("Extracted collection must be not null", extractedValues);
        assertEquals("Extracted collection must contain 4 elements",
                3, extractedValues.size());
        Iterator<Integer> it = extractedValues.iterator();
        assertEquals(Integer.valueOf(10), it.next());
        assertEquals(Integer.valueOf(20), it.next());
        assertEquals(Integer.valueOf(30), it.next());
    }
}
