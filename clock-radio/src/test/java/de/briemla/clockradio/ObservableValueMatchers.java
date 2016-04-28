package de.briemla.clockradio;

import javafx.beans.value.ObservableValue;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ObservableValueMatchers {

    public static <T> Matcher<ObservableValue<T>> hasValue(Matcher<T> matcher) {
        return new TypeSafeMatcher<ObservableValue<T>>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("has value ");
            }

            @Override
            protected boolean matchesSafely(ObservableValue<T> item) {
                return matcher.matches(item.getValue());
            }
        };
    }
}
