package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Assert;

import java.util.List;

final class AssertHelper {
    private AssertHelper() {
    }

    public static void assertContains(final List collection, final Object object) {
        Assert.assertTrue(collection.contains(object));
    }
}
