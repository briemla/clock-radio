package de.briemla.clockradio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import de.briemla.clockradio.controls.MediaSelector;
import de.briemla.clockradio.controls.TimeEditor;

public class AlarmSettings extends HBox {

	@FXML
	private TimeEditor time;
	@FXML
	private MediaSelector source;
	private Alarm lastAlarm;
	private final Settings settings;

	public AlarmSettings(Settings settings) {
		super();
		this.settings = settings;
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
		time.timeProperty().unbindBidirectional(lastAlarm.wakeUpTimeProperty());
		source.mediaProperty().unbindBidirectional(lastAlarm.mediaProperty());
	}

	private void bindTo(Alarm alarm) {
		time.timeProperty().bindBidirectional(alarm.wakeUpTimeProperty());
		source.mediaProperty().bindBidirectional(alarm.mediaProperty());
		lastAlarm = alarm;
		time.switchToHour();
	}

	@FXML
	public void delete(ActionEvent event) {
		settings.delete(lastAlarm);
	}

}
