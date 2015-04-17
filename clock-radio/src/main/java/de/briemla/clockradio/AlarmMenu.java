package de.briemla.clockradio;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class AlarmMenu extends GridPane {

	@FXML
	private GridPane container;
	@FXML
	private Button time;
	@FXML
	private Button weekdays;
	@FXML
	private Button mediaDescription;
	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleObjectProperty<WakeUpTime> wakeUpTimeProperty;
	private final ViewSwitcher viewSwitcher;

	public AlarmMenu(ViewSwitcher viewSwitcher) {
		super();
		this.viewSwitcher = viewSwitcher;
		FXUtil.load(this, this);
		mediaProperty = new SimpleObjectProperty<>();
		wakeUpTimeProperty = new SimpleObjectProperty<>();
		time.textProperty().bind(wakeUpTimeProperty.asString());
		mediaDescription.textProperty().bind(mediaProperty.asString());
	}

	public void unbind() {
		mediaProperty.unbind();
		wakeUpTimeProperty.unbind();
	}

	public void setCurrentAlarm(Alarm alarm) {
		unbind();
		bindTo(alarm);
	}

	private void bindTo(Alarm alarm) {
		mediaProperty.bindBidirectional(alarm.mediaProperty());
		wakeUpTimeProperty.bind(alarm.wakeUpTimeProperty());
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
