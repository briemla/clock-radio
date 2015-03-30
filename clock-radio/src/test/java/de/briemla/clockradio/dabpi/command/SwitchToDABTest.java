package de.briemla.clockradio.dabpi.command;

import java.io.IOException;

import org.junit.Test;

import de.briemla.clockradio.Output;

public class SwitchToDABTest {

	@Test(expected = IOException.class)
	public void parseError() throws Exception {
		Output output = new Output();
		output.addError("something");

		SwitchToDAB switchToDAB = new SwitchToDAB();
		switchToDAB.parse(output);
	}

	@Test
	public void parseEmptyError() throws Exception {
		Output output = new Output();

		SwitchToDAB switchToDAB = new SwitchToDAB();
		switchToDAB.parse(output);
	}

}
