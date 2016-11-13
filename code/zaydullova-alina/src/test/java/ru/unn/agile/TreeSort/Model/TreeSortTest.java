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
        assertNotNull("Output collection is null", outputCollection);
        assertEquals("Output collection should be empty", 0, outputCollection.size());
    }

    @Test
    public void testSortCollectionWithSingleElement() {
        TreeSort sort = new TreeSort();
        Collection<Integer> inputCollection = new ArrayList<Integer>();
        Integer testValue0 = 1;
        inputCollection.add(testValue0);

        Collection<Integer> outputCollection = sort.sort(inputCollection);

        assertNotNull("Output collection is null", outputCollection);
        assertEquals("Output collection should have size equals to input collection",
                inputCollection.size(), outputCollection.size());
        assertEquals("Output collection should have the same element as in input collection",
                testValue0, outputCollection.iterator().next());
    }

    @Test
    public void testSortCollectionWithEqualsElements() {
        TreeSort sort = new TreeSort();
        Collection<Integer> inputCollection = new ArrayList<Integer>();
        Integer testValue0 = 1;
        inputCollection.add(testValue0);
        inputCollection.add(testValue0);
        inputCollection.add(testValue0);

        Collection<Integer> outputCollection = sort.sort(inputCollection);
        assertNotNull("Output collection is null", outputCollection);
        assertEquals("Output collection should have size equals to input collection",
                inputCollection.size(), outputCollection.size());
        for (Integer val : outputCollection) {
            assertEquals("Output collection should not change values", testValue0, val);
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

        assertNotNull("Output collection is null", outputCollection);
        assertEquals("Output collection should have size equals to input collection",
                inputCollection.size(), outputCollection.size());

        Iterator<Integer> it = outputCollection.iterator();
        assertEquals("Element 0 must be equal to (0)", Integer.valueOf(0), it.next());
        assertEquals("Element 1 must be equal to (10)", Integer.valueOf(10), it.next());
        assertEquals("Element 2 must be equal to (20)", Integer.valueOf(20), it.next());
        assertEquals("Element 3 must be equal to (30)", Integer.valueOf(30), it.next());
    }
}
