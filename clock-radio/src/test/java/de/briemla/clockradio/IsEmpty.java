package de.briemla.clockradio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

final class IsEmpty extends TypeSafeMatcher<File> {
    @Override
    public void describeTo(Description description) {
        description.appendText("file is empty");
    }

    @Override
    protected boolean matchesSafely(File item) {
        List<String> content = readContentOf(item);
        return content.isEmpty();
    }

    private List<String> readContentOf(File item) {
        try {
            return Files.readAllLines(item.toPath());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void describeMismatchSafely(File item, Description mismatchDescription) {
        mismatchDescription.appendText("file contains: " + readContentOf(item));
    }
}