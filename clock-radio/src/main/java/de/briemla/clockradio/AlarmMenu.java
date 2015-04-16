package de.briemla.clockradio;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class AlarmMenu extends GridPane {

	private static final String TIME_FORMAT = "%02d";
	@FXML
	private GridPane container;
	@FXML
	private Button time;
	@FXML
	private Button weekdays;
	@FXML
	private Button mediaDescription;
	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleIntegerProperty hourProperty;
	private final SimpleIntegerProperty minuteProperty;
	private final SimpleStringProperty weekdaysProperty;
	private final ViewSwitcher viewSwitcher;

	public AlarmMenu(ViewSwitcher viewSwitcher) {
		super();
		this.viewSwitcher = viewSwitcher;
		FXUtil.load(this, this);
		mediaProperty = new SimpleObjectProperty<>();
		hourProperty = new SimpleIntegerProperty();
		minuteProperty = new SimpleIntegerProperty();
		weekdaysProperty = new SimpleStringProperty("Mo - Fr");
		time.textProperty().bind(
				hourProperty.asString(TIME_FORMAT).concat(":").concat(minuteProperty.asString(TIME_FORMAT)));
		mediaDescription.textProperty().bind(mediaProperty.asString());
	}

	public void unbind() {
		hourProperty.unbind();
		minuteProperty.unbind();
		mediaProperty.unbind();
	}

	public void setCurrentAlarm(Alarm alarm) {
		unbind();
		bindTo(alarm);
	}

	private void bindTo(Alarm alarm) {
		hourProperty.bindBidirectional(alarm.hourProperty());
		minuteProperty.bindBidirectional(alarm.minuteProperty());
		mediaProperty.bindBidirectional(alarm.mediaProperty());
	}

	@FXML
	public void selectMediaDescription(Event event) {

	}

	@FXML
	public void selectTime(Event event) {

	}

	@FXML
	public void selectWeekdays(Event event) {

	}

}
