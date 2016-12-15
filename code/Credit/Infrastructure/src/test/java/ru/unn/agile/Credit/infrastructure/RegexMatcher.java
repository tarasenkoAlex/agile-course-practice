package ru.unn.agile.Credit.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regularExpression;

    public RegexMatcher(final String regularExpression) {
        this.regularExpression = regularExpression;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regularExpression);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regularExpression = ");
        description.appendText(regularExpression);
    }

    public static Matcher<? super String> matchesPattern(final String regularExpression) {
        RegexMatcher matcher = new RegexMatcher(regularExpression);
        //NOTE: this ugly cast is needed to workaround 'unchecked' Java warning
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcher;
        return castedMatcher;
    }
}
