package de.briemla.clockradio.player;

import java.io.IOException;
import java.util.List;

import de.briemla.clockradio.dabpi.DABStation;
import de.briemla.clockradio.dabpi.FMStation;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.dabpi.command.Region;

public interface RadioPlayer {

    List<FMStation> scanFM();

    List<DABStation> scanDAB(Region region);

    void play(Station station) throws IOException;

    void stop();

}