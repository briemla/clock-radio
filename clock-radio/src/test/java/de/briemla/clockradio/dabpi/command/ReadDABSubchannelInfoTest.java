package de.briemla.clockradio.dabpi.command;

import org.junit.Test;

import de.briemla.clockradio.Output;

public class ReadDABSubchannelInfoTest {

	@Test(expected = UnsupportedOperationException.class)
	public void parse() throws Exception {
		new ReadDABAudioInfo().parse(new Output());
	}
}
