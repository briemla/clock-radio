package de.briemla.clockradio.dabpi.command;

import org.junit.Test;

import de.briemla.clockradio.Output;

public class ReadRDSTest {

	@Test(expected = UnsupportedOperationException.class)
	public void parse() throws Exception {
		new ReadRDS().parse(new Output());
	}

}
