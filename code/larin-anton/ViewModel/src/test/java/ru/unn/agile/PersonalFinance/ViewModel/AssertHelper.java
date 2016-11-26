package ru.unn.agile.PersonalFinance.ViewModel;

import java.util.List;
import org.junit.Assert;

final class AssertHelper {
    private AssertHelper() {
    }

    public static void assertContains(final List collection, final Object object) {
        Assert.assertTrue(collection.contains(object));
    }
}
