package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.RadioResult;

public class BaseCommandTest {

	public static class ConcreteBaseCommand extends BaseCommand<RadioResult> {

		public ConcreteBaseCommand(String parameter) {
			super(parameter);
		}

		@Override
		public RadioResult parse(Output output) {
			throw new RuntimeException("Should not be called in this test.");
		}
	}

	@Test
	public void serialize() throws Exception {
		BaseCommand<RadioResult> command = new ConcreteBaseCommand("test");

		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -test")));
	}

	@Test
	public void serializeSomethingElse() throws Exception {
		BaseCommand<RadioResult> command = new ConcreteBaseCommand("somethingElse");

		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -somethingElse")));
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(BaseCommand.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}
}
