package de.briemla.clockradio;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javafx.beans.property.SimpleBooleanProperty;

import org.junit.Before;
import org.junit.Test;

public class AlarmTest {

    private PlayerFactory player;
    private PlayerWorker worker;
    private SimpleBooleanProperty started;

    @Before
    public void initialise() {
        player = mock(PlayerFactory.class);
        worker = mock(PlayerWorker.class);
        started = new SimpleBooleanProperty();
    }

    @Test
    public void playMusicInBackground() throws Exception {
        when(player.create(any(Media.class))).thenReturn(worker);
        Alarm alarm = new Alarm(started, player);

        boolean playing = alarm.play();

        assertThat(playing, is(true));
        verify(player).create(any(Media.class));
        verify(worker).start();
    }

    @Test
    public void playAndStopMusic() throws Exception {
        when(player.create(any(Media.class))).thenReturn(worker);
        Alarm alarm = new Alarm(started, player);

        boolean playing = alarm.play();
        alarm.stop();

        assertThat(playing, is(true));
        verify(player).create(any(Media.class));
        verify(worker).start();
        verify(worker).stop();
    }
}
