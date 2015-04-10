package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SwitchToDABTest {

	@Test
	public void serialize() throws Exception {
		SwitchToDAB command = new SwitchToDAB();
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -a")));
	}
}
