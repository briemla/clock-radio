package de.briemla.clockradio.player;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import de.briemla.clockradio.dabpi.DABStation;
import de.briemla.clockradio.dabpi.FMStation;
import de.briemla.clockradio.dabpi.Station;

public interface Player {

    void play(URI uriToPlay);

    void play(Station station) throws IOException;

    void stop();

    List<DABStation> searchDAB();

    List<FMStation> searchFM();

}