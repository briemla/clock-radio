package de.briemla.clockradio;

public interface PlayerFactory {

    PlayerWorker create(Media media);

}
