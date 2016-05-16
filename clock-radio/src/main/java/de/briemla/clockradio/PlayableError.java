package de.briemla.clockradio;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

import de.briemla.clockradio.player.Player;

public class PlayableError implements PlayableMedia {

    private static final String errorSoundName = "ErrorSound.mp3";

    private final Logger logger;

    private final AtomicBoolean playing;

    public PlayableError(Logger logger) {
        super();
        this.logger = logger;
        playing = new AtomicBoolean(true);
    }

    @Override
    public void play(Player player) {
        try {
            playUntilStopped(player);
        } catch (URISyntaxException exception) {
            logger.log("Could not play error sound", exception);
        }
    }

    private void playUntilStopped(Player player) throws URISyntaxException {
        while (playing.get()) {
            player.play(errorSound());
        }
    }

    @Override
    public void stop(Player player) {
        playing.set(false);
        player.stop();
    }

    private static URI errorSound() throws URISyntaxException {
        return PlayableError.class.getResource(errorSoundName).toURI();
    }
}
