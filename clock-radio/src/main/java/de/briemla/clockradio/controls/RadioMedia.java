package de.briemla.clockradio.controls;

import java.io.IOException;

import de.briemla.clockradio.Media;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.player.PlayableMedia;
import de.briemla.clockradio.player.Player;

public class RadioMedia implements Media {

    private final Station station;

    public RadioMedia(Station station) {
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
    public PlayableMedia create() {
        return null;
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
        RadioMedia other = (RadioMedia) obj;
        if (station == null) {
            if (other.station != null) {
                return false;
            }
        } else if (!station.equals(other.station)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return station.toString();
    }

}
