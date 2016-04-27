package de.briemla.utils.matcher;

import static de.briemla.utils.matcher.FileStorageMatcher.isEmpty;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import de.briemla.clockradio.FileStorage;

public class FileStorageTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();
    private File storageFile;
    private FileStorage storage;

    @Before
    public void initialise() throws IOException {
        storageFile = folder.newFile("alarm.storage");
        storage = new FileStorage(storageFile);
    }

    @Test
    public void saveEmptyListOfAlarmsToFile() throws Exception {
        storage.save(Collections.emptyList());

        assertThat(storageFile, isEmpty());
    }

}
