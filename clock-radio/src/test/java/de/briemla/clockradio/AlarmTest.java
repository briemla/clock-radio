package de.briemla.clockradio;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javafx.beans.property.SimpleBooleanProperty;

import org.junit.Test;

public class AlarmTest {

    @Test
    public void playMusicInBackground() throws Exception {
        SimpleBooleanProperty started = new SimpleBooleanProperty();
        PlayerFactory player = mock(PlayerFactory.class);
        PlayerWorker worker = mock(PlayerWorker.class);
        when(player.create(any(Media.class))).thenReturn(worker);
        Alarm alarm = new Alarm(started, player);

        boolean playing = alarm.play();

        assertThat(playing, is(true));
        verify(player).create(any(Media.class));
        verify(worker).start();
    }
}
