package de.briemla.clockradio;

import java.io.File;
import java.util.List;

public class FileStorage implements AlarmStorage {

    private final File storagepath;

    public FileStorage(File storagepath) {
        super();
        this.storagepath = storagepath;
    }

    @Override
    public void save(List<Alarm> alarms) {
    }

}
