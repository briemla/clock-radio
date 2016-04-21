package de.briemla.clockradio.controls;

import java.time.LocalDateTime;

import javafx.collections.ObservableList;

import de.briemla.clockradio.Alarm;

public interface Trigger {

    void bind(ObservableList<Alarm> alarms);

    void start(LocalDateTime time);

    void startNow();

    void stop();

}
