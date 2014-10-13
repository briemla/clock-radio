package de.briemla.clockradio;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Settings {

	private final ObservableList<Alarm> alarms;
	private final ViewSwitcher viewSwitcher;
	private final AlarmSelectionListener alarmSelectionListener;
	private ObservableList<Alarm> selectedAlarms;

	public Settings(ViewSwitcher viewSwitcher) {
		this.viewSwitcher = viewSwitcher;
		alarms = FXCollections.observableArrayList();
		alarmSelectionListener = new AlarmSelectionListener(this.viewSwitcher);
	}

	public void addAlarm() {
		Alarm alarm = new Alarm();
		alarms.add(alarm);
		AlarmSettings alarmSettings = viewSwitcher.show(Alarm.class);
		alarmSettings.setCurrentAlarm(alarm);
	}

	public ObservableList<Alarm> getAlarms() {
		return alarms;
	}

	public void setSelectedAlarms(ObservableList<Alarm> selectedAlarms) {
		if (this.selectedAlarms != null) {
			this.selectedAlarms.removeListener(alarmSelectionListener);
		}
		this.selectedAlarms = selectedAlarms;
		this.selectedAlarms.addListener(alarmSelectionListener);
	}

	public ReadOnlyBooleanProperty defaultVisisbleProperty() {
		return viewSwitcher.defaultVisisbleProperty();
	}
}
