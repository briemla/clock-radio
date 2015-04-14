package de.briemla.clockradio;

import java.net.URI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class JavaFxPlayer {
	private MediaPlayer lastPlayer;

	public void play(URI uriToPlay) {
		stop();
		Media media = new Media(uriToPlay.toString());
		lastPlayer = new MediaPlayer(media);
		lastPlayer.play();
	}

	public void stop() {
		if (lastPlayer != null) {
			lastPlayer.stop();
			lastPlayer.dispose();
			lastPlayer = null;
		}
	}

}
