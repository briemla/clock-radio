package de.briemla.clockradio;

import java.net.URI;
import java.net.URISyntaxException;

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
		settings = new Settings(viewSwitch);
		alarm.setSettings(settings);
		Node clock = new Clock();
		viewSwitch.setDefaultView(clock);
		AlarmSettings alarmSettings = new AlarmSettings();
		viewSwitch.addView(Alarm.class, alarmSettings);
	}

	@FXML
	public void openSettings(ActionEvent event) {

	}

	public void startSound(ActionEvent event) {
		try {
			URI uri = new URI("file:///D:/Bibliotheken/Musik/WCG_Theme_Song.mp3");
			player.play(uri);
		} catch (URISyntaxException exception) {
			exception.printStackTrace();
		}
	}

	public void stopSound(ActionEvent event) {
		player.stop();
	}

}
