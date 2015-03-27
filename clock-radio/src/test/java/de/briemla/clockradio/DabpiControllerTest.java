package de.briemla.clockradio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
		SwitchToDABResult result = mock(SwitchToDABResult.class);

		when(factory.switchToDAB()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);
		when(result.isSuccessful()).thenReturn(true);

		DabpiController controller = new DabpiController(executor, factory);
		boolean switchSuccessful = controller.switchToDAB();
		assertThat("Return value", switchSuccessful, is(true));

		verify(factory).switchToDAB();
		verify(executor).execute(command);
	}

	@Test
	public void switchToFM() throws Exception {
		@SuppressWarnings("unchecked")
		Command<SwitchToFMResult> command = mock(Command.class);
		SwitchToFMResult result = mock(SwitchToFMResult.class);

		when(factory.switchToFM()).thenReturn(command);
		when(executor.execute(command)).thenReturn(result);
		when(result.isSuccessful()).thenReturn(true);

		DabpiController controller = new DabpiController(executor, factory);
		boolean switchSuccessful = controller.switchToFM();
		assertThat("Return value", switchSuccessful, is(true));

		verify(factory).switchToFM();
		verify(executor).execute(command);
	}
}
