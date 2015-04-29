package de.briemla.clockradio.dabpi.result;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import de.briemla.clockradio.dabpi.DABStation;
import de.briemla.clockradio.dabpi.RadioController;
import de.briemla.clockradio.dabpi.command.Region;

public class DABChannelListTest {

	@Test
	public void emptyChannelList() throws Exception {
		Region region = Region.BAYERN;
		DABChannelList channelList = new DABChannelList(region);

		assertThat(channelList.getChannelList(), is(empty()));
	}

	@Test
	public void addSingleFrequency() throws Exception {
		Region region = Region.BAYERN;
		Integer channelId = 1;
		DABChannelList channelList = new DABChannelList(region);

		DABChannel channel = new DABChannel(channelId);
		channelList.add(channel);

		assertThat(channelList.getChannelList(), contains(new DABChannel(channelId)));
	}

	@Test
	public void addMultipleFrequencies() throws Exception {
		Region region = Region.BAYERN;
		DABChannelList channelList = new DABChannelList(region);

		channelList.add(new DABChannel(0));
		channelList.add(new DABChannel(2));

		assertThat(channelList.getChannelList(), containsInAnyOrder(new DABChannel(0), new DABChannel(2)));
	}

	@Test
	public void scanOneStation() throws Exception {
		Region region = Region.BADEN_WUERTEMBERG;
		DABChannelList channelList = new DABChannelList(region);
		DABChannel channel = new DABChannel(0);
		channelList.add(channel);
		DABService service = new DABService(2, "d0d3", "Some station name");
		DABStation station = new DABStation(region, service, channel);
		DABStation[] expectedStations = { station };
		DABServiceList serviceList = new DABServiceList();
		serviceList.add(service);

		RadioController controller = mock(RadioController.class);
		when(controller.readDABServiceList()).thenReturn(serviceList);

		List<DABStation> stations = channelList.scanStations(controller);

		assertThat(stations, contains(expectedStations));

		verify(controller).selectDABRegion(region);
		verify(controller).selectDABChannel(channel);
		verify(controller).readDABServiceList();
	}

	@Test
	public void scanMultipleStationsInSameChannel() throws Exception {
		Region region = Region.BADEN_WUERTEMBERG;
		DABChannelList channelList = new DABChannelList(region);
		DABChannel channel = new DABChannel(0);
		channelList.add(channel);
		DABService service1 = new DABService(0, "a0a3", "Some station name");
		DABService service2 = new DABService(2, "d0d3", "Some other station name");
		DABService service3 = new DABService(4, "a0d3", "");
		DABStation station1 = new DABStation(region, service1, channel);
		DABStation station2 = new DABStation(region, service2, channel);
		DABStation[] expectedStations = { station1, station2 };
		DABServiceList serviceList = new DABServiceList();
		serviceList.add(service1);
		serviceList.add(service2);
		serviceList.add(service3);

		RadioController controller = mock(RadioController.class);
		when(controller.readDABServiceList()).thenReturn(serviceList);

		List<DABStation> stations = channelList.scanStations(controller);

		assertThat(stations, contains(expectedStations));

		verify(controller).selectDABRegion(region);
		verify(controller).selectDABChannel(channel);
		verify(controller).readDABServiceList();
	}

	@Test
	public void scanMultipleStationsInDifferentChannels() throws Exception {
		Region region = Region.BADEN_WUERTEMBERG;
		DABChannelList channelList = new DABChannelList(region);
		DABChannel channel1 = new DABChannel(0);
		DABChannel channel2 = new DABChannel(2);
		channelList.add(channel1);
		channelList.add(channel2);
		DABService service1 = new DABService(0, "a0a3", "Some station name");
		DABService service2 = new DABService(2, "d0d3", "Some other station name");
		DABService service3 = new DABService(4, "a0d3", "");
		DABStation station1 = new DABStation(region, service1, channel1);
		DABStation station2 = new DABStation(region, service2, channel2);
		DABStation[] expectedStations = { station1, station2 };
		DABServiceList serviceListOfChannel1 = new DABServiceList();
		serviceListOfChannel1.add(service1);
		DABServiceList serviceListOfChannel2 = new DABServiceList();
		serviceListOfChannel2.add(service2);
		serviceListOfChannel2.add(service3);

		RadioController controller = mock(RadioController.class);
		when(controller.readDABServiceList()).thenReturn(serviceListOfChannel1).thenReturn(serviceListOfChannel2);

		List<DABStation> stations = channelList.scanStations(controller);

		assertThat(stations, contains(expectedStations));

		verify(controller).selectDABRegion(region);
		verify(controller).selectDABChannel(channel1);
		verify(controller).selectDABChannel(channel2);
		verify(controller, times(2)).readDABServiceList();
	}

	@Test
	public void scanOneStationWithoutName() throws Exception {
		Region region = Region.BADEN_WUERTEMBERG;
		DABChannelList channelList = new DABChannelList(region);
		DABChannel channel = new DABChannel(0);
		channelList.add(channel);
		DABService service = new DABService(2, "d0d3", "");
		DABServiceList serviceList = new DABServiceList();
		serviceList.add(service);

		RadioController controller = mock(RadioController.class);
		when(controller.readDABServiceList()).thenReturn(serviceList);

		List<DABStation> stations = channelList.scanStations(controller);

		assertThat(stations, is(empty()));

		verify(controller).selectDABRegion(region);
		verify(controller).selectDABChannel(channel);
		verify(controller).readDABServiceList();
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(DABChannelList.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}
}
