package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABStatus;

public class DABStatusCommandTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void serialize() throws Exception {
		DABStatusCommand command = new DABStatusCommand();
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -e")));
	}

	@Test
	public void parseCorrectOutput() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_digrad_status():");
		output.addStandard("ACQ: 1");
		output.addStandard("VALID: 1");
		output.addStandard("RSSI: 36");
		output.addStandard("SNR: -10");
		output.addStandard("FIC_QUALITY: 95");
		output.addStandard("CNR 6");
		output.addStandard("FFT_OFFSET 0");
		output.addStandard("Tuned frequency 208064kHz");
		output.addStandard("Tuned index 2");
		output.addStandard("ANTCAP: 128");

		DABStatusCommand command = new DABStatusCommand();
		DABStatus dabStatus = command.parse(output);

		assertThat(dabStatus, is(equalTo(new DABStatus(208064))));
	}

	@Test
	public void parseMissingFrequencyInOutput() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_digrad_status():");
		output.addStandard("ACQ: 1");
		output.addStandard("VALID: 1");
		output.addStandard("RSSI: 36");
		output.addStandard("SNR: -10");
		output.addStandard("FIC_QUALITY: 95");
		output.addStandard("CNR 6");
		output.addStandard("FFT_OFFSET 0");
		output.addStandard("Tuned index 2");
		output.addStandard("ANTCAP: 128");

		DABStatusCommand command = new DABStatusCommand();

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Frequency missing in DAB status output.");
		command.parse(output);
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(DABStatusCommand.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}

}
