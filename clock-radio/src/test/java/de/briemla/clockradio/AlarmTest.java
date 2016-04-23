package de.briemla.clockradio;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.player.PlayerFactory;
import de.briemla.clockradio.player.PlayerWorker;

public class AlarmTest {

    private static final LocalDate date = LocalDate.of(0, 1, 1);
    private PlayerFactory player;
    private PlayerWorker worker;
    private TimeProvider timeProvider;
    private LocalTime now;
    private Alarm alarm;

    @Before
    public void initialise() {
        player = mock(PlayerFactory.class);
        worker = mock(PlayerWorker.class);
        when(player.create(any(Media.class))).thenReturn(worker);
        timeProvider = mock(TimeProvider.class);
        now = LocalTime.of(1, 2);
        when(timeProvider.nextMinute()).thenReturn(now);
        LocalDateTime todayMorning = LocalDateTime.of(date, now).withHour(0).withMinute(0);
        when(timeProvider.today()).thenReturn(todayMorning);
        alarm = new Alarm(player, timeProvider);
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
}
