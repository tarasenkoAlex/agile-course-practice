package ru.unn.agile.todoapp.model;

import java.util.List;
import org.junit.Assert;

class AssertHelper {
    private AssertHelper() { }

    public static <T> void assertContains(final List<T> collection, final T object) {
        Assert.assertTrue(collection.contains(object));
    }
}
