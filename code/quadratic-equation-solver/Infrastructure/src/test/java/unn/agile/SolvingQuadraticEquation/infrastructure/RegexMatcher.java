package unn.agile.SolvingQuadraticEquation.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regex;

    public RegexMatcher(final String regex) {
        this.regex = regex;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regex);
    }

    public void describeTo(final Description descript) {
        descript.appendText("matches regex = ");
        descript.appendText(regex);
    }

    public static Matcher<? super String> matchesPattern(final String regex) {
        RegexMatcher regexMatcher = new RegexMatcher(regex);
        //NOTE: this ugly cast is needed to workaround 'unchecked' Java warning
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   regexMatcher;
        return castedMatcher;
    }
}
