package de.briemla.clockradio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class FileStorage implements AlarmStorage {

    private final File storagepath;

    public FileStorage(File storagepath) {
        super();
        this.storagepath = storagepath;
    }

    @Override
    public void save(List<Alarm> alarms) {
        try (PrintStream file = new PrintStream(storagepath)) {
            alarms.stream().forEach(alarm -> alarm.storeTo(file));
        } catch (IOException exception) {
            throw new RuntimeException("Could not save alarms", exception);
        }
    }

}
