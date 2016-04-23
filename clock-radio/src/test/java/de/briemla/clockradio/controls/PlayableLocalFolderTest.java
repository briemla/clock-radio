package de.briemla.clockradio.controls;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.io.File;
import java.nio.file.Path;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import de.briemla.clockradio.player.Player;

public class PlayableLocalFolderTest {

    @Test
    public void whenSourceContainsOneMp3File() throws Exception {
        File mp3Folder = new File(PlayableLocalFolderTest.class.getResource("mp3").toURI());
        Path path = mp3Folder.toPath();
        Player player = mock(Player.class);

        PlayableLocalFolder folder = new PlayableLocalFolder(path);
        folder.play(player);

        File mp3 = new File(mp3Folder, "something.mp3");
        verify(player).play(mp3.toURI());
    }

    @Test
    public void whenSourceContainsNoMp3Files() throws Exception {
        File noMp3 = new File(PlayableLocalFolderTest.class.getResource("noMp3").toURI());
        Path path = noMp3.toPath();
        Player player = mock(Player.class);

        PlayableLocalFolder folder = new PlayableLocalFolder(path);
        folder.play(player);

        verifyZeroInteractions(player);
    }

    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(PlayableLocalFolder.class)
                      .withIgnoredFields("cancelled")
                      .usingGetClass()
                      .verify();
    }
}
