package de.briemla.clockradio.controls;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.util.function.Consumer;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import de.briemla.clockradio.player.Player;

public class PlayableLocalFolderTest {

    @Test
    public void whenSourceContainsOneMp3File() throws Exception {
        Path path = mock(Path.class);
        Path filePath = mock(Path.class);
        File file = new File("something.mp3");
        URI uri = file.toURI();
        when(filePath.toFile()).thenReturn(file);
        when(filePath.toUri()).thenReturn(uri);
        Player player = mock(Player.class);
        doAnswer(invocation -> {
            invocation.getArgumentAt(0, Consumer.class).accept(filePath);
            return null;
        }).when(path).forEach(any());

        PlayableLocalFolder folder = new PlayableLocalFolder(path);
        folder.play(player);

        verify(player).play(file.toURI());
    }

    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(PlayableLocalFolder.class)
                      .withIgnoredFields("cancelled")
                      .usingGetClass()
                      .verify();
    }
}
