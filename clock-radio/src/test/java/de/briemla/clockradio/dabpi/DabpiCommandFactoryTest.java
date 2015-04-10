package de.briemla.clockradio.dabpi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.dabpi.command.DABStatusCommand;
import de.briemla.clockradio.dabpi.command.FMStatusCommand;
import de.briemla.clockradio.dabpi.command.ReadDABAudioInfo;
import de.briemla.clockradio.dabpi.command.ReadDABServiceList;
import de.briemla.clockradio.dabpi.command.ReadDABSubchannelInfo;
import de.briemla.clockradio.dabpi.command.ReadFrequencyList;
import de.briemla.clockradio.dabpi.command.ReadRDS;
import de.briemla.clockradio.dabpi.command.Region;
import de.briemla.clockradio.dabpi.command.ScanNextStation;
import de.briemla.clockradio.dabpi.command.SelectDABChannel;
import de.briemla.clockradio.dabpi.command.SelectDABRegion;
import de.briemla.clockradio.dabpi.command.StartDABService;
import de.briemla.clockradio.dabpi.command.SwitchToDAB;
import de.briemla.clockradio.dabpi.command.SwitchToFM;
import de.briemla.clockradio.dabpi.command.TuneToFrequency;
import de.briemla.clockradio.dabpi.result.DABAudioInfo;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABChannelList;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABServiceList;
import de.briemla.clockradio.dabpi.result.DABStatus;
import de.briemla.clockradio.dabpi.result.DABSubchannelInfo;
import de.briemla.clockradio.dabpi.result.FMStatus;
import de.briemla.clockradio.dabpi.result.RDSInfo;
import de.briemla.clockradio.dabpi.result.Station;
import de.briemla.clockradio.dabpi.result.TuneToFrequencyResult;

public class DabpiCommandFactoryTest {

	private DabpiCommandFactory factory;

	@Before
	public void initializeFactory() {
		factory = new DabpiCommandFactory();
	}

	@Test
	public void switchToDAB() {
		Command<Void> switchToDAB = factory.switchToDAB();

		assertThat(switchToDAB, is(equalTo(new SwitchToDAB())));
	}

	@Test
	public void switchToFM() {
		Command<Void> switchToDAB = factory.switchToFM();

		assertThat(switchToDAB, is(equalTo(new SwitchToFM())));
	}

	@Test
	public void tuneToFrequency() {
		Integer frequency = 106700;
		Command<TuneToFrequencyResult> command = factory.tuneTo(frequency);

		assertThat(command, is(equalTo(new TuneToFrequency(frequency))));
	}

	@Test
	public void fmStatus() {
		Command<FMStatus> command = factory.fmStatus();

		assertThat(command, is(equalTo(new FMStatusCommand())));
	}

	@Test
	public void dabStatus() {
		Command<DABStatus> command = factory.dabStatus();

		assertThat(command, is(equalTo(new DABStatusCommand())));
	}

	@Test
	public void startDABService() {
		Integer serviceNumber = 0;
		String serviceId = "d3a3";
		String serviceName = "ServiceName";
		DABService service = new DABService(serviceNumber, serviceId, serviceName);
		Command<DABService> command = factory.startDABService(service);

		assertThat(command, is(equalTo(new StartDABService(service))));
	}

	@Test
	public void readDABServiceList() {
		Command<DABServiceList> command = factory.readDABServiceList();

		assertThat(command, is(equalTo(new ReadDABServiceList())));
	}

	@Test
	public void selectDABChannel() {
		DABChannel channel = new DABChannel(0);
		Command<DABChannel> command = factory.selectDABChannel(channel);

		assertThat(command, is(equalTo(new SelectDABChannel(channel))));
	}

	@Test
	public void selectDABRegion() {
		Region region = Region.BAYERN;
		Command<Void> command = factory.selectDABRegion(region);

		assertThat(command, is(equalTo(new SelectDABRegion(region))));
	}

	@Test
	public void readFrequencyList() {
		Region region = Region.BAYERN;
		Command<DABChannelList> command = factory.readFrequencyListFor(region);

		assertThat(command, is(equalTo(new ReadFrequencyList(region))));
	}

	@Test
	public void scanNextStation() {
		ScanDirection direction = ScanDirection.UP;
		Command<Station> command = factory.scanNextStation(direction);

		assertThat(command, is(equalTo(new ScanNextStation(direction))));
	}

	@Test
	public void readRDS() {
		Command<RDSInfo> command = factory.readRDS();

		assertThat(command, is(equalTo(new ReadRDS())));
	}

	@Test
	public void readDABAudioInfo() {
		Command<DABAudioInfo> command = factory.readDABAudioInfo();

		assertThat(command, is(equalTo(new ReadDABAudioInfo())));
	}

	@Test
	public void readDABSubchannelInfo() {
		Command<DABSubchannelInfo> command = factory.readDABSubchannelInfo();

		assertThat(command, is(equalTo(new ReadDABSubchannelInfo())));
	}
}
