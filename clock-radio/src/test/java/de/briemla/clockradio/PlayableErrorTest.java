package de.briemla.clockradio;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.player.Player;

public class PlayableErrorTest {

    private Player player;
    private PlayableError error;
    private Logger notNeeded;

    @Before
    public void initialise() {
        player = mock(Player.class);
        notNeeded = mock(Logger.class);

        error = new PlayableError(notNeeded);
    }

    @Test
    public void playsErrorMediaOnPlayer() throws Exception {
        error.play(player);

        URI uri = PlayableError.class.getResource("ErrorSound.mp3").toURI();
        verify(player).play(uri);
    }
}
