package de.briemla.clockradio;

import java.util.Iterator;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Popup;

public class Settings {

	private final ViewSwitcher viewSwitcher;
	private final SimpleBooleanProperty alarmStartedProperty;
	private final ObservableList<Alarm> alarms;
	private final Player player;
	private final OverlaySwitcher overlaySwitcher;

	public Settings(ViewSwitcher viewSwitcher, Player player, OverlaySwitcher overlaySwitcher) {
		this.viewSwitcher = viewSwitcher;
		this.player = player;
		this.overlaySwitcher = overlaySwitcher;
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
		// AlarmMenu menu = overlaySwitcher.show(Alarm.class);
		// menu.setCurrentAlarm(alarm);
		AlarmMenu alarmMenu = new AlarmMenu();
		alarmMenu.setCurrentAlarm(alarm);
		Popup popup = new Popup();
		popup.setAutoHide(true);
		popup.getContent().add(alarmMenu);
		AlarmSettings alarmSettings = viewSwitcher.show(Alarm.class);
		alarmSettings.setCurrentAlarm(alarm);
		popup.show(alarmSettings.getScene().getWindow());
	}

	public void delete(Alarm alarm) {
		viewSwitcher.showDefault();
		alarm.kill();
		alarms.remove(alarm);
		rebindAlarms();
	}

	public void stopCurrentAlarm() {
		for (Alarm alarm : alarms) {
			alarm.stop();
		}
	}
}
