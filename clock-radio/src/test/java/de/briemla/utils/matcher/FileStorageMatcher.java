package de.briemla.utils.matcher;

import java.io.File;

import org.hamcrest.Matcher;

public class FileStorageMatcher {

    /**
     * Tests whether a given file is empty or not.
     *
     * @return true if file is empty
     */
    public static Matcher<File> isEmpty() {
        return new IsEmpty();
    }

}
