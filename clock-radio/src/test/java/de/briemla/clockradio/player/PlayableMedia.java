package de.briemla.clockradio.player;

import de.briemla.clockradio.Media;

public interface PlayableMedia {

    Media create();

    void play(Player player);

    void stop(Player player);

}
