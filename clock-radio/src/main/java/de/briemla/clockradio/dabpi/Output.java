package de.briemla.clockradio.dabpi;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Output {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private final ArrayList<String> standard;
    private final ArrayList<String> error;

    public Output() {
        standard = new ArrayList<>();
        error = new ArrayList<>();
    }

    public void addStandard(String line) {
        standard.add(line);
    }

    public void addError(String line) {
        if (line == null) {
            return;
        }
        error.add(line);
    }

    /**
     * Checks if there has something be written on the error output. Empty lines
     * will be ignored.
     *
     * @return
     */
    public boolean isErrorEmpty() {
        return error.stream().filter(text -> !text.isEmpty()).count() == 0;
    }

    /**
     * Returns the error output of the underlying dabpi_ctl application
     *
     * @return
     */
    public String concatErrorMessage() {
        return error.stream().collect(Collectors.joining(LINE_SEPARATOR)).toString();
    }

    public Stream<String> standardAsStream() {
        return standard.stream();
    }

}
