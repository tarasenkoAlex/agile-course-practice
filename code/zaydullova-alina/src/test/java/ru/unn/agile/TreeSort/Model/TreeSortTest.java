package ru.unn.agile.TreeSort.Model;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TreeSortTest {

    @Test
    public void testSortEmptyCollection() {
        TreeSort sort = new TreeSort();
        Collection<Integer> inputCollection = new ArrayList<Integer>();
        Collection<Integer> outputCollection = sort.sort(inputCollection);
        assertNotNull(outputCollection);
        assertEquals(0, outputCollection.size());
    }

    @Test
    public void testSortCollectionWithSingleElement() {
        TreeSort sort = new TreeSort();
        Collection<Integer> inputCollection = new ArrayList<Integer>();
        inputCollection.add(1);

        Collection<Integer> outputCollection = sort.sort(inputCollection);

        assertNotNull(outputCollection);
        assertEquals(inputCollection.size(), outputCollection.size());
        assertEquals(Integer.valueOf(1), outputCollection.iterator().next());
    }

    @Test
    public void testSortCollectionWithEqualsElements() {
        TreeSort sort = new TreeSort();
        Collection<Integer> inputCollection = new ArrayList<Integer>();
        inputCollection.add(10);
        inputCollection.add(10);
        inputCollection.add(10);

        Collection<Integer> outputCollection = sort.sort(inputCollection);
        assertNotNull(outputCollection);
        assertEquals(inputCollection.size(), outputCollection.size());
        for (Integer val : outputCollection) {
            assertEquals(Integer.valueOf(10), val);
        }
    }

    @Test
    public void testSortCollectionWithMultipleElements() {
        TreeSort sort = new TreeSort();
        Collection<Integer> inputCollection = new ArrayList<Integer>();
        inputCollection.add(20);
        inputCollection.add(30);
        inputCollection.add(10);
        inputCollection.add(0);

        Collection<Integer> outputCollection = sort.sort(inputCollection);

        assertNotNull(outputCollection);
        assertEquals(inputCollection.size(), outputCollection.size());

        Iterator<Integer> it = outputCollection.iterator();
        assertEquals(Integer.valueOf(0), it.next());
        assertEquals(Integer.valueOf(10), it.next());
        assertEquals(Integer.valueOf(20), it.next());
        assertEquals(Integer.valueOf(30), it.next());
    }
}
