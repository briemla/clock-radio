package de.briemla.clockradio.dabpi;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class FMStationTest {

    @Test
    public void tuneTo() throws Exception {
        Integer frequency = 105500;
        FMStation station = new FMStation(frequency);
        RadioController controller = mock(RadioController.class);

        station.tuneTo(controller);

        verify(controller).switchToFM();
        verify(controller).tuneTo(frequency);
    }

    @Test
    public void compareTo() throws Exception {
        FMStation lowerStation = new FMStation(1);
        FMStation higherStation = new FMStation(2);

        assertThat(lowerStation, is(lessThan(higherStation)));
        assertThat(higherStation, is(greaterThan(lowerStation)));
    }

    @Test
    public void testToString() throws Exception {
        FMStation fmStation = new FMStation(106700);

        assertThat(fmStation.toString(), is(equalTo("106700")));
    }

    @Test
    public void equalsAndHashCode() throws Exception {
        EqualsVerifier.forClass(FMStation.class).usingGetClass().verify();
    }

}
