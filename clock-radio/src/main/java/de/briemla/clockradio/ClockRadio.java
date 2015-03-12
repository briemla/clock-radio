package de.briemla.clockradio;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
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
		watchCssFile(mainPanel);
		primaryStage.setScene(new Scene(mainPanel));
		primaryStage.show();
	}

	private static void watchCssFile(MainPanel mainPanel) throws URISyntaxException, MalformedURLException {
		if (!"amd64".equals(System.getProperty("os.arch"))) {
			return;
		}
		File file = new File(ClockRadio.class.getResource("clock-radio.css").toURI());
		mainPanel.getStylesheets().add(file.toURI().toURL().toExternalForm());
		Path myDir = file.getParentFile().toPath();

		try {
			WatchService watcher = myDir.getFileSystem().newWatchService();
			myDir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);

			Thread thread = new Thread("CSS Watcher") {

				@Override
				public void run() {
					try {
						WatchKey watckKey = watcher.take();

						List<WatchEvent<?>> events = watckKey.pollEvents();
						for (WatchEvent<?> event : events) {
							if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
								Platform.runLater(() -> {
									mainPanel.getStylesheets().clear();
									mainPanel.getStylesheets().add("file:" + file.getAbsolutePath());
									System.out.println("CSS file changed");
								});
							}
						}
					} catch (Exception e) {
						System.out.println("Error: " + e.toString());
					}
				}
			};
			thread.setDaemon(true);
			thread.start();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}

	}

	/**
	 * Will check which player is available. On Raspberry Pi there is no
	 * javafx.scene.media support, so we need another player.
	 *
	 * @return
	 */
	private static Player availablePlayer() {
		return new AudioFilePlayer();
		// return new JavaFxPlayer();
	}

}
