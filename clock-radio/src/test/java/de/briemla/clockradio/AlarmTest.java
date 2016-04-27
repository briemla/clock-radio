package de.briemla.clockradio;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.EnumSet;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.controls.LocalFolder;
import de.briemla.clockradio.player.PlayerFactory;
import de.briemla.clockradio.player.PlayerWorker;

public class AlarmTest {

    private static final String defaultWakeUpTime = "01:02";
    private static final String separator = ";";
    private static final String defaultMedia = LocalFolder.defaultFolder().toString();
    private static final LocalDate date = LocalDate.of(0, 1, 1);
    private static final LocalTime now = LocalTime.of(1, 2);
    private PlayerFactory player;
    private PlayerWorker worker;
    private TimeProvider timeProvider;
    private Alarm alarm;
    private SaveTrigger storage;

    @Before
    public void initialise() {
        player = mock(PlayerFactory.class);
        worker = mock(PlayerWorker.class);
        when(player.create(any(Media.class))).thenReturn(worker);
        timeProvider = mock(TimeProvider.class);
        when(timeProvider.nextMinute()).thenReturn(now);
        LocalDateTime todayMorning = LocalDateTime.of(date, now).withHour(0).withMinute(0);
        when(timeProvider.today()).thenReturn(todayMorning);
        storage = mock(SaveTrigger.class);
        alarm = new Alarm(player, timeProvider, storage);
    }

    @Test
    public void playMusicInBackground() throws Exception {
        boolean playing = alarm.play(playTime());

        assertThat(playing, is(true));
        verify(player).create(any(Media.class));
        verify(worker).start();
    }

    @Test
    public void playAndStopMusic() throws Exception {
        boolean playing = alarm.play(playTime());
        alarm.stop();

        assertThat(playing, is(true));
        verify(player).create(any(Media.class));
        verify(worker).start();
        verify(worker).stop();
    }

    @Test
    public void whenAlarmIsStartedInTheMinuteBeforeWakeupTime() throws Exception {
        alarm.wakeUpTimeProperty().set(new WakeUpTime(1, 1));

        LocalDateTime atTime = playTime();
        boolean playing = alarm.play(atTime);

        assertThat(playing, is(false));
        verifyZeroInteractions(player);
    }

    @Test
    public void requestInitialWakeUpTimeFromTimeProvider() throws Exception {
        WakeUpTime wakeUpTime = alarm.wakeUpTimeProperty().get();
        assertThat(wakeUpTime, is(equalTo(new WakeUpTime(now.getHour(), now.getMinute()))));
        verify(timeProvider).nextMinute();
    }

    @Test
    public void whenAlarmIsStartedStoppedAndStartedInTheSameMinute() throws Exception {

    }

    @Test
    public void whenAlarmIsStartedInTheMinuteAfterWakeuptime() throws Exception {
        boolean playing = alarm.play(playTime().plus(1, MINUTES));

        assertThat(playing, is(false));
        verifyZeroInteractions(player);
    }

    private static LocalDateTime playTime() {
        return LocalDateTime.of(date, LocalTime.of(1, 2));
    }

    @Test
    public void saveToStorageWhenWakeUpTimeIsAltered() throws Exception {
        WakeUpTime toAnotherWakeUpTime = new WakeUpTime(12, 34);

        alarm.wakeUpTimeProperty().set(toAnotherWakeUpTime);

        verify(storage).save();
    }

    @Test
    public void saveToStorageWhenMediaPropertyIsAltered() throws Exception {
        Media toAnotherMedia = mock(Media.class);

        alarm.mediaProperty().set(toAnotherMedia);

        verify(storage).save();
    }

    @Test
    public void saveToStorageWhenActiveDaysAreAltered() throws Exception {
        ActiveDays toOtherActiveDays = new ActiveDays(EnumSet.of(DayOfWeek.MONDAY));

        alarm.activeDaysProperty().set(toOtherActiveDays);

        verify(storage).save();
    }

    @Test
    public void saveToStorageWhenAlarmIsActivatedOrDeactivated() throws Exception {
        alarm.activatedProperty().setValue(false);
        alarm.activatedProperty().setValue(true);

        verify(storage, times(2)).save();
    }

    @Test
    public void storeInitialAlarmToOutput() throws Exception {
        PrintStream output = mock(PrintStream.class);
        alarm.storeTo(output);

        verify(output).println(defaultWakeUpTime + separator + defaultMedia);
    }

    @Test
    public void storeChangedWakeUpTimeToOutput() throws Exception {
        WakeUpTime toAnotherWakeUpTime = new WakeUpTime(12, 34);
        alarm.wakeUpTimeProperty().set(toAnotherWakeUpTime);

        PrintStream output = mock(PrintStream.class);
        alarm.storeTo(output);

        String changedWakeUpTime = "12:34";
        verify(output).println(changedWakeUpTime + separator + defaultMedia);
    }

    @Test
    public void storeChangedMediaToOutput() throws Exception {
        Media myMedia = mock(Media.class);
        when(myMedia.toString()).thenReturn("myMedia");
        alarm.mediaProperty().set(myMedia);

        PrintStream output = mock(PrintStream.class);
        alarm.storeTo(output);

        String media = "myMedia";
        verify(output).println(defaultWakeUpTime + separator + media);
    }

}
