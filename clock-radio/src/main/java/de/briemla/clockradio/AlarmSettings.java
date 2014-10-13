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
	private TextField second;

	public AlarmSettings() {
		super();
		FXUtil.load(this, this);
	}

	public void setCurrentAlarm(Alarm alarm) {
		hour.textProperty().bindBidirectional(alarm.hourProperty(), new NumberStringConverter());
		minute.textProperty().bindBidirectional(alarm.minuteProperty(), new NumberStringConverter());
		second.textProperty().bindBidirectional(alarm.secondProperty(), new NumberStringConverter());
	}

}
