package de.briemla.clockradio.player;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import de.briemla.clockradio.Media;

public class RealPlayerFactoryTest {

    @Test
    public void createPlayerForMedia() throws Exception {
        Player player = mock(Player.class);
        RealPlayerFactory factory = new RealPlayerFactory(player);
        PlayableMedia playable = mock(PlayableMedia.class);
        Media media = mock(Media.class);
        when(media.create()).thenReturn(playable);

        PlayerWorker worker = factory.create(media);

        PlayerWorker expectedPlayer = new BackgroundPlayer(player, playable);
        assertThat(worker, is(equalTo(expectedPlayer)));
        verify(media).create();
    }
}
