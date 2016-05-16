package de.briemla.clockradio;

import java.net.URI;
import java.net.URISyntaxException;

import de.briemla.clockradio.player.Player;

public class PlayableError implements PlayableMedia {

    private static final String errorSoundName = "ErrorSound.mp3";

    private final Logger logger;

    public PlayableError(Logger logger) {
        super();
        this.logger = logger;
    }

    @Override
    public void play(Player player) {
        try {
            player.play(errorSound());
        } catch (URISyntaxException exception) {
            logger.log("Could not play error sound", exception);
        }
    }

    @Override
    public void stop(Player player) {
        player.stop();
    }

    private static URI errorSound() throws URISyntaxException {
        return PlayableError.class.getResource(errorSoundName).toURI();
    }
}
