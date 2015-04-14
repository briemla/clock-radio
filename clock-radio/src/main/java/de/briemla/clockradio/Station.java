package de.briemla.clockradio;

import java.io.IOException;

import de.briemla.clockradio.dabpi.RadioController;

public interface Station {

	void tuneTo(RadioController controller) throws IOException;

}