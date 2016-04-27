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

    /**
     * Tests whether a given file contains only the given line
     *
     * @param line
     *            which should be contained in the file
     * @return true if the file only contains this line, false otherwise
     */
    public static Matcher<File> containsSingleLine(String line) {
        return new ContainsSingleLine(line);
    }

    /**
     * Tests whether a given file contains all given lines
     *
     * @param lines
     *            which should be contained in the file
     * @return true if the file contains all given lines, false otherwise
     */
    public static Matcher<File> contains(String... lines) {
        return new Contains(lines);
    }

}
