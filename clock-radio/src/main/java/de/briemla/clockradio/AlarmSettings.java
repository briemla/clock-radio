package de.briemla.clockradio;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import de.briemla.clockradio.controls.MediaSelector;
import de.briemla.clockradio.controls.Time;

public class AlarmSettings extends HBox {

	@FXML
	private Time time;
	@FXML
	private MediaSelector source;
	private Alarm lastAlarm;

	public AlarmSettings() {
		super();
		FXUtil.load(this, this);
	}

	public void setCurrentAlarm(Alarm alarm) {
		unbind();
		bindTo(alarm);
	}

	private void unbind() {
		if (lastAlarm == null) {
			return;
		}
		time.hourProperty().unbindBidirectional(lastAlarm.hourProperty());
		time.minuteProperty().unbindBidirectional(lastAlarm.minuteProperty());
		source.mediaProperty().unbindBidirectional(lastAlarm.mediaProperty());
	}

	private void bindTo(Alarm alarm) {
		time.hourProperty().bindBidirectional(alarm.hourProperty());
		time.minuteProperty().bindBidirectional(alarm.minuteProperty());
		source.mediaProperty().bindBidirectional(alarm.mediaProperty());
		lastAlarm = alarm;
		time.switchToHour();
	}

}
