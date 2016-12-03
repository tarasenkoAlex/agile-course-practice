package ru.unn.agile.personalfinance.view.utils;

import javafx.util.StringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.CategoryViewModel;

public final class Converters {
    private static StringConverter<AccountViewModel> accountToStringConverter;
    private static StringConverter<CategoryViewModel> categoryToStringConverter;

    public static StringConverter<AccountViewModel> getAccountToStringConverter() {
        if (accountToStringConverter == null) {
            accountToStringConverter = new StringConverter<AccountViewModel>() {
                @Override
                public String toString(AccountViewModel object) {
                    return object.getName();
                }

                @Override
                public AccountViewModel fromString(String string) {
                    // NOTE: Need only one side conversion, from object to string
                    return null;
                }
            };
        }
        return accountToStringConverter;
    }

    public static StringConverter<CategoryViewModel> getCategoryToStringConverter() {
        if (categoryToStringConverter == null) {
            categoryToStringConverter = new StringConverter<CategoryViewModel>() {
                @Override
                public String toString(CategoryViewModel object) {
                    return object.getName();
                }

                @Override
                public CategoryViewModel fromString(String string) {
                    // NOTE: Need only one side conversion, from object to string
                    return null;
                }
            };
        }
        return categoryToStringConverter;
    }

    private Converters() {
    }
}
