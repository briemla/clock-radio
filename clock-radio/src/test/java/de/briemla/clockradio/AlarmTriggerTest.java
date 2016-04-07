package de.briemla.clockradio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.junit.Test;

import de.briemla.clockradio.controls.Trigger;

public class AlarmTriggerTest {

    @Test
    public void startOneActiveAlarm() throws Exception {
        ObservableList<Alarm> alarms = FXCollections.observableArrayList();
        Trigger trigger = new AlarmTrigger();
        trigger.bind(alarms);

        Alarm alarm = mock(Alarm.class);
        alarms.add(alarm);

        trigger.start();

        verify(alarm).play();
    }
}
