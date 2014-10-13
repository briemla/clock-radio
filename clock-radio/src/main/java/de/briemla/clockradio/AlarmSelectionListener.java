package de.briemla.clockradio;

import javafx.collections.ListChangeListener;

public class AlarmSelectionListener implements ListChangeListener<Alarm> {

	private final ViewSwitcher viewSwitcher;

	public AlarmSelectionListener(ViewSwitcher viewSwitcher) {
		super();
		this.viewSwitcher = viewSwitcher;
	}

	@Override
	public void onChanged(Change<? extends Alarm> change) {
		while (change.next()) {
			if (change.wasReplaced()) {
				for (Alarm alarm : change.getAddedSubList()) {
					if (alarm == null) {
						continue;
					}
					AlarmSettings alarmSettings = viewSwitcher.show(Alarm.class);
					alarmSettings.setCurrentAlarm(alarm);
				}
				continue;
			}
			if (change.wasRemoved()) {
				viewSwitcher.showDefault();
			}
			if (change.wasAdded()) {
				for (Alarm alarm : change.getAddedSubList()) {
					AlarmSettings alarmSettings = viewSwitcher.show(Alarm.class);
					alarmSettings.setCurrentAlarm(alarm);
				}
			}
		}
	}
}