package de.briemla.clockradio;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import de.briemla.clockradio.controls.TimeEditor;

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
		wakeUpTimeProperty = new SimpleObjectProperty<>(new WakeUpTime(0, 0));
		time.textProperty().bind(wakeUpTimeProperty.asString());
		mediaDescription.textProperty().bind(mediaProperty.asString());
		registerViews();
	}

	private void registerViews() {
		TimeEditor editor = new TimeEditor();
		viewSwitcher.addView(WakeUpTime.class, editor);
		editor.timeProperty().bindBidirectional(wakeUpTimeProperty);
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
		wakeUpTimeProperty.bindBidirectional(alarm.wakeUpTimeProperty());
	}

	@FXML
	public void selectMediaDescription(Event event) {

	}

	@FXML
	public void selectTime(Event event) {
		viewSwitcher.show(WakeUpTime.class);
	}

	@FXML
	public void selectWeekdays(Event event) {

	}

}
