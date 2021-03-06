package de.briemla.clockradio.controls;

import de.briemla.clockradio.Media;
import de.briemla.clockradio.PlayableMedia;
import de.briemla.clockradio.dabpi.Station;

public class RadioMedia implements Media {

    private final Station station;

    public RadioMedia(Station station) {
        this.station = station;
    }

    @Override
    public PlayableMedia create() {
        return new PlayableRadio(station);
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
