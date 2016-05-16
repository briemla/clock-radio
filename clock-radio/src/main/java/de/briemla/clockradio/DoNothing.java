package de.briemla.clockradio;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import de.briemla.clockradio.dabpi.DABStation;
import de.briemla.clockradio.dabpi.FMStation;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.dabpi.command.Region;
import de.briemla.clockradio.player.RadioPlayer;

public class DoNothing implements RadioPlayer {

    @Override
    public List<FMStation> scanFM() {
        return Collections.emptyList();
    }

    @Override
    public List<DABStation> scanDAB(Region region) {
        return Collections.emptyList();
    }

    @Override
    public void play(Station station) throws IOException {
    }

    @Override
    public void stop() {
    }

}
