package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.DABChannel;

public class SelectDABChannelTest {

	private static final int CHANNEL_ID = 2;

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void serialize() throws Exception {
		DABChannel dabChannel = new DABChannel(CHANNEL_ID);

		SelectDABChannel command = new SelectDABChannel(dabChannel);
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -i 2")));
	}

	@Test
	public void parseChannel0() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_tune_freq(0): 0x818000c0");
		DABChannel expectedResult = new DABChannel(0);
		DABChannel channel = new DABChannel(0);
		SelectDABChannel command = new SelectDABChannel(channel);

		DABChannel result = command.parse(output);

		assertThat(result, is(equalTo(expectedResult)));
	}

	@Test
	public void parseChannel2() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_tune_freq(2): 0x818000c0");
		DABChannel expectedResult = new DABChannel(2);
		DABChannel channel = new DABChannel(2);
		SelectDABChannel command = new SelectDABChannel(channel);

		DABChannel result = command.parse(output);

		assertThat(result, is(equalTo(expectedResult)));
	}

	@Test
	public void parseNoValidChannel() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("assi46xx_dab_tune_freq(0): 0x818000c0");
		DABChannel channel = new DABChannel(0);

		SelectDABChannel command = new SelectDABChannel(channel);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("No valid channel in output found.");
		command.parse(output);
	}

	@Test
	public void parseCorruptedChannel() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_tune_freq(a0): 0x818000c0");
		DABChannel channel = new DABChannel(0);

		SelectDABChannel command = new SelectDABChannel(channel);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Could not parse channel: si46xx_dab_tune_freq(a0): 0x818000c0");
		command.parse(output);
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(SelectDABChannel.class).usingGetClass().verify();
	}

}
