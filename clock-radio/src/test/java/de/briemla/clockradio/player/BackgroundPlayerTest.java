package de.briemla.clockradio.player;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import de.briemla.clockradio.Media;
import de.briemla.clockradio.player.BackgroundPlayer;
import de.briemla.clockradio.player.Player;

public class BackgroundPlayerTest {

    private static final class Dummy implements Answer<Void> {
        private final CountDownLatch latch;
        private boolean isInterrupted;

        public Dummy(CountDownLatch latch) {
            super();
            isInterrupted = false;
            this.latch = latch;
        }

        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            try {
                latch.await();
            } catch (InterruptedException exception) {
                isInterrupted = true;
            }
            return null;
        }
    }

    @Test
    public void startAndStopMedia() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Player player = mock(Player.class);
        Media media = mock(Media.class);
        Dummy answer = new Dummy(latch);
        doAnswer(answer).when(media)
                        .play(any());

        BackgroundPlayer worker = new BackgroundPlayer(player, media);

        worker.start();
        worker.stop();

        verify(media).play(player);
        verify(media).stop(player);
        assertThat(answer.isInterrupted, is(true));
    }
}
