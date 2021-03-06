package de.briemla.clockradio.player;

import java.io.IOException;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;

import de.briemla.clockradio.dabpi.DABStation;
import de.briemla.clockradio.dabpi.FMStation;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.dabpi.command.Region;

public class BasePlayer implements Player {

    private static final Region DEFAULT_REGION = Region.BADEN_WUERTEMBERG;
    private final AudioFilePlayer streamPlayer;
    private final RadioPlayer radioPlayer;
    private final Region region;

    public BasePlayer(AudioFilePlayer streamPlayer, RadioPlayer radioPlayer) {
        this.streamPlayer = streamPlayer;
        this.radioPlayer = radioPlayer;
        region = DEFAULT_REGION;
    }

    /**
     * Method blocks until audio input is finished or closed.
     */
    @Override
    public void play(URI uriToPlay) {
        System.out.println("Start: " + LocalTime.now());
        streamPlayer.play(uriToPlay);
    }

    @Override
    public void stop() {
        System.out.println("Stop: " + LocalTime.now());
        streamPlayer.stop();
        radioPlayer.stop();
    }

    @Override
    public void play(Station station) throws IOException {
        radioPlayer.play(station);
    }

    @Override
    public List<DABStation> searchDAB() {
        return radioPlayer.scanDAB(region);
    }

    @Override
    public List<FMStation> searchFM() {
        return radioPlayer.scanFM();
    }

}
