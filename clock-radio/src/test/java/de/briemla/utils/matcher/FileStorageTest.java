package de.briemla.utils.matcher;

import static de.briemla.utils.matcher.FileStorageMatcher.containsSingleLine;
import static de.briemla.utils.matcher.FileStorageMatcher.isEmpty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.FileStorage;
import de.briemla.clockradio.SaveTrigger;
import de.briemla.clockradio.TimeProvider;
import de.briemla.clockradio.controls.LocalFolder;
import de.briemla.clockradio.player.PlayerFactory;

public class FileStorageTest {

    private static final LocalTime nextMinute = LocalTime.of(12, 34);
    private static final String separator = ";";

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    private File storageFile;
    private FileStorage storage;
    private PlayerFactory notUsedFactory;
    private TimeProvider timeProvider;
    private SaveTrigger notUsedStorage;

    @Before
    public void initialise() throws IOException {
        storageFile = folder.newFile("alarm.storage");
        storage = new FileStorage(storageFile);
        timeProvider = mock(TimeProvider.class);
        when(timeProvider.nextMinute()).thenReturn(nextMinute);
    }

    @Test
    public void saveEmptyListOfAlarmsToFile() throws Exception {
        storage.save(Collections.emptyList());

        assertThat(storageFile, isEmpty());
    }

    @Test
    public void saveOneAlarmToFile() throws Exception {
        Alarm ofAlarm = new Alarm(notUsedFactory, timeProvider, notUsedStorage);
        List<Alarm> oneAlarm = Collections.singletonList(ofAlarm);
        storage.save(oneAlarm);

        String wakeUpTime = "12:34";
        String media = LocalFolder.defaultFolder().toString();
        String activeDays = "[MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]";
        assertThat(storageFile,
            containsSingleLine(wakeUpTime + separator + media + separator + activeDays));
    }

}
