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
        assertEquals("Key in Tree must be equals to original value", testKey0, tree.getKey());
    }

    @Test
    public void testInsertLess() {
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
    public void testInsertGreat() {
        Integer testKey0 = 10;
        Integer testKey1 = 15;

        Tree tree = new Tree(testKey0);
        assertEquals("Key in Tree must be equals to original value", testKey0, tree.getKey());

        assertNull("Right tree must be null", tree.getRight());

        tree.insert(testKey1);
        assertNotNull("Right tree must be not null", tree.getRight());
        assertEquals("Key in right tree must be equals to original value testKey1", testKey1, tree.getRight().getKey());
    }

    @Test
    public void testInsertLessAndGreat() {
        Integer testKey0 = 10;
        Integer testKeyL = 5;
        Integer testKeyG = 15;


        Tree tree = new Tree(testKey0);
        assertEquals("Key in Tree must be equals to original value", testKey0, tree.getKey());

        assertNull("Left tree must be null", tree.getLeft());
        assertNull("Right tree must be null", tree.getRight());

        tree.insert(testKeyL);
        tree.insert(testKeyG);

        assertNotNull("Left tree must be not null", tree.getLeft());
        assertNotNull("Right tree must be not null", tree.getRight());

        assertEquals("Key in left tree must be equals to original value testKeyL", testKeyL, tree.getLeft().getKey());
        assertEquals("Key in right tree must be equals to original value testKeyG", testKeyG, tree.getRight().getKey());
    }

    @Test
    public void testExtractCollectionWithSingleElement() {
        Integer testKey0 = 10;

        Tree tree = new Tree(testKey0);
        assertEquals("Key in Tree must be equals to original value", testKey0, tree.getKey());

        assertNull("Left tree must be null", tree.getLeft());
        assertNull("Right tree must be null", tree.getRight());

        Collection<Integer> extractedValues = tree.extractValues();
        assertNotNull("Extracted collection must be not null", extractedValues);
        assertEquals("Extracted collection must contain single element", 1, extractedValues.size());
        assertEquals("Extracted collection must contain original value testKey0", testKey0, extractedValues.iterator().next());
    }

    @Test
    public void testExtractCollectionWithEqualsElements() {
        Integer testKey0 = 10;

        Tree tree = new Tree(testKey0);
        assertEquals("Key in Tree must be equals to original value", testKey0, tree.getKey());
        assertNull("Left tree must be null", tree.getLeft());
        assertNull("Right tree must be null", tree.getRight());

        tree.insert(testKey0);
        tree.insert(testKey0);
        tree.insert(testKey0);

        Collection<Integer> extractedValues = tree.extractValues();
        assertNotNull("Extracted collection must be not null", extractedValues);
        assertEquals("Extracted collection must contain 4 elements", 4, extractedValues.size());

        for (Integer val : extractedValues) {
            assertEquals("Extracted collection must contain only original value testKey0", testKey0, val);
        }
    }

    @Test
    public void testExtractCollectionWithMultipleElements() {
        Integer testKey0 = 10;
        Integer testKey1 = 20;
        Integer testKey2 = 30;
        Integer testKey3 = 40;

        Tree tree = new Tree(testKey2);
        assertEquals("Key in Tree must be equals to original value testKey2", testKey2, tree.getKey());
        assertNull("Left tree must be null", tree.getLeft());
        assertNull("Right tree must be null", tree.getRight());

        tree.insert(testKey1);
        tree.insert(testKey3);
        tree.insert(testKey0);

        Collection<Integer> extractedValues = tree.extractValues();
        assertNotNull("Extracted collection must be not null", extractedValues);
        assertEquals("Extracted collection must contain 4 elements", 4, extractedValues.size());

        Iterator<Integer> it = extractedValues.iterator();
        assertEquals("Element 0 must be equal to testKey0 (10)", testKey0, it.next());
        assertEquals("Element 1 must be equal to testKey1 (20)", testKey1, it.next());
        assertEquals("Element 2 must be equal to testKey2 (30)", testKey2, it.next());
        assertEquals("Element 3 must be equal to testKey3 (40)", testKey3, it.next());
    }
}
