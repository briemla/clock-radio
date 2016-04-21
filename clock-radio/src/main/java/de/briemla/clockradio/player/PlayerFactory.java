package de.briemla.clockradio.player;

import de.briemla.clockradio.Media;

public interface PlayerFactory {

    PlayerWorker create(Media media);

}
