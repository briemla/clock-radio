package de.briemla.utils.matcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileUtil {

    public static List<String> contentOf(File item) {
        try {
            return Files.readAllLines(item.toPath());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
