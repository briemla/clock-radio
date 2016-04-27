package de.briemla.utils.matcher;

import static de.briemla.utils.matcher.FileUtil.contentOf;

import java.io.File;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

final class ContainsSingleLine extends TypeSafeMatcher<File> {
    private static final int singleLine = 0;
    private final String line;

    public ContainsSingleLine(String line) {
        super();
        this.line = line;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("contains only: " + line);
    }

    @Override
    protected boolean matchesSafely(File item) {
        List<String> content = contentOf(item);
        return 1 == content.size() && line.equals(content.get(singleLine));
    }

    @Override
    protected void describeMismatchSafely(File item, Description mismatchDescription) {
        mismatchDescription.appendText("contains: " + contentOf(item));
    }
}