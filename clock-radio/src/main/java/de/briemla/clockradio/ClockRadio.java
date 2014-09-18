package de.briemla.clockradio;

import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClockRadio extends Application {

	private final JavaFxPlayer player;

	public ClockRadio() {
		player = new JavaFxPlayer();
	}

	public static void main(String[] args) {
		Application.launch(ClockRadio.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainPanel mainPanel = new MainPanel();

		mainPanel.startTimeline();

		primaryStage.setScene(new Scene(mainPanel));
		primaryStage.show();
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
