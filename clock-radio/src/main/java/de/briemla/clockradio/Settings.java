package de.briemla.clockradio;

import java.util.List;

import javafx.collections.FXCollections;

public class Settings {

	private final List<Alarm> alarms = FXCollections.observableArrayList();
	private final ViewSwitcher viewSwitch;

	public Settings(ViewSwitcher viewSwitch) {
		this.viewSwitch = viewSwitch;
	}

	public void addAlarm() {
		Alarm alarm = new Alarm();
		alarms.add(alarm);
		AlarmSettings alarmSettings = viewSwitch.show(Alarm.class);
		alarmSettings.setCurrentAlarm(alarm);
	}

}
