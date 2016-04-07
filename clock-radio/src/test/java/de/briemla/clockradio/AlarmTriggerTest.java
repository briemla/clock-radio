package de.briemla.clockradio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
}
