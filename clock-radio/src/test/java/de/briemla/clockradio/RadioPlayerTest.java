package de.briemla.clockradio;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;

import de.briemla.clockradio.dabpi.RadioController;
import de.briemla.clockradio.dabpi.ScanDirection;
import de.briemla.clockradio.dabpi.result.FMStatus;

public class RadioPlayerTest {

	@Test
	public void scanOneFMStation() throws Exception {
		Integer[] expectedFrequencies = { 105500 };
		RadioController controller = mock(RadioController.class);
		RadioPlayer player = new RadioPlayer(controller);
		when(controller.fmStatus()).thenReturn(new FMStatus(105500));

		ArrayList<Integer> frequencies = player.scanFM();

		assertThat(frequencies, contains(expectedFrequencies));
		verify(controller).switchToFM();
		verify(controller).scanNextStation(ScanDirection.UP);
		verify(controller, times(2)).fmStatus();
	}

	@Test
	public void scanMultipleFMStations() throws Exception {
		Integer[] expectedFrequencies = { 105500, 106700 };
		RadioController controller = mock(RadioController.class);
		RadioPlayer player = new RadioPlayer(controller);
		when(controller.fmStatus()).thenReturn(new FMStatus(105500)).thenReturn(new FMStatus(106700))
		.thenReturn(new FMStatus(105500));

		ArrayList<Integer> frequencies = player.scanFM();

		assertThat(frequencies, contains(expectedFrequencies));
		verify(controller).switchToFM();
		verify(controller, times(2)).scanNextStation(ScanDirection.UP);
		verify(controller, times(3)).fmStatus();
	}
}
