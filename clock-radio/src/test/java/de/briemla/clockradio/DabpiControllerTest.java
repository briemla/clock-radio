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
	public void tuneToFrequencyInFM() throws Exception {
		@SuppressWarnings("unchecked")
		Command<TuneToFrequencyResult> command = mock(Command.class);
		TuneToFrequencyResult result = new TuneToFrequencyResult(true);
		TuneToFrequencyResult expectedResult = new TuneToFrequencyResult(true);
		Integer frequency = 106700;

		when(factory.tuneTo(frequency)).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);

		DabpiController controller = new DabpiController(executor, factory);
		TuneToFrequencyResult switchSuccessful = controller.tuneTo(frequency);
		assertThat("Return value", switchSuccessful, is(equalTo(expectedResult)));

		verify(factory).tuneTo(frequency);
		verify(executor).execute(command);
		verifyZeroInteractions(command);
	}
}
