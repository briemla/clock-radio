package de.briemla.clockradio;

import de.briemla.clockradio.player.PlayableMedia;
import de.briemla.clockradio.player.Player;

public interface Media {

    void play(Player player);

    void stop(Player player);

    PlayableMedia create();

}