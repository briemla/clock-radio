package de.briemla.clockradio.player;

import java.io.IOException;
import java.net.URI;

import javafx.beans.property.ReadOnlyBooleanProperty;
import de.briemla.clockradio.dabpi.Station;

public interface Player {

	void play(URI uriToPlay);

	void play(Station station) throws IOException;

	void stop();

	ReadOnlyBooleanProperty playingProperty();

}