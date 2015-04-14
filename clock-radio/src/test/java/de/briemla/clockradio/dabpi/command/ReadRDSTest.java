package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.briemla.clockradio.dabpi.Output;

public class ReadRDSTest {

	@Test
	public void serialize() throws Exception {
		ReadRDS command = new ReadRDS();
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -m")));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void parse() throws Exception {
		new ReadRDS().parse(new Output());
	}

}
