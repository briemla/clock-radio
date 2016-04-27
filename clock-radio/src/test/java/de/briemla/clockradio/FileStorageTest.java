package de.briemla.clockradio;

import static de.briemla.clockradio.FileStorageMatcher.isEmpty;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileStorageTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();
    private File storageFile;

    @Before
    public void initialise() throws IOException {
        storageFile = folder.newFile("alarm.storage");
    }

    @Test
    public void saveEmptyListOfAlarmsToFile() throws Exception {
        FileStorage storage = new FileStorage(storageFile);

        storage.save(Collections.emptyList());

        assertThat(storageFile, isEmpty());
    }

}
