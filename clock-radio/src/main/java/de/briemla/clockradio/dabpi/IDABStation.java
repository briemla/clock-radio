package de.briemla.clockradio.dabpi;

import java.io.IOException;

public interface IDABStation extends Station {

	@Override
	void tuneTo(RadioController controller) throws IOException;

	@Override
	String toString();

}