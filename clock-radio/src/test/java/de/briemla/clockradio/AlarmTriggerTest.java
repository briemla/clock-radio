package de.briemla.clockradio;

import static java.time.LocalDateTime.now;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.junit.Before;
import org.junit.Test;

public class AlarmTriggerTest {

    private ObservableList<Alarm> alarms;
    private AlarmTrigger trigger;
    private TimeProvider timeProvider;

    @Before
    public void initialise() {
        alarms = FXCollections.observableArrayList();
        timeProvider = mock(TimeProvider.class);
        trigger = new AlarmTrigger(timeProvider);
        trigger.bind(alarms);
    }

    @Test
    public void startOneActiveAlarm() throws Exception {
        Alarm alarm = mock(Alarm.class);
        alarms.add(alarm);

        trigger.startNow();

        verify(alarm).play(any(LocalDateTime.class));
        verify(timeProvider).now();
    }

    @Test
    public void startFirstMatchingAlarmAndIgnoreLaterAlarms() throws Exception {
        Alarm notMatching = mock(Alarm.class);
        when(notMatching.play(any(LocalDateTime.class))).thenReturn(false);
        Alarm matching = mock(Alarm.class);
        when(matching.play(any(LocalDateTime.class))).thenReturn(true);
        Alarm later = mock(Alarm.class);
        alarms.add(notMatching);
        alarms.add(matching);
        alarms.add(later);

        trigger.startNow();

        verify(notMatching).play(any(LocalDateTime.class));
        verify(matching).play(any(LocalDateTime.class));
        verifyZeroInteractions(later);
        verify(timeProvider).now();
    }

    @Test
    public void doNotStartAnotherAlarmWhenOneIsRunning() throws Exception {
        Alarm running = mock(Alarm.class);
        when(running.play(any())).thenReturn(true);
        Alarm another = mock(Alarm.class);
        alarms.add(running);
        alarms.add(another);

        trigger.start(now());
        trigger.start(now());

        verify(running).play(any());
        verifyZeroInteractions(running);
        verifyZeroInteractions(another);
    }

    @Test
    public void startOneAlarmAndStopAllAlarms() throws Exception {
        Alarm running = mock(Alarm.class);
        when(running.play(any())).thenReturn(true);
        Alarm notRunning = mock(Alarm.class);
        alarms.add(running);
        alarms.add(notRunning);

        trigger.start(now());
        trigger.stop();

        verify(running).play(any());
        verify(running).stop();
        verify(notRunning).stop();
        verifyNoMoreInteractions(running);
        verifyNoMoreInteractions(notRunning);
    }

    @Test
    public void startAlarmAfterAlarmsHaveBeenStopped() throws Exception {
        Alarm alarm = mock(Alarm.class);
        when(alarm.play(any())).thenReturn(true);
        alarms.add(alarm);

        trigger.start(now());
        trigger.stop();
        trigger.start(now());

        verify(alarm, times(2)).play(any());
        verify(alarm).stop();
    }

}
