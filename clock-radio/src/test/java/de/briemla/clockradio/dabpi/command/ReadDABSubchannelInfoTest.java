package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.briemla.clockradio.Output;

public class ReadDABSubchannelInfoTest {

	@Test
	public void serialize() throws Exception {
		ReadDABSubchannelInfo command = new ReadDABSubchannelInfo();
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -o")));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void parse() throws Exception {
		new ReadDABAudioInfo().parse(new Output());
	}
}
