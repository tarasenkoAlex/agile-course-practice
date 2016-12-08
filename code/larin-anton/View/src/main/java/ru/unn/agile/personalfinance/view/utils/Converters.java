package ru.unn.agile.personalfinance.view.utils;

import javafx.util.StringConverter;
import ru.unn.agile.PersonalFinance.ViewModel.AccountViewModel;
import ru.unn.agile.PersonalFinance.ViewModel.CategoryViewModel;

public final class Converters {
    private static final Lazy<AccountViewModelNameExtractor> ACCOUNT_TO_STRING_CONVERTER =
            new Lazy<>(() -> new AccountViewModelNameExtractor());

    private static final Lazy<CategoryViewModelNameExtractor> CATEGORY_TO_STRING_CONVERTER =
            new Lazy<>(() -> new CategoryViewModelNameExtractor());

    public static StringConverter<AccountViewModel> getAccountToStringConverter() {
        return ACCOUNT_TO_STRING_CONVERTER.get();
    }

    public static StringConverter<CategoryViewModel> getCategoryToStringConverter() {
        return CATEGORY_TO_STRING_CONVERTER.get();
    }

    private Converters() {
    }
}
