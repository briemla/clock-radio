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

		when(factory.startDABService()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		StartDABServiceResult startDABService = controller.startDABService(serviceId);
		assertThat("Result", startDABService, is(equalTo(expectedResult)));

		verify(factory).startDABService();
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}
}
