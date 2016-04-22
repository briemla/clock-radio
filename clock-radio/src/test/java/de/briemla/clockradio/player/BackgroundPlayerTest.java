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

public class BackgroundPlayerTest {

    private static final class Dummy implements Answer<Void> {
        private final CountDownLatch latch;
        private final CountDownLatch interrupted;
        private boolean isInterrupted;

        public Dummy(CountDownLatch latch) {
            super();
            isInterrupted = false;
            interrupted = new CountDownLatch(1);
            this.latch = latch;
        }

        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            try {
                latch.await();
            } catch (InterruptedException exception) {
                interrupted.countDown();
                isInterrupted = true;
            }
            return null;
        }

        public void awaitInterrupted() throws InterruptedException {
            interrupted.await();
        }
    }

    @Test
    public void startAndStopMedia() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Player player = mock(Player.class);
        PlayableMedia media = mock(PlayableMedia.class);
        Dummy answer = new Dummy(latch);
        doAnswer(answer).when(media).play(any());

        BackgroundPlayer worker = new BackgroundPlayer(player, media);

        worker.start();
        worker.stop();

        answer.awaitInterrupted();

        verify(media).play(player);
        verify(media).stop(player);
        assertThat(answer.isInterrupted, is(true));
    }
}
