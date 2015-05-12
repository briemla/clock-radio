package de.briemla.clockradio.controls;

import java.io.IOException;
import java.util.List;

import de.briemla.clockradio.dabpi.Station;

public interface SearchStation {

	List<? extends Station> search() throws IOException;

}
