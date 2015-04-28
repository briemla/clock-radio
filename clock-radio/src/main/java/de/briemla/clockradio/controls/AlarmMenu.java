package de.briemla.clockradio.controls;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import de.briemla.clockradio.ActiveDays;
import de.briemla.clockradio.Alarm;
import de.briemla.clockradio.FXUtil;
import de.briemla.clockradio.Media;
import de.briemla.clockradio.Settings;
import de.briemla.clockradio.WakeUpTime;

public class AlarmMenu extends VBox {

	@FXML
	private GridPane container;
	@FXML
	private Button time;
	@FXML
	private Button weekdays;
	@FXML
	private Button mediaDescription;
	private final SimpleObjectProperty<ActiveDays> activesDayProperty;
	private final SimpleObjectProperty<Media> mediaProperty;
	private final SimpleObjectProperty<WakeUpTime> wakeUpTimeProperty;
	private final DefaultableViewSwitcher viewSwitcher;
	private final Settings settings;
	private Alarm lastAlarm;

	public AlarmMenu(DefaultableViewSwitcher viewSwitcher, Settings settings) {
		super();
		this.viewSwitcher = viewSwitcher;
		this.settings = settings;
		FXUtil.load(this, this);
		activesDayProperty = new SimpleObjectProperty<>(new ActiveDays());
		mediaProperty = new SimpleObjectProperty<>(new LocalFolder());
		wakeUpTimeProperty = new SimpleObjectProperty<>(new WakeUpTime(0, 0));
		time.textProperty().bind(wakeUpTimeProperty.asString());
		mediaDescription.textProperty().bind(mediaProperty.asString());
		weekdays.textProperty().bind(activesDayProperty.asString());
		registerViews();
	}

	private void registerViews() {
		TimeEditor editor = new TimeEditor();
		viewSwitcher.addView(WakeUpTime.class, editor);
		editor.timeProperty().bindBidirectional(wakeUpTimeProperty);
		MediaSelector mediaSelector = new MediaSelector(settings);
		viewSwitcher.addView(Media.class, mediaSelector);
		mediaSelector.mediaProperty().bindBidirectional(mediaProperty);
		ActiveDayEditor activeDayEditor = new ActiveDayEditor();
		viewSwitcher.addView(ActiveDays.class, activeDayEditor);
		activeDayEditor.daysProperty().bindBidirectional(activesDayProperty);
	}

	public void unbind() {
		if (lastAlarm == null) {
			return;
		}
		activesDayProperty.unbindBidirectional(lastAlarm.activeDaysProperty());
		mediaProperty.unbindBidirectional(lastAlarm.mediaProperty());
		wakeUpTimeProperty.unbindBidirectional(lastAlarm.wakeUpTimeProperty());
	}

	public void setCurrentAlarm(Alarm alarm) {
		unbind();
		bindTo(alarm);
		lastAlarm = alarm;
	}

	private void bindTo(Alarm alarm) {
		activesDayProperty.bindBidirectional(alarm.activeDaysProperty());
		mediaProperty.bindBidirectional(alarm.mediaProperty());
		wakeUpTimeProperty.bindBidirectional(alarm.wakeUpTimeProperty());
	}

	@FXML
	public void selectMediaDescription(Event event) {
		viewSwitcher.show(Media.class);
	}

	@FXML
	public void selectTime(Event event) {
		viewSwitcher.show(WakeUpTime.class);
	}

	@FXML
	public void selectWeekdays(Event event) {
		viewSwitcher.show(ActiveDays.class);
	}

	@FXML
	public void delete(Event event) {
		settings.delete(lastAlarm);
	}
}
