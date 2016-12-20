package ru.unn.agile.MultisystemCalculator.infrastucture;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regularExpression;

    public RegexMatcher(final String regularExpression) {
        this.regularExpression = regularExpression;
    }

    public boolean matches(final Object object) {
        return ((String) object).matches(regularExpression);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(regularExpression);
    }

    public static Matcher<? super String> matchesPattern(final String regularExpression) {
        RegexMatcher regexMatcher = new RegexMatcher(regularExpression);
        //NOTE: this ugly cast is needed to workaround 'unchecked' Java warning
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   regexMatcher;
        return castedMatcher;
    }
}
