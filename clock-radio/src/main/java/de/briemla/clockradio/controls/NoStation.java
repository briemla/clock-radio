package de.briemla.clockradio.controls;

import java.io.IOException;

import de.briemla.clockradio.dabpi.RadioController;
import de.briemla.clockradio.dabpi.Station;

public class NoStation implements Station {

    @Override
    public void tuneTo(RadioController controller) throws IOException {
    }

    @Override
    public String toString() {
        return "No station selected";
    }

}
