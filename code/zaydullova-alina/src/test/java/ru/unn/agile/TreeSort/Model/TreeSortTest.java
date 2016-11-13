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
        assertEquals("Output collection should have size equals to input collection", inputCollection.size(), outputCollection.size());
        assertEquals("Output collection should have the same element as in input collection", testValue0, outputCollection.iterator().next());
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
        assertEquals("Output collection should have size equals to input collection", inputCollection.size(), outputCollection.size());
        for (Integer val : outputCollection) {
            assertEquals("Output collection should not change values", testValue0, val);
        }
    }

    @Test
    public void testSortCollectionWithMultipleElements() {
        TreeSort sort = new TreeSort();
        Collection<Integer> inputCollection = new ArrayList<Integer>();
        Integer testValue0 = 0;
        Integer testValue1 = 10;
        Integer testValue2 = 20;
        Integer testValue3 = 30;
        inputCollection.add(testValue2);
        inputCollection.add(testValue3);
        inputCollection.add(testValue1);
        inputCollection.add(testValue0);

        Collection<Integer> outputCollection = sort.sort(inputCollection);

        assertNotNull("Output collection is null", outputCollection);
        assertEquals("Output collection should have size equals to input collection", inputCollection.size(), outputCollection.size());

        Iterator<Integer> it = outputCollection.iterator();
        assertEquals("Element 0 must be equal to testValue0 (0)", testValue0, it.next());
        assertEquals("Element 1 must be equal to testValue1 (10)", testValue1, it.next());
        assertEquals("Element 2 must be equal to testValue2 (20)", testValue2, it.next());
        assertEquals("Element 3 must be equal to testValue3 (30)", testValue3, it.next());
    }
}
