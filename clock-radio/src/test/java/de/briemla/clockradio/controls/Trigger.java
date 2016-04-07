package de.briemla.clockradio.controls;

import javafx.collections.ObservableList;

import de.briemla.clockradio.Alarm;

public interface Trigger {

    void bind(ObservableList<Alarm> alarms);

}
