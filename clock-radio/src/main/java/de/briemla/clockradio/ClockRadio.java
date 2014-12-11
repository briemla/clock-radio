package de.briemla.clockradio;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClockRadio extends Application {

	public static void main(String[] args) {
		Application.launch(ClockRadio.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainPanel mainPanel = new MainPanel(availablePlayer());
		mainPanel.getStylesheets().add(ClockRadio.class.getResource("clock-radio.css").toExternalForm());
		primaryStage.setScene(new Scene(mainPanel));
		primaryStage.show();
	}

	/**
	 * Will check which player is available. On Raspberry Pi there is no javafx.scene.media support,
	 * so we need another player.
	 *
	 * @return
	 */
	private static Player availablePlayer() {
		return new JavaFxPlayer();
	}

}
