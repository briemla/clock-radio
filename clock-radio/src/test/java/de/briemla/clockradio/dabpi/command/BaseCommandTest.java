package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
}
