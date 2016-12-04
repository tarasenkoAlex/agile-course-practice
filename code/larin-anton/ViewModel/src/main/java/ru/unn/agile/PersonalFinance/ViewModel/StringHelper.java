package ru.unn.agile.PersonalFinance.ViewModel;

final class StringHelper {
    static boolean isNullOrEmpty(final String value) {
        return value == null ||
               value.trim().isEmpty();
    }

    private StringHelper() {
    }
}
