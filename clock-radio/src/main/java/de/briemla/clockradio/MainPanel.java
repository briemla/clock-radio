package de.briemla.clockradio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class MainPanel extends VBox {

	@FXML
	private ViewSwitcher viewSwitch;
	@FXML
	private AlarmView alarm;

	private final Player player;
	private final Settings settings;

	public MainPanel(Player player) {
		super();
		this.player = player;
		FXUtil.load(this, this);
		TimeProvider timeProvider = new TimeProvider();
		settings = new Settings(viewSwitch, timeProvider, player);
		alarm.setSettings(settings);
		Node clock = new Clock(timeProvider);
		viewSwitch.setDefaultView(clock);
		AlarmSettings alarmSettings = new AlarmSettings();
		viewSwitch.addView(Alarm.class, alarmSettings);
	}

	@FXML
	public void openSettings(ActionEvent event) {

	}

	public void stopSound(ActionEvent event) {
		player.stop();
	}

}
