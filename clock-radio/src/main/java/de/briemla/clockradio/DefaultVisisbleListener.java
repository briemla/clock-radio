package de.briemla.clockradio;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;

public class DefaultVisisbleListener implements ChangeListener<Boolean> {
	private final ListView<Alarm> alarm;

	public DefaultVisisbleListener(ListView<Alarm> alarm) {
		this.alarm = alarm;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		if (newValue) {
			alarm.getSelectionModel().clearSelection();
		}
	}
}