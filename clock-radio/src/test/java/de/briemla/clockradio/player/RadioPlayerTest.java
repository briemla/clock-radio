package de.briemla.clockradio.player;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.briemla.clockradio.dabpi.DABStation;
import de.briemla.clockradio.dabpi.FMStation;
import de.briemla.clockradio.dabpi.RadioController;
import de.briemla.clockradio.dabpi.ScanDirection;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.dabpi.command.Region;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABChannelList;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABServiceList;
import de.briemla.clockradio.dabpi.result.FMStatus;

public class RadioPlayerTest {

	@Test
	public void scanOneFMStation() throws Exception {
		FMStation[] expectedFrequencies = { new FMStation(105500) };
		RadioController controller = mock(RadioController.class);
		RadioPlayer player = new RadioPlayer(controller);
		when(controller.fmStatus()).thenReturn(new FMStatus(105500));

		ArrayList<FMStation> frequencies = player.scanFM();

		assertThat(frequencies, contains(expectedFrequencies));
		verify(controller).switchToFM();
		verify(controller).scanNextStation(ScanDirection.UP);
		verify(controller, times(2)).fmStatus();
	}

	@Test
	public void scanMultipleFMStations() throws Exception {
		FMStation[] expectedFrequencies = { new FMStation(105500), new FMStation(106700) };
		RadioController controller = mock(RadioController.class);
		RadioPlayer player = new RadioPlayer(controller);
		when(controller.fmStatus()).thenReturn(new FMStatus(105500)).thenReturn(new FMStatus(106700))
		        .thenReturn(new FMStatus(105500));

		ArrayList<FMStation> frequencies = player.scanFM();

		assertThat(frequencies, contains(expectedFrequencies));
		verify(controller).switchToFM();
		verify(controller, times(2)).scanNextStation(ScanDirection.UP);
		verify(controller, times(3)).fmStatus();
	}

	@Test
	public void scanDAB() throws Exception {
		Region region = Region.SAARLAND;
		DABService service = new DABService(3, "a3a0", "Some other name");
		DABChannel channel = new DABChannel(1);
		DABStation station = new DABStation(region, service, channel);
		DABStation[] expectedStations = { station };

		RadioController controller = mock(RadioController.class);
		DABChannelList channelList = new DABChannelList(region);
		channelList.add(channel);
		DABServiceList serviceList = new DABServiceList();
		serviceList.add(service);
		when(controller.readFrequencyListFor(region)).thenReturn(channelList);
		when(controller.readDABServiceList()).thenReturn(serviceList);

		RadioPlayer player = new RadioPlayer(controller);

		List<DABStation> stationList = player.scanDAB(region);

		assertThat(stationList, contains(expectedStations));

		verify(controller).switchToDAB();
		verify(controller).readFrequencyListFor(region);
		verify(controller).selectDABRegion(region);
		verify(controller).selectDABChannel(channel);
		verify(controller).readDABServiceList();
		verifyNoMoreInteractions(controller);
	}

	@Test
	public void play() throws Exception {
		RadioController controller = mock(RadioController.class);
		Station station = mock(Station.class);
		RadioPlayer player = new RadioPlayer(controller);
		player.play(station);

		verify(station).tuneTo(controller);
		verify(controller).playAudio();
		verifyNoMoreInteractions(controller);
	}

	@Test
	public void stop() throws Exception {
		RadioController controller = mock(RadioController.class);
		RadioPlayer player = new RadioPlayer(controller);
		player.stop();

		verify(controller).stopAudio();
		verifyNoMoreInteractions(controller);
	}
}
