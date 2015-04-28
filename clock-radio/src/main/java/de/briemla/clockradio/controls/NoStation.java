package de.briemla.clockradio.controls;

import java.io.IOException;

import de.briemla.clockradio.dabpi.IDABStation;
import de.briemla.clockradio.dabpi.RadioController;

public class NoStation implements IDABStation {

	@Override
	public void tuneTo(RadioController controller) throws IOException {
	}

	@Override
	public String toString() {
		return "No station selected";
	}

}
