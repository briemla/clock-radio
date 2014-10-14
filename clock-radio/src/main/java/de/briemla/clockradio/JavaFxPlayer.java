package de.briemla.clockradio;

import java.net.URI;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class JavaFxPlayer implements Player {
	private MediaPlayer lastPlayer;
	private final SimpleBooleanProperty playingProperty = new SimpleBooleanProperty(false);

	@Override
	public void play(URI uriToPlay) {
		stop();
		Media media = new Media(uriToPlay.toString());
		lastPlayer = new MediaPlayer(media);
		lastPlayer.play();
		playingProperty.set(true);
	}

	@Override
	public void stop() {
		if (lastPlayer != null) {
			lastPlayer.stop();
			lastPlayer.dispose();
			lastPlayer = null;
			playingProperty.set(false);
		}
	}

	@Override
	public ReadOnlyBooleanProperty playingProperty() {
		return playingProperty;
	}
}
