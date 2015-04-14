package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.briemla.clockradio.dabpi.Output;

public class ReadDABAudioInfoTest {

	@Test
	public void serialize() throws Exception {
		ReadDABAudioInfo command = new ReadDABAudioInfo();
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -n")));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void parse() throws Exception {
		new ReadDABAudioInfo().parse(new Output());
	}
}
