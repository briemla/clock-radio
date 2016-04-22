package de.briemla.clockradio.controls;

import java.io.IOException;

import de.briemla.clockradio.PlayableMedia;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.player.Player;

public class PlayableRadio implements PlayableMedia {

    private final Station station;

    public PlayableRadio(Station station) {
        super();
        this.station = station;
    }

    @Override
    public void play(Player player) {
        try {
            player.play(station);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void stop(Player player) {
        player.stop();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((station == null) ? 0 : station.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PlayableRadio other = (PlayableRadio) obj;
        if (station == null) {
            if (other.station != null) {
                return false;
            }
        } else if (!station.equals(other.station)) {
            return false;
        }
        return true;
    }

}
