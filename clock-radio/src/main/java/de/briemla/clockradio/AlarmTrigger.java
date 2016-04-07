package de.briemla.clockradio;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;

import de.briemla.clockradio.controls.Trigger;

public class AlarmTrigger implements Trigger {

    private final List<Alarm> alarms;

    public AlarmTrigger() {
        super();
        alarms = new ArrayList<>();
    }

    @Override
    public void bind(ObservableList<Alarm> alarms) {
        Bindings.bindContent(this.alarms, alarms);
    }

    @Override
    public void start() {
        for (Alarm alarm : alarms) {
            if (alarm.play()) {
                break;
            }
        }
    }

}
