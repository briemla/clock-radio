package de.briemla.clockradio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.controls.Trigger;

public class AlarmTriggerTest {

    private ObservableList<Alarm> alarms;
    private Trigger trigger;

    @Before
    public void initialise() {
        alarms = FXCollections.observableArrayList();
        trigger = new AlarmTrigger();
        trigger.bind(alarms);
    }

    @Test
    public void startOneActiveAlarm() throws Exception {
        Alarm alarm = mock(Alarm.class);
        alarms.add(alarm);

        trigger.start();

        verify(alarm).play();
    }

    @Test
    public void startFirstMatchingAlarmAndIgnoreLaterAlarms() throws Exception {
        Alarm notMatching = mock(Alarm.class);
        when(notMatching.play()).thenReturn(false);
        Alarm matching = mock(Alarm.class);
        when(matching.play()).thenReturn(true);
        Alarm later = mock(Alarm.class);
        alarms.add(notMatching);
        alarms.add(matching);
        alarms.add(later);

        trigger.start();

        verify(notMatching).play();
        verify(matching).play();
        verifyZeroInteractions(later);
    }
}
