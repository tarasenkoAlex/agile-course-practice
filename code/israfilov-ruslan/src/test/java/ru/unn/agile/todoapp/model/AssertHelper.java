package ru.unn.agile.todoapp.model;

import java.util.List;
import org.junit.Assert;

class AssertHelper {
    private AssertHelper() { }

    public static <T> void assertContains(final List<T> collection, final T object) {
        Assert.assertTrue(collection.contains(object));
    }

    public static <T> void assertNotContains(final List<T> collection, final T object) {
        Assert.assertFalse(collection.contains(object));
    }

    public static <T> void assertListEquals(final List<T> lhs, final List<T> rhs) {
        for (int i = 0; i < lhs.size(); i++) {
            T lhsItem = lhs.get(i);
            T rhsItem = rhs.get(i);
            if (!lhsItem.equals(rhsItem)) {
                Assert.fail();
            }
        }
    }
}
