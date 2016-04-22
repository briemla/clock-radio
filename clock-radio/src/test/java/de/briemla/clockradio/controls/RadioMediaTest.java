package de.briemla.clockradio.controls;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.player.Player;

public class RadioMediaTest {

    @Test
    public void play() throws Exception {
        Station station = mock(Station.class);
        Player player = mock(Player.class);
        RadioMedia media = new RadioMedia(station);

        media.play(player);

        verify(player).play(station);
        verifyNoMoreInteractions(player);
        verifyZeroInteractions(station);
    }

    @Test
    public void stop() throws Exception {
        Station station = mock(Station.class);
        Player player = mock(Player.class);
        RadioMedia media = new RadioMedia(station);

        media.stop(player);

        verify(player).stop();
        verifyNoMoreInteractions(player);
        verifyZeroInteractions(station);
    }

    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(RadioMedia.class).usingGetClass().verify();
    }

}
