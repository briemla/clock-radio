package de.briemla.clockradio.dabpi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class DabpiControllerTest {

	private RadioExecutor executor;
	private CommandFactory factory;

	@Before
	public void initializeMocks() {
		executor = mock(RadioExecutor.class);
		factory = mock(CommandFactory.class);
	}

	@Test
	public void switchToDAB() throws Exception {
		@SuppressWarnings("unchecked")
		Command<SwitchToDABResult> command = mock(Command.class);
		SwitchToDABResult result = new SwitchToDABResult(true);
		SwitchToDABResult expectedResult = new SwitchToDABResult(true);

		when(factory.switchToDAB()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		SwitchToDABResult switchSuccessful = controller.switchToDAB();
		assertThat("Return value", switchSuccessful, is(equalTo(expectedResult)));

		verify(factory).switchToDAB();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void switchToFM() throws Exception {
		@SuppressWarnings("unchecked")
		Command<SwitchToFMResult> command = mock(Command.class);
		SwitchToFMResult result = new SwitchToFMResult(true);
		SwitchToFMResult expectedResult = new SwitchToFMResult(true);

		when(factory.switchToFM()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		SwitchToFMResult switchSuccessful = controller.switchToFM();
		assertThat("Return value", switchSuccessful, is(equalTo(expectedResult)));

		verify(factory).switchToFM();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void tuneToFrequency() throws Exception {
		@SuppressWarnings("unchecked")
		Command<TuneToFrequencyResult> command = mock(Command.class);
		Integer frequency = 106700;
		TuneToFrequencyResult result = new TuneToFrequencyResult(true, frequency);
		TuneToFrequencyResult expectedResult = new TuneToFrequencyResult(true, frequency);

		when(factory.tuneTo(frequency)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		TuneToFrequencyResult tunedFrequency = controller.tuneTo(frequency);
		assertThat("Return value", tunedFrequency, is(equalTo(expectedResult)));

		verify(factory).tuneTo(frequency);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void fmStatus() throws Exception {
		@SuppressWarnings("unchecked")
		Command<FMStatus> command = mock(Command.class);
		FMStatus result = new FMStatus(true);
		FMStatus expectedResult = new FMStatus(true);

		when(factory.fmStatus()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		FMStatus fmStatus = controller.fmStatus();
		assertThat("Result", fmStatus, is(equalTo(expectedResult)));

		verify(factory).fmStatus();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void dabStatus() throws Exception {
		@SuppressWarnings("unchecked")
		Command<DABStatus> command = mock(Command.class);
		DABStatus result = new DABStatus(true);
		DABStatus expectedResult = new DABStatus(true);

		when(factory.dabStatus()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		DABStatus dabStatus = controller.dabStatus();
		assertThat("Result", dabStatus, is(equalTo(expectedResult)));

		verify(factory).dabStatus();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void startDABService() throws Exception {
		@SuppressWarnings("unchecked")
		Command<DABService> command = mock(Command.class);
		Integer serviceId = 0;
		DABService result = new DABService(true, serviceId);
		DABService expectedResult = new DABService(true, serviceId);

		when(factory.startDABService(serviceId)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		DABService startDABService = controller.startDABService(serviceId);
		assertThat("Result", startDABService, is(equalTo(expectedResult)));

		verify(factory).startDABService(serviceId);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void readDABServiceList() throws Exception {
		@SuppressWarnings("unchecked")
		Command<DABServiceList> command = mock(Command.class);
		DABServiceList result = new DABServiceList(true);
		DABServiceList expectedResult = new DABServiceList(true);

		when(factory.readDABServiceList()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		DABServiceList readDABServiceList = controller.readDABServiceList();
		assertThat("Result", readDABServiceList, is(equalTo(expectedResult)));

		verify(factory).readDABServiceList();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void selectDABChannel() throws Exception {
		@SuppressWarnings("unchecked")
		Command<DABChannel> command = mock(Command.class);
		Integer channelId = 0;
		DABChannel result = new DABChannel(true, channelId);
		DABChannel expectedResult = new DABChannel(true, channelId);

		when(factory.selectDABChannel(channelId)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		DABChannel selectDABChannel = controller.selectDABChannel(channelId);
		assertThat("Result", selectDABChannel, is(equalTo(expectedResult)));

		verify(factory).selectDABChannel(channelId);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void selectDABRegion() throws Exception {
		@SuppressWarnings("unchecked")
		Command<DABRegion> command = mock(Command.class);
		Integer regionId = 0;
		DABRegion result = new DABRegion(true, regionId);
		DABRegion expectedResult = new DABRegion(true, regionId);

		when(factory.selectDABRegion(regionId)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		DABRegion selectDABRegion = controller.selectDABRegion(regionId);
		assertThat("Result", selectDABRegion, is(equalTo(expectedResult)));

		verify(factory).selectDABRegion(regionId);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void readFrequencyListForRegion() throws Exception {
		@SuppressWarnings("unchecked")
		Command<FrequencyList> command = mock(Command.class);
		Integer regionId = 0;
		FrequencyList result = new FrequencyList(true, regionId);
		FrequencyList expectedResult = new FrequencyList(true, regionId);

		when(factory.readFrequencyListFor(regionId)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		FrequencyList readFrequencyList = controller.readFrequencyListFor(regionId);
		assertThat("Result", readFrequencyList, is(equalTo(expectedResult)));

		verify(factory).readFrequencyListFor(regionId);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void scanNextStation() throws Exception {
		@SuppressWarnings("unchecked")
		Command<Station> command = mock(Command.class);
		ScanDirection direction = ScanDirection.UP;
		Station result = new Station(true, direction);
		Station expectedResult = new Station(true, direction);

		when(factory.scanNextStation(direction)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		Station scanNextStation = controller.scanNextStation(direction);
		assertThat("Result", scanNextStation, is(equalTo(expectedResult)));

		verify(factory).scanNextStation(direction);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void readRDS() throws Exception {
		@SuppressWarnings("unchecked")
		Command<RDSInfo> command = mock(Command.class);
		RDSInfo result = new RDSInfo(true);
		RDSInfo expectedResult = new RDSInfo(true);

		when(factory.readRDS()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		RDSInfo readRDS = controller.readRDS();
		assertThat("Result", readRDS, is(equalTo(expectedResult)));

		verify(factory).readRDS();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void readDABAudioInfo() throws Exception {
		@SuppressWarnings("unchecked")
		Command<DABAudioInfo> command = mock(Command.class);
		DABAudioInfo result = new DABAudioInfo(true);
		DABAudioInfo expectedResult = new DABAudioInfo(true);

		when(factory.readDABAudioInfo()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		DABAudioInfo readDABAudioInfo = controller.readDABAudioInfo();
		assertThat("Result", readDABAudioInfo, is(equalTo(expectedResult)));

		verify(factory).readDABAudioInfo();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void readDABSubchannelInfo() throws Exception {
		@SuppressWarnings("unchecked")
		Command<DABSubchannelInfo> command = mock(Command.class);
		DABSubchannelInfo result = new DABSubchannelInfo(true);
		DABSubchannelInfo expectedResult = new DABSubchannelInfo(true);

		when(factory.readDABSubchannelInfo()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		DABSubchannelInfo readDABSubchannelInfo = controller.readDABSubchannelInfo();
		assertThat("Result", readDABSubchannelInfo, is(equalTo(expectedResult)));

		verify(factory).readDABSubchannelInfo();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}
}
