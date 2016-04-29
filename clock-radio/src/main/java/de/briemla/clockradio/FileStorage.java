package de.briemla.clockradio;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.List;

public class FileStorage implements AlarmStorage {

    private final File storagepath;
    private final AlarmFactory alarmFactory;

    public FileStorage(File storagepath, AlarmFactory alarmFactory) {
        super();
        this.storagepath = storagepath;
        this.alarmFactory = alarmFactory;
    }

    @Override
    public void save(List<Alarm> alarms) {
        try (PrintStream file = new PrintStream(storagepath)) {
            alarms.stream().forEach(alarm -> alarm.storeTo(file));
        } catch (IOException exception) {
            throw new RuntimeException("Could not save alarms", exception);
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
