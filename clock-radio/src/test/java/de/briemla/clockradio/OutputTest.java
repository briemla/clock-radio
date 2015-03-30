package de.briemla.clockradio;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OutputTest {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

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

	@Test
	public void emptyLinesInError() throws Exception {
		Output output = new Output();
		output.addError("");
		output.addError("");

		assertThat(output.isErrorEmpty(), is(true));
	}

	@Test
	public void somethingInErrorOutput() throws Exception {
		Output output = new Output();
		output.addError("something");

		assertThat(output.isErrorEmpty(), is(false));
	}

	@Test
	public void addNull() throws Exception {
		Output output = new Output();
		output.addError(null);

		assertThat(output.isErrorEmpty(), is(true));
	}

	@Test
	public void concatSingleLineErrorMessage() throws Exception {
		Output output = new Output();
		output.addError("firstLine");

		assertThat(output.concatErrorMessage(), is(equalTo("firstLine")));
	}

	@Test
	public void concatMultiLineErrorMessage() throws Exception {
		Output output = new Output();
		output.addError("firstLine");
		output.addError("secondLine");

		assertThat(output.concatErrorMessage(), is(equalTo("firstLine" + LINE_SEPARATOR + "secondLine")));
	}

}
