package de.briemla.clockradio;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.player.PlayerFactory;
import de.briemla.clockradio.player.PlayerWorker;

public class PlayMusicOnErrorTest {

    private PlayerFactory factory;
    private PlayMusicOnError playMusicOnError;
    private PlayerWorker worker;
    private Logger notNeeded;

    @Before
    public void initialise() {
        factory = mock(PlayerFactory.class);
        worker = mock(PlayerWorker.class);
        notNeeded = mock(Logger.class);

        playMusicOnError = new PlayMusicOnError(factory, notNeeded);
    }

    @Test
    public void playsMusicWhenExceptionIsThrown() throws Exception {
        when(factory.create(any())).thenReturn(worker);
        Throwable throwable = mock(Throwable.class);

        playMusicOnError.handle(throwable);

        verify(factory).create(any());
        verify(worker).start();
    }
}
