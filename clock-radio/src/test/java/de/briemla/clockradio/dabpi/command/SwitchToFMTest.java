package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SwitchToFMTest {

	@Test
	public void serialize() throws Exception {
		SwitchToFM command = new SwitchToFM();
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -b")));
	}

}
