package de.briemla.clockradio;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

public class AlarmSettings extends HBox {

	@FXML
	private TextField hour;
	@FXML
	private TextField minute;
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
		hour.textProperty().unbindBidirectional(lastAlarm.hourProperty());
		minute.textProperty().unbindBidirectional(lastAlarm.minuteProperty());
	}

	private void bindTo(Alarm alarm) {
		hour.textProperty().bindBidirectional(alarm.hourProperty(), new NumberStringConverter());
		minute.textProperty().bindBidirectional(alarm.minuteProperty(), new NumberStringConverter());
		lastAlarm = alarm;
	}

}
