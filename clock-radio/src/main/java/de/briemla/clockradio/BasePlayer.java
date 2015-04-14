package de.briemla.clockradio;

import java.io.IOException;
import java.net.URI;
import java.time.LocalTime;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import de.briemla.clockradio.dabpi.Station;

public class BasePlayer implements Player {

	private final AudioFilePlayer streamPlayer;
	private final RadioPlayer radioPlayer;

	public BasePlayer(AudioFilePlayer streamPlayer, RadioPlayer radioPlayer) {
		this.streamPlayer = streamPlayer;
		this.radioPlayer = radioPlayer;
	}

	private final SimpleBooleanProperty playingProperty = new SimpleBooleanProperty(false);

	/**
	 * Method blocks until audio input is finished or closed.
	 */
	@Override
	public void play(URI uriToPlay) {
		System.out.println("Start: " + LocalTime.now());
		streamPlayer.play(uriToPlay);
	}

	@Override
	public void stop() {
		System.out.println("Stop: " + LocalTime.now());
		streamPlayer.stop();
		radioPlayer.stop();
		playingProperty.set(false);
	}

	@Override
	public ReadOnlyBooleanProperty playingProperty() {
		return playingProperty;
	}

	@Override
	public void play(Station station) throws IOException {
		radioPlayer.play(station);
	}

}
