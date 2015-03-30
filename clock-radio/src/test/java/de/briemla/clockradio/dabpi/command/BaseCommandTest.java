package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.briemla.clockradio.Output;

public class BaseCommandTest {

	public static class ConcreteBaseCommand extends BaseCommand<Void> {

		private boolean called = false;

		public ConcreteBaseCommand(String parameter) {
			super(parameter);
		}

		@Override
		protected Void parseSpecialized(Output output) {
			called = true;
			return null;
		}
	}

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void serialize() throws Exception {
		ConcreteBaseCommand command = new ConcreteBaseCommand("test");

		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -test")));
	}

	@Test
	public void serializeSomethingElse() throws Exception {
		ConcreteBaseCommand command = new ConcreteBaseCommand("somethingElse");

		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -somethingElse")));
	}

	@Test
	public void parseEmptyError() throws Exception {
		Output output = new Output();
		ConcreteBaseCommand command = new ConcreteBaseCommand("something");

		command.parse(output);

		assertThat(command.called, is(true));
	}

	@Test
	public void parseError() throws Exception {
		Output output = new Output();
		output.addError("error");
		ConcreteBaseCommand command = new ConcreteBaseCommand("command");

		thrown.expect(IOException.class);
		thrown.expectMessage("error");
		command.parse(output);
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(BaseCommand.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}
}
