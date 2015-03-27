package de.briemla.clockradio;

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
		Command<FMStatusResult> command = mock(Command.class);
		FMStatusResult result = new FMStatusResult(true);
		FMStatusResult expectedResult = new FMStatusResult(true);

		when(factory.fmStatus()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		FMStatusResult fmStatus = controller.fmStatus();
		assertThat("Result", fmStatus, is(equalTo(expectedResult)));

		verify(factory).fmStatus();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void dabStatus() throws Exception {
		@SuppressWarnings("unchecked")
		Command<DABStatusResult> command = mock(Command.class);
		DABStatusResult result = new DABStatusResult(true);
		DABStatusResult expectedResult = new DABStatusResult(true);

		when(factory.dabStatus()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		DABStatusResult dabStatus = controller.dabStatus();
		assertThat("Result", dabStatus, is(equalTo(expectedResult)));

		verify(factory).dabStatus();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void startDABService() throws Exception {
		@SuppressWarnings("unchecked")
		Command<StartDABServiceResult> command = mock(Command.class);
		Integer serviceId = 0;
		StartDABServiceResult result = new StartDABServiceResult(true, serviceId);
		StartDABServiceResult expectedResult = new StartDABServiceResult(true, serviceId);

		when(factory.startDABService(serviceId)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		StartDABServiceResult startDABService = controller.startDABService(serviceId);
		assertThat("Result", startDABService, is(equalTo(expectedResult)));

		verify(factory).startDABService(serviceId);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void readDABServiceList() throws Exception {
		@SuppressWarnings("unchecked")
		Command<ReadDABServiceListResult> command = mock(Command.class);
		ReadDABServiceListResult result = new ReadDABServiceListResult(true);
		ReadDABServiceListResult expectedResult = new ReadDABServiceListResult(true);

		when(factory.readDABServiceList()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		ReadDABServiceListResult readDABServiceList = controller.readDABServiceList();
		assertThat("Result", readDABServiceList, is(equalTo(expectedResult)));

		verify(factory).readDABServiceList();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void selectDABChannel() throws Exception {
		@SuppressWarnings("unchecked")
		Command<SelectDABChannelResult> command = mock(Command.class);
		Integer channelId = 0;
		SelectDABChannelResult result = new SelectDABChannelResult(true, channelId);
		SelectDABChannelResult expectedResult = new SelectDABChannelResult(true, channelId);

		when(factory.selectDABChannel(channelId)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		SelectDABChannelResult selectDABChannel = controller.selectDABChannel(channelId);
		assertThat("Result", selectDABChannel, is(equalTo(expectedResult)));

		verify(factory).selectDABChannel(channelId);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void selectDABRegion() throws Exception {
		@SuppressWarnings("unchecked")
		Command<SelectDABRegionResult> command = mock(Command.class);
		Integer regionId = 0;
		SelectDABRegionResult result = new SelectDABRegionResult(true, regionId);
		SelectDABRegionResult expectedResult = new SelectDABRegionResult(true, regionId);

		when(factory.selectDABRegion(regionId)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		SelectDABRegionResult selectDABRegion = controller.selectDABRegion(regionId);
		assertThat("Result", selectDABRegion, is(equalTo(expectedResult)));

		verify(factory).selectDABRegion(regionId);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void readFrequencyListForRegion() throws Exception {
		@SuppressWarnings("unchecked")
		Command<ReadFrequencyListForRegionResult> command = mock(Command.class);
		Integer regionId = 0;
		ReadFrequencyListForRegionResult result = new ReadFrequencyListForRegionResult(true, regionId);
		ReadFrequencyListForRegionResult expectedResult = new ReadFrequencyListForRegionResult(true, regionId);

		when(factory.readFrequencyListFor(regionId)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		ReadFrequencyListForRegionResult readFrequencyList = controller.readFrequencyListFor(regionId);
		assertThat("Result", readFrequencyList, is(equalTo(expectedResult)));

		verify(factory).readFrequencyListFor(regionId);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void scanNextStation() throws Exception {
		@SuppressWarnings("unchecked")
		Command<ScanNextStationResult> command = mock(Command.class);
		ScanDirection direction = ScanDirection.UP;
		ScanNextStationResult result = new ScanNextStationResult(true, direction);
		ScanNextStationResult expectedResult = new ScanNextStationResult(true, direction);

		when(factory.scanNextStation(direction)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		ScanNextStationResult scanNextStation = controller.scanNextStation(direction);
		assertThat("Result", scanNextStation, is(equalTo(expectedResult)));

		verify(factory).scanNextStation(direction);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void readRDS() throws Exception {
		@SuppressWarnings("unchecked")
		Command<ReadRDSResult> command = mock(Command.class);
		ReadRDSResult result = new ReadRDSResult(true);
		ReadRDSResult expectedResult = new ReadRDSResult(true);

		when(factory.readRDS()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		ReadRDSResult scanNextStation = controller.readRDS();
		assertThat("Result", scanNextStation, is(equalTo(expectedResult)));

		verify(factory).readRDS();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}
}
