package ru.unn.agile.todoapp.model;

import java.util.List;
import java.util.StringJoiner;

import org.junit.Assert;

class AssertHelper {
    private AssertHelper() { }

    public static void assertContains(final List collection, final Object object) {
        Assert.assertTrue(collection.contains(object));
    }

    public static void assertNotContains(final List collection, final Object object) {
        Assert.assertFalse(collection.contains(object));
    }

    public static void assertListEquals(final List lhs, final List rhs) {
        int lhsSize = lhs.size();
        int rhsSize = rhs.size();
        if (lhsSize != rhsSize) {
            String message = "The lists have different sizes: %d and %d";
            Assert.fail(String.format(message, lhsSize, rhsSize));
        }

        for (int i = 0; i < lhs.size(); i++) {
            Object lhsItem = lhs.get(i);
            Object rhsItem = rhs.get(i);
            if (!lhsItem.equals(rhsItem)) {
                String message = "Elements at index %d are not equal: '%s' != '%s'";
                Assert.fail(String.format(message, i, lhsItem, rhsItem));
            }
        }
    }
}
