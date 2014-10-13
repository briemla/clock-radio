package de.briemla.clockradio;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;

public class AlarmView extends HBox {

	private Settings settings;

	public AlarmView() {
		super();
		FXUtil.load(this, this);
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public void add(ActionEvent event) {
		if (settings == null) {
			throw new NullPointerException("Settings have not been initialized in AlarmView");
		}

		settings.addAlarm();
	}

}
