package de.briemla.utils.matcher;

import static de.briemla.clockradio.ObservableValueMatchers.hasValue;
import static de.briemla.utils.matcher.FileStorageMatcher.contains;
import static de.briemla.utils.matcher.FileStorageMatcher.containsSingleLine;
import static de.briemla.utils.matcher.FileStorageMatcher.exists;
import static de.briemla.utils.matcher.FileStorageMatcher.isEmpty;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import de.briemla.clockradio.ActiveDays;
import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.AlarmFactory;
import de.briemla.clockradio.FileStorage;
import de.briemla.clockradio.Media;
import de.briemla.clockradio.RealAlarmFactory;
import de.briemla.clockradio.SaveTrigger;
import de.briemla.clockradio.TimeProvider;
import de.briemla.clockradio.WakeUpTime;
import de.briemla.clockradio.controls.LocalFolder;
import de.briemla.clockradio.player.PlayerFactory;

public class FileStorageTest {

    private static final String storageFileName = "alarm.storage";
    private static final String backupFileName = storageFileName + ".backup";
    private static final int singleAlarm = 0;
    private static final LocalTime nextMinute = LocalTime.of(12, 34);
    private static final String separator = ";";

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    private File storageFile;
    private FileStorage storage;
    private PlayerFactory notUsedFactory;
    private TimeProvider timeProvider;
    private SaveTrigger notUsedTrigger;
    private AlarmFactory alarmFactory;
    private File backupFile;

    @Before
    public void initialise() throws IOException {
        storageFile = new File(folder.getRoot(), storageFileName);
        backupFile = new File(folder.getRoot(), backupFileName);
        timeProvider = mock(TimeProvider.class);
        when(timeProvider.nextMinute()).thenReturn(nextMinute);
        alarmFactory = new RealAlarmFactory(notUsedFactory, timeProvider);
        storage = new FileStorage(storageFile, alarmFactory);
    }

    private Alarm alarm() {
        return new Alarm(notUsedFactory, timeProvider, notUsedTrigger);
    }

    @Test
    public void saveEmptyListOfAlarmsToFile() throws Exception {
        storage.save(Collections.emptyList());

        assertThat(storageFile, isEmpty());
    }

    @Test
    public void saveOneAlarmToFile() throws Exception {
        Alarm ofAlarm = alarm();
        List<Alarm> oneAlarm = Collections.singletonList(ofAlarm);
        storage.save(oneAlarm);

        String wakeUpTime = "12:34";
        String media = LocalFolder.defaultFolder().toString();
        String activeDays = "[MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]";
        String activated = "true";
        assertThat(storageFile, containsSingleLine(
            wakeUpTime + separator + media + separator + activeDays + separator + activated));
    }

    @Test
    public void saveSeveralAlarmsToFile() throws Exception {
        Alarm firstAlarm = alarm();
        Alarm secondAlarm = alarm();
        secondAlarm.activatedProperty().setValue(false);
        List<Alarm> alarms = Arrays.asList(firstAlarm, secondAlarm);

        storage.save(alarms);

        String wakeUpTime = "12:34";
        String media = LocalFolder.defaultFolder().toString();
        String activeDays = "[MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]";
        String activated = "true";
        String firstLine = wakeUpTime + separator + media + separator + activeDays + separator
                + activated;
        String deactivated = "false";
        String secondLine = wakeUpTime + separator + media + separator + activeDays + separator
                + deactivated;
        assertThat(storageFile, contains(firstLine, secondLine));
    }

    @Test
    public void restoreSavedAlarmsFromFile() throws Exception {
        Alarm alarmBeforeSave = alarm();
        WakeUpTime wakeUpTimeBeforeSave = new WakeUpTime(23, 56);
        alarmBeforeSave.wakeUpTimeProperty().set(wakeUpTimeBeforeSave);
        Media mediaBeforeSave = new LocalFolder(new File("/home/somewhere/").toPath());
        alarmBeforeSave.mediaProperty().set(mediaBeforeSave);
        ActiveDays activeDaysBeforeSave = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));
        alarmBeforeSave.activeDaysProperty().set(activeDaysBeforeSave);
        boolean activatedBeforeSave = false;
        alarmBeforeSave.activatedProperty().setValue(activatedBeforeSave);

        List<Alarm> alarms = Collections.singletonList(alarmBeforeSave);
        storage.save(alarms);

        List<Alarm> loaded = storage.load();

        Alarm alarmAfterLoad = loaded.get(singleAlarm);
        assertThat(alarmAfterLoad.wakeUpTimeProperty(), hasValue(equalTo(wakeUpTimeBeforeSave)));
        assertThat(alarmAfterLoad.mediaProperty(), hasValue(equalTo(mediaBeforeSave)));
        assertThat(alarmAfterLoad.activeDaysProperty(), hasValue(equalTo(activeDaysBeforeSave)));
        assertThat(alarmAfterLoad.activatedProperty(), hasValue(equalTo(activatedBeforeSave)));
    }

    @Test
    public void restoreNoAlarmsFromEmptyFile() throws Exception {
        storageFile.createNewFile();

        List<Alarm> storedAlarms = storage.load();

        assertThat(storedAlarms, is(empty()));
    }

    @Test
    public void writesBackupFileBeforeSavingChanges() throws Exception {
        List<Alarm> alarms = Collections.singletonList(alarm());

        storage.save(alarms);
        assertThat("first storage", backupFile, not(exists()));

        storage.save(alarms);
        assertThat("second storage", backupFile, exists());
    }

    // private Alarm failsOnWrite() {
    // return new Alarm(notUsedFactory, timeProvider, notUsedTrigger) {
    // @Override
    // public void storeTo(PrintStream output) {
    // throw new IOException("Fail storing of alarm");
    // }
    // };
    // }

}
