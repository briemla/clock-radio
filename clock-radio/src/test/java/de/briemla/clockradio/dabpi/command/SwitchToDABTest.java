package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.SwitchToDABResult;

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
		SwitchToDABResult result = switchToDAB.parse(output);

		assertThat("null return", result, is(notNullValue()));
	}

}
