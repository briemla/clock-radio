package de.briemla.clockradio;

import de.briemla.clockradio.player.PlayerFactory;
import de.briemla.clockradio.player.PlayerWorker;

public class PlayMusicOnError implements ExceptionHandler {

    private final PlayerFactory playerFactory;
    private final Media errorMedia;

    public PlayMusicOnError(PlayerFactory playerFactory, Logger logger) {
        super();
        this.playerFactory = playerFactory;
        errorMedia = () -> new PlayableError(logger);
    }

    @Override
    public void handle(Throwable throwable) {
        PlayerWorker player = playerFactory.create(errorMedia);
        player.start();
    }

}
