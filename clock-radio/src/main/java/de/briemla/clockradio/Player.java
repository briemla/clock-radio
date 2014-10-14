package de.briemla.clockradio;

import java.net.URI;

import javafx.beans.property.ReadOnlyBooleanProperty;

public interface Player {

	void play(URI uriToPlay);

	void stop();

	ReadOnlyBooleanProperty playingProperty();

}