package de.briemla.utils.matcher;

import java.io.File;

import org.hamcrest.Matcher;

public class FileStorageMatcher {

    /**
     * Tests whether a given {@link File} is empty or not.
     *
     * @return <code>true</code> if {@link File} is empty
     */
    public static Matcher<File> isEmpty() {
        return new IsEmpty();
    }

    /**
     * Tests whether a given {@link File} contains only the given line.
     *
     * @param line
     *            which should be contained in the {@link File}
     * @return <code>true</code> if the {@link File} only contains this line, <code>false</code>
     *         otherwise
     */
    public static Matcher<File> containsSingleLine(String line) {
        return new ContainsSingleLine(line);
    }

    /**
     * Tests whether a given {@link File} contains all given lines.
     *
     * @param lines
     *            which should be contained in the {@link File}
     * @return <code>true</code> if the {@link File} contains all given lines, <code>false</code>
     *         otherwise
     */
    public static Matcher<File> contains(String... lines) {
        return new Contains(lines);
    }

    /**
     * Tests whether a {@link File} exists or not.
     *
     * @see File#exists()
     *
     * @return <code>true</code> if the {@link File} exists otherwise <code>false</code>
     */
    public static Matcher<File> exists() {
        return new Exists();
    }

}
