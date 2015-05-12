package de.briemla.clockradio.dabpi;

import java.io.IOException;

public interface Station {

	void tuneTo(RadioController controller) throws IOException;

	@Override
    String toString();

}