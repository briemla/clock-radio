package de.briemla.clockradio;

import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainPanel extends VBox {

	@FXML
	private ViewSwitcher viewSwitch;
	@FXML
	private AlarmView alarm;

	private final Player player;

	public MainPanel(Player player) {
		super();
		this.player = player;
		FXUtil.load(this, this);
		Node clock = new Clock();
		viewSwitch.addView(clock);
		Node button = new Button("TROET");
		viewSwitch.addView(button);
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
