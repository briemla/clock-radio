package de.briemla.clockradio;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileStorage implements AlarmStorage {

    private static final String backupExtension = ".backup";
    private final File storagepath;
    private final AlarmFactory alarmFactory;
    private final Path backupFile;

    public FileStorage(File storagepath, AlarmFactory alarmFactory) {
        super();
        this.storagepath = storagepath;
        this.alarmFactory = alarmFactory;
        backupFile = backFileFrom(storagepath);
    }

    private static Path backFileFrom(File storagepath) {
        File folder = storagepath.getParentFile();
        String storageFileName = storagepath.getName();
        String backupFileName = storageFileName + backupExtension;
        return new File(folder, backupFileName).toPath();
    }

    @Override
    public void save(List<Alarm> alarms) {
        if (backupFile()) {
            return;
        }
        try (PrintStream file = new PrintStream(storagepath)) {
            alarms.stream().forEach(alarm -> alarm.storeTo(file));
        } catch (IOException exception) {
            throw new RuntimeException("Could not save alarms", exception);
        }
    }

    private boolean backupFile() {
        if (!storagepath.exists()) {
            return false;
        }
        try {
            Files.copy(storagepath.toPath(), backupFile);
            return false;
        } catch (IOException exception) {
            return true;
        }
    }

    @Override
    public List<Alarm> load() {
        try {
            return Files.readAllLines(storagepath.toPath())
                        .stream()
                        .map(alarmFactory::fromStorage)
                        .collect(toList());
        } catch (IOException exception) {
            throw new RuntimeException("Could not load alarms", exception);
        }
    }

}
