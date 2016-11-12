package ru.unn.agile.todoapp.model;

import java.util.List;
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
        for (int i = 0; i < lhs.size(); i++) {
            Object lhsItem = lhs.get(i);
            Object rhsItem = rhs.get(i);
            if (!lhsItem.equals(rhsItem)) {
                Assert.fail();
            }
        }
    }
}
