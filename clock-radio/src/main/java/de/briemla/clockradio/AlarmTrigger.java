package de.briemla.clockradio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;

import de.briemla.clockradio.controls.Trigger;

public class AlarmTrigger implements Trigger {

    private final List<Alarm> alarms;
    private boolean running;

    public AlarmTrigger() {
        super();
        alarms = new ArrayList<>();
        running = false;
    }

    @Override
    public void bind(ObservableList<Alarm> alarms) {
        Bindings.bindContent(this.alarms, alarms);
    }

    @Override
    public void start(LocalDateTime time) {
        if (running) {
            return;
        }
        for (Alarm alarm : alarms) {
            running = alarm.play(time);
            if (running) {
                break;
            }
        }
    }

    @Override
    public void startNow() {
        start(LocalDateTime.now());
    }

    @Override
    public void stop() {
        alarms.stream().forEach(Alarm::stop);
    }

}
