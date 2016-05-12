package de.briemla.clockradio;

import static de.briemla.clockradio.ObservableValueMatchers.hasValue;
import static de.briemla.utils.matcher.FileStorageMatcher.contains;
import static de.briemla.utils.matcher.FileStorageMatcher.containsSingleLine;
import static de.briemla.utils.matcher.FileStorageMatcher.exists;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import de.briemla.clockradio.controls.LocalFolder;
import de.briemla.clockradio.player.PlayerFactory;

public class FileStorageTest {

    private static final String storageFileName = "alarm.storage";
    private static final String backupFileName = storageFileName + ".backup";
    private static final int singleAlarm = 0;
    private static final LocalTime nextMinute = LocalTime.of(12, 34);
    private static final String separator = ";";
    private static final String closing = "closing";

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    private File storageFile;
    private FileStorage storage;
    private PlayerFactory notUsedFactory;
    private TimeProvider timeProvider;
    private SaveTrigger notUsedTrigger;
    private AlarmFactory alarmFactory;
    private File backupFile;
    private OutputFactory outputFactory;
    private ExceptionHandler exceptionHandler;

    @Before
    public void initialise() throws IOException {
        storageFile = new File(folder.getRoot(), storageFileName);
        backupFile = new File(folder.getRoot(), backupFileName);
        timeProvider = mock(TimeProvider.class);
        when(timeProvider.nextMinute()).thenReturn(nextMinute);
        alarmFactory = new RealAlarmFactory(notUsedFactory, timeProvider);
        outputFactory = mock(OutputFactory.class);
        exceptionHandler = mock(ExceptionHandler.class);
        storage = new FileStorage(storageFile, alarmFactory, outputFactory, exceptionHandler);
        initializeOutputFactory();
    }

    private void initializeOutputFactory() throws IOException {
        when(outputFactory.create(storageFile)).thenAnswer(
            invocation -> new FileWriter(storageFile));
    }

    private Alarm alarm() {
        return new Alarm(notUsedFactory, timeProvider, notUsedTrigger);
    }

    @After
    public void verifyExceptionHandler() {
        verifyNoMoreInteractions(exceptionHandler);
    }

    @Test
    public void saveEmptyListOfAlarmsToFile() throws Exception {
        storage.save(Collections.emptyList());

        assertThat(storageFile, containsSingleLine(closing));
        verify(outputFactory).create(storageFile);
    }

    @Test
    public void saveOneAlarmToFile() throws Exception {
        Alarm ofAlarm = alarm();
        List<Alarm> oneAlarm = Collections.singletonList(ofAlarm);

        storage.save(oneAlarm);

        assertThat(storageFile, contains(defaultStoredAlarm(), closing));
        verify(outputFactory).create(storageFile);
    }

    private String defaultStoredAlarm() {
        String wakeUpTime = "12:34";
        String media = LocalFolder.defaultFolder().toString();
        String activeDays = "[MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]";
        String activated = "true";
        String defaultStoredAlarm = wakeUpTime + separator + media + separator + activeDays
                + separator + activated;
        return defaultStoredAlarm;
    }

    @Test
    public void saveSeveralAlarmsToFile() throws Exception {
        Alarm firstAlarm = alarm();
        Alarm secondAlarm = alarm();
        secondAlarm.activatedProperty().setValue(false);
        List<Alarm> alarms = Arrays.asList(firstAlarm, secondAlarm);

        storage.save(alarms);

        String firstLine = defaultStoredAlarm();
        String wakeUpTime = "12:34";
        String media = LocalFolder.defaultFolder().toString();
        String activeDays = "[MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]";
        String deactivated = "false";
        String secondLine = wakeUpTime + separator + media + separator + activeDays + separator
                + deactivated;
        assertThat(storageFile, contains(firstLine, secondLine, closing));
        verify(outputFactory).create(storageFile);
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
        verify(outputFactory).create(storageFile);
    }

    @Test
    public void restoreNoAlarmsFromEmptyFile() throws Exception {
        storageFile.createNewFile();

        List<Alarm> storedAlarms = storage.load();

        assertThat(storedAlarms, is(empty()));
    }

    @Test
    public void restoreNoAlarmsWhenFileDoesNotExist() throws Exception {
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
        verify(outputFactory, times(2)).create(any());
    }

    @Test
    public void restoresFromBackupFileWhenNormalFileIsCorrupt() throws Exception {
        saveBackupFile();

        List<Alarm> alarms = new ArrayList<>();
        alarms.add(alarm());
        alarms.add(alarmFailingToStoreItself());

        storage.save(alarms);

        assertThat(backupFile, contains(defaultStoredAlarm(), defaultStoredAlarm(), closing));
        assertThat(storageFile, containsSingleLine(defaultStoredAlarm()));

        List<Alarm> storedAlarms = storage.load();
        assertThat(storedAlarms, hasSize(2));
        verify(exceptionHandler).handle(any(IOException.class));
    }

    private void saveBackupFile() {
        List<Alarm> alarms = new ArrayList<>();
        alarms.add(alarm());
        alarms.add(alarm());
        storage.save(alarms);
    }

    private Alarm alarmFailingToStoreItself() {
        return new Alarm(notUsedFactory, timeProvider, notUsedTrigger) {
            @Override
            public void storeTo(Writer output) throws IOException {
                throw new IOException("Failing to store me for test purpose");
            }
        };
    }

    @Test
    public void restoresAsMuchAsPossibleFromCorruptFileWhenBackupIsMissing() throws Exception {
        List<Alarm> alarms = new ArrayList<>();
        alarms.add(alarm());
        alarms.add(alarmFailingToStoreItself());

        storage.save(alarms);

        assertThat(backupFile, not(exists()));
        assertThat(storageFile, containsSingleLine(defaultStoredAlarm()));

        List<Alarm> storedAlarms = storage.load();
        assertThat(storedAlarms, hasSize(1));
        verify(exceptionHandler).handle(any(IOException.class));
    }

    @Test
    public void triggersExceptionHandlerWhenAlarmFileCanNotBeOpenedForSaving() throws Exception {
        doAnswer((answer) -> (answer)).when(exceptionHandler).handle(any());
        when(outputFactory.create(any())).thenThrow(new IOException("Exception in test"));
        List<Alarm> alarms = Collections.emptyList();

        storage.save(alarms);

        verify(exceptionHandler).handle(any(IOException.class));
    }
}
