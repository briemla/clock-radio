package de.briemla.clockradio.controls;

import java.io.IOException;

import de.briemla.clockradio.Media;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.player.Player;

public class RadioMedia implements Media {

	private final Station station;

	public RadioMedia(Station station) {
		this.station = station;
	}

	@Override
	public void play(Player player) {
		try {
			player.play(station);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void stop(Player player) {
		player.stop();
	}

}
