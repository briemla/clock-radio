package de.briemla.clockradio.dabpi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.dabpi.command.Region;
import de.briemla.clockradio.dabpi.result.DABAudioInfo;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABChannelList;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABServiceList;
import de.briemla.clockradio.dabpi.result.DABStatus;
import de.briemla.clockradio.dabpi.result.DABSubchannelInfo;
import de.briemla.clockradio.dabpi.result.FMStatus;
import de.briemla.clockradio.dabpi.result.RDSInfo;
import de.briemla.clockradio.dabpi.result.TuneToFrequencyResult;

public class DabpiControllerTest {

	private RadioExecutor executor;
	private CommandFactory factory;
	private AlsaController alsaController;

	@Before
	public void initializeMocks() {
		executor = mock(RadioExecutor.class);
		factory = mock(CommandFactory.class);
		alsaController = mock(AlsaController.class);
	}

	@After
	public void verifyNoMoreInteraction() {
		verifyNoMoreInteractions(executor);
		verifyNoMoreInteractions(factory);
		verifyNoMoreInteractions(alsaController);
	}

	@Test
	public void switchToDAB() throws Exception {
		@SuppressWarnings("unchecked")
		Command<Void> command = mock(Command.class);
		Void result = null;

		when(factory.switchToDAB()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
		Void switchSuccessful = controller.switchToDAB();
		assertThat("Return value", switchSuccessful, is(equalTo(result)));

		verify(factory).switchToDAB();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void switchToFM() throws Exception {
		@SuppressWarnings("unchecked")
		Command<Void> command = mock(Command.class);
		Void result = null;
		Void expectedResult = null;

		when(factory.switchToFM()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
		Void switchSuccessful = controller.switchToFM();
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
		TuneToFrequencyResult result = new TuneToFrequencyResult(frequency);
		TuneToFrequencyResult expectedResult = new TuneToFrequencyResult(frequency);

		when(factory.tuneTo(frequency)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
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
		FMStatus result = new FMStatus(105500);
		FMStatus expectedResult = new FMStatus(105500);

		when(factory.fmStatus()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
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
		DABStatus result = new DABStatus(208064);
		DABStatus expectedResult = new DABStatus(208064);

		when(factory.dabStatus()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
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
		Integer serviceNumber = 0;
		String serviceId = "da09";
		String serviceName = "ServiceName";
		DABService dabService = new DABService(serviceNumber, serviceId, serviceName);
		DABService result = new DABService(serviceNumber, serviceId, serviceName);
		DABService expectedResult = new DABService(serviceNumber, serviceId, serviceName);

		when(factory.startDABService(dabService)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
		DABService startDABService = controller.startDABService(dabService);
		assertThat("Result", startDABService, is(equalTo(expectedResult)));

		verify(factory).startDABService(dabService);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void readDABServiceList() throws Exception {
		@SuppressWarnings("unchecked")
		Command<DABServiceList> command = mock(Command.class);
		DABServiceList result = new DABServiceList();
		DABServiceList expectedResult = new DABServiceList();

		when(factory.readDABServiceList()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
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
		DABChannel original = new DABChannel(channelId);
		DABChannel result = new DABChannel(channelId);
		DABChannel expectedResult = new DABChannel(channelId);

		when(factory.selectDABChannel(original)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
		DABChannel selectDABChannel = controller.selectDABChannel(original);
		assertThat("Result", selectDABChannel, is(equalTo(expectedResult)));

		verify(factory).selectDABChannel(original);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void selectDABRegion() throws Exception {
		@SuppressWarnings("unchecked")
		Command<Void> command = mock(Command.class);
		Region region = Region.BAYERN;
		Void result = null;
		Void expectedResult = null;

		when(factory.selectDABRegion(region)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
		Void selectDABRegion = controller.selectDABRegion(region);
		assertThat("Result", selectDABRegion, is(equalTo(expectedResult)));

		verify(factory).selectDABRegion(region);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void readFrequencyListForRegion() throws Exception {
		@SuppressWarnings("unchecked")
		Command<DABChannelList> command = mock(Command.class);
		Region region = Region.BAYERN;
		DABChannelList result = new DABChannelList(region);
		DABChannelList expectedResult = new DABChannelList(region);

		when(factory.readFrequencyListFor(region)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
		DABChannelList readFrequencyList = controller.readFrequencyListFor(region);
		assertThat("Result", readFrequencyList, is(equalTo(expectedResult)));

		verify(factory).readFrequencyListFor(region);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	@Test
	public void scanNextStation() throws Exception {
		@SuppressWarnings("unchecked")
		Command<Void> command = mock(Command.class);
		ScanDirection direction = ScanDirection.UP;
		Void result = null;
		Void expectedResult = null;

		when(factory.scanNextStation(direction)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = newDabpiController();
		Void scanNextStation = controller.scanNextStation(direction);
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

		DabpiController controller = newDabpiController();
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

		DabpiController controller = newDabpiController();
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

		DabpiController controller = newDabpiController();
		DABSubchannelInfo readDABSubchannelInfo = controller.readDABSubchannelInfo();
		assertThat("Result", readDABSubchannelInfo, is(equalTo(expectedResult)));

		verify(factory).readDABSubchannelInfo();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}

	private DabpiController newDabpiController() {
		return new DabpiController(executor, factory, alsaController);
	}

	@Test
	public void playAudio() throws Exception {
		DabpiController controller = newDabpiController();

		controller.playAudio();

		verify(alsaController).play();
	}

	@Test
	public void stopAudio() throws Exception {
		DabpiController controller = newDabpiController();

		controller.stopAudio();

		verify(alsaController).stop();
	}
}
