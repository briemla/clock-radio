package de.briemla.clockradio;

import java.net.URI;

public interface Player {

	void play(URI uriToPlay);

	void stop();

}