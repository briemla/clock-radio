package de.briemla.clockradio.controls;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.nio.file.Path;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import de.briemla.clockradio.PlayableMedia;

public class LocalFolderTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenCreateImmutableMedia() throws Exception {
        Path path = mock(Path.class);

        LocalFolder localFolder = new LocalFolder(path);
        PlayableMedia firstMedia = localFolder.create();
        PlayableMedia anotherMedia = localFolder.create();

        assertThat(firstMedia, is(not(sameInstance(anotherMedia))));
        assertThat(firstMedia, is(equalTo(anotherMedia)));
    }

    @Test
    public void doesNotFailWhenSourceDirectoryIsEmpty() throws Exception {
        Path emptyFolder = missingFolder();

        LocalFolder media = new LocalFolder(emptyFolder);

        assertThat(media.children(), is(empty()));
    }

    @Test
    public void doesNotFailWhenSourceDirectoryIsMissing() throws Exception {
        LocalFolder media = new LocalFolder(missingFolder());

        assertThat(media.children(), is(empty()));
    }

    private Path missingFolder() {
        File root = folder.getRoot();
        File missingFolder = new File(root, "missing");
        int runningNumber = 0;
        while (missingFolder.exists()) {
            missingFolder = new File(root, "missing" + runningNumber);
            runningNumber++;
        }
        return missingFolder.toPath();
    }

    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(LocalFolder.class).usingGetClass().verify();
    }
}
