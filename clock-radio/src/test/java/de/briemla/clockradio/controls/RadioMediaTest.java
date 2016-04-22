package de.briemla.clockradio.controls;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import de.briemla.clockradio.PlayableMedia;
import de.briemla.clockradio.dabpi.Station;

public class RadioMediaTest {

    @Test
    public void whenCreateImmutableMedia() throws Exception {
        Station station = mock(Station.class);
        RadioMedia radio = new RadioMedia(station);
        PlayableMedia firstMedia = radio.create();
        PlayableMedia anotherMedia = radio.create();

        assertThat(firstMedia, is(not(sameInstance(anotherMedia))));
        assertThat(firstMedia, is(equalTo(anotherMedia)));
    }

    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(RadioMedia.class).usingGetClass().verify();
    }

}
