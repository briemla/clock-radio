package de.briemla.clockradio.controls;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.nio.file.Path;

import org.junit.Test;

import de.briemla.clockradio.PlayableMedia;

public class LocalFolderTest {

    @Test
    public void whenCreateImmutableMedia() throws Exception {
        Path path = mock(Path.class);

        LocalFolder localFolder = new LocalFolder(path);
        PlayableMedia firstMedia = localFolder.create();
        PlayableMedia anotherMedia = localFolder.create();

        assertThat(firstMedia, is(not(sameInstance(anotherMedia))));
        assertThat(firstMedia, is(equalTo(anotherMedia)));
    }
}
