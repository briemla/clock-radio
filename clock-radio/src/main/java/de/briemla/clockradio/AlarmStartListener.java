package de.briemla.clockradio;

import java.time.LocalTime;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public class AlarmStartListener implements ChangeListener<LocalTime> {

	private final ObservableList<Alarm> alarms;
	private final Player player;

	public AlarmStartListener(ObservableList<Alarm> alarms, Player player) {
		super();
		this.alarms = alarms;
		this.player = player;
	}

	@Override
	public void changed(ObservableValue<? extends LocalTime> observable, LocalTime oldValue, LocalTime newValue) {
		int hour = newValue.getHour();
		int minute = newValue.getMinute();
		for (Alarm alarm : alarms) {
			if (alarm.hourProperty().get() == hour && alarm.minuteProperty().get() == minute) {
				alarm.play(player);
				return;
			}
		}
	}

}
