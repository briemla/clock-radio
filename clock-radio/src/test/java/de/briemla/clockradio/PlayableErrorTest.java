package de.briemla.clockradio;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import de.briemla.clockradio.player.Player;

public class PlayableErrorTest {

    private final class StopAfterSeveralRetries implements Answer<Void> {
        private final AtomicInteger invocations = new AtomicInteger(0);

        public StopAfterSeveralRetries() {
            super();
        }

        @Override
        public Void answer(InvocationOnMock media) throws Throwable {
            invocations.incrementAndGet();
            if (5 <= invocations.get()) {
                error.stop(player);
            }
            return null;
        }
    }

    private Player player;
    private PlayableError error;
    private Logger notNeeded;

    @Before
    public void initialise() {
        player = mock(Player.class);
        notNeeded = mock(Logger.class);

        error = new PlayableError(notNeeded);
    }

    @Test(timeout = 1000)
    public void playsErrorMediaUntilStopped() throws Exception {
        Answer<Void> stopAfterSeveralCallsOfPlay = new StopAfterSeveralRetries();
        doAnswer(stopAfterSeveralCallsOfPlay).when(player).play(any(URI.class));

        error.play(player);

        URI uri = PlayableError.class.getResource("ErrorSound.mp3").toURI();
        verify(player, times(5)).play(uri);
    }

    @Test
    public void stopsPlayingErrorMediaOnPlay() throws Exception {
        error.stop(player);

        verify(player).stop();
    }
}
