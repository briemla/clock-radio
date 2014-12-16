package de.briemla.clockradio;

import java.util.Iterator;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Settings {

	private final ViewSwitcher viewSwitcher;
	private final SimpleBooleanProperty alarmStartedProperty;
	private final ObservableList<Alarm> alarms;
	private final Player player;

	public Settings(ViewSwitcher viewSwitcher, Player player) {
		this.viewSwitcher = viewSwitcher;
		this.player = player;
		alarmStartedProperty = new SimpleBooleanProperty();
		alarms = FXCollections.observableArrayList();
	}

	public void addAlarm() {
		Alarm alarm = new Alarm(alarmStartedProperty, player);
		alarms.add(alarm);
		rebindAlarms();
		AlarmSettings alarmSettings = viewSwitcher.show(Alarm.class);
		alarmSettings.setCurrentAlarm(alarm);
	}

	private void rebindAlarms() {
		alarmStartedProperty.unbind();
		if (alarms.isEmpty()) {
			return;
		}
		Iterator<Alarm> alarmIterator = alarms.iterator();
		Alarm nextAlarm = alarmIterator.next();
		ReadOnlyBooleanProperty binding = nextAlarm.alarmStartedProperty();
		for (Alarm alarm : alarms) {
			binding.or(alarm.alarmStartedProperty());
		}
		alarmStartedProperty.bind(binding);
	}

	public ObservableList<Alarm> getAlarms() {
		return alarms;
	}

	public ReadOnlyBooleanProperty defaultVisisbleProperty() {
		return viewSwitcher.defaultVisisbleProperty();
	}

	public void select(Alarm alarm) {
		AlarmSettings alarmSettings = viewSwitcher.show(Alarm.class);
		alarmSettings.setCurrentAlarm(alarm);
	}

	public void delete(Alarm alarm) {
		viewSwitcher.showDefault();
		alarms.remove(alarm);
		rebindAlarms();
	}
}
