package ru.unn.agile.MortgageCalculator.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regex;

    public RegexMatcher(final String regex) {
        this.regex = regex;
    }

    public boolean matches(final Object o) {
        return o.toString().matches(regex);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = " + regex);
    }

    public static Matcher<? super String> matchesTemplate(final String regex) {
        RegexMatcher matcher = new RegexMatcher(regex);

        @SuppressWarnings (value = "unchecked")

        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcher;
        return castedMatcher;
    }
}
