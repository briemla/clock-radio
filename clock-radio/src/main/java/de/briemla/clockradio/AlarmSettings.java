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
	@FXML
	private final MediaSelector mediaSelector = new MediaSelector();
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
		mediaSelector.mediaProperty().unbindBidirectional(lastAlarm.mediaProperty());
	}

	private void bindTo(Alarm alarm) {
		hour.textProperty().bindBidirectional(alarm.hourProperty(), new NumberStringConverter());
		minute.textProperty().bindBidirectional(alarm.minuteProperty(), new NumberStringConverter());
		mediaSelector.mediaProperty().bindBidirectional(alarm.mediaProperty());
		lastAlarm = alarm;
	}

}
