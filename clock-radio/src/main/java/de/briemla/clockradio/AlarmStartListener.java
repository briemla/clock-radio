package de.briemla.clockradio;

import java.time.LocalTime;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public class AlarmStartListener implements ChangeListener<LocalTime> {

	private final ObservableList<Alarm> alarms;
	private final SimpleBooleanProperty alarmStartedProperty;
	private final Player player;

	public AlarmStartListener(ObservableList<Alarm> alarms, Player player, SimpleBooleanProperty alarmStartedProperty) {
		super();
		this.alarms = alarms;
		this.player = player;
		this.alarmStartedProperty = alarmStartedProperty;
	}

	@Override
	public void changed(ObservableValue<? extends LocalTime> observable, LocalTime oldValue, LocalTime newValue) {
		if (alarmStartedProperty.get()) {
			return;
		}
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
