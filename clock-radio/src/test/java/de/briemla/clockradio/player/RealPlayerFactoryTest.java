package de.briemla.clockradio.player;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import de.briemla.clockradio.Media;
import de.briemla.clockradio.player.BackgroundPlayer;
import de.briemla.clockradio.player.Player;
import de.briemla.clockradio.player.RealPlayerFactory;

public class RealPlayerFactoryTest {

    @Test
    public void createPlayerForMedia() throws Exception {
        Player player = mock(Player.class);
        RealPlayerFactory factory = new RealPlayerFactory(player);
        Media media = mock(Media.class);

        PlayerWorker worker = factory.create(media);

        PlayerWorker expectedPlayer = new BackgroundPlayer(player, media);
        assertThat(worker, is(equalTo(expectedPlayer)));
    }
}
