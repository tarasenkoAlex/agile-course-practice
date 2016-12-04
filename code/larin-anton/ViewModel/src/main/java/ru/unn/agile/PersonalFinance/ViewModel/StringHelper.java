package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

final class StringHelper {
    static boolean isNullOrEmpty(final String value) {
        return value == null ||
               value.trim().isEmpty();
    }

    static boolean isEqual(final String lhs, final String rhs) {
        if (lhs != null && rhs != null) {
            return lhs.equals(rhs);
        }
        return lhs == null && rhs == null;
    }

    static boolean areEqualTrimmed(final String lhs, final String rhs) {
        if (lhs != null && rhs != null) {
            return lhs.trim().equals(rhs.trim());
        }
        return lhs == null && rhs == null;
    }

    static BooleanBinding isNullOrEmpty(final StringProperty property) {
        return Bindings.createBooleanBinding(() ->
                StringHelper.isNullOrEmpty(property.get()), property);
    }

    private StringHelper() {
    }
}
