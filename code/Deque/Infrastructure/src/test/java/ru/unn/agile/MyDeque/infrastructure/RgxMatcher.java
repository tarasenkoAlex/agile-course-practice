package ru.unn.agile.MyDeque.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RgxMatcher extends BaseMatcher {
    private final String regex;

    public RgxMatcher(final String regex) {
        this.regex = regex;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regex);
    }

    public void describeTo(final Description descr) {
        descr.appendText("matches regex = ");
        descr.appendText(regex);
    }

    public static Matcher<? super String> matchPattern(final String regex) {
        RgxMatcher matcher = new RgxMatcher(regex);
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcher;
        return castedMatcher;
    }
}
