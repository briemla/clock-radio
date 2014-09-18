package de.briemla.clockradio;

import java.net.URI;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class JavaFxPlayer implements Player {
	private MediaPlayer lastPlayer;

	@Override
	public void play(URI uriToPlay) {
		Media media = new Media(uriToPlay.toString());
		lastPlayer = new MediaPlayer(media);
		lastPlayer.play();
	}

	@Override
	public void stop() {
		if (lastPlayer != null) {
			lastPlayer.stop();
		}
	}
}
