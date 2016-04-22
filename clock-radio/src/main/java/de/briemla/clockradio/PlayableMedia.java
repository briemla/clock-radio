package de.briemla.clockradio;

import de.briemla.clockradio.player.Player;

public interface PlayableMedia {

    Media create();

    void play(Player player);

    void stop(Player player);

}
