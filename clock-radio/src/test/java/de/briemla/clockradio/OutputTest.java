package de.briemla.clockradio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OutputTest {

	@Test
	public void emptyError() throws Exception {
		Output output = new Output();

		assertThat(output.isErrorEmpty(), is(true));
	}

	@Test
	public void emptyLineInError() throws Exception {
		Output output = new Output();
		output.addError("");

		assertThat(output.isErrorEmpty(), is(true));
	}

}
