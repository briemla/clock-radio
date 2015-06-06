package de.briemla.clockradio.controls;

import java.util.List;

import de.briemla.clockradio.dabpi.Station;

public interface SearchStation {

    List<? extends Station> search();

}
