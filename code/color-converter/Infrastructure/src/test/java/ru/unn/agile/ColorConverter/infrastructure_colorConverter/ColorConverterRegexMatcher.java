package ru.unn.agile.ColorConverter.infrastructure_colorConverter;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ColorConverterRegexMatcher extends BaseMatcher {
    private final String colorRegex;

    public ColorConverterRegexMatcher(final String regex) {
        this.colorRegex = regex;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(colorRegex);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(colorRegex);
    }

    public static Matcher<? super String> matchesPattern(final String regex) {
        ColorConverterRegexMatcher colorRegexMatcher = new ColorConverterRegexMatcher(regex);
        //NOTE: this ugly cast is needed to workaround 'unchecked' Java warning
        @SuppressWarnings(value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)
                colorRegexMatcher;
        return castedMatcher;
    }
}
