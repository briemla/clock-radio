package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.FMStatus;

public class FMStatusCommandTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void parseCorrectOutput() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_fm_rsq_status()");
		output.addStandard("0x818000c00001ae29df1f100c01006e00ffffffff");
		output.addStandard("SNR: 16 dB");
		output.addStandard("RSSI: 31 dBuV");
		output.addStandard("Frequency: 106700kHz");
		output.addStandard("FREQOFF: -66");
		output.addStandard("READANTCAP: 1");

		FMStatusCommand command = new FMStatusCommand();
		FMStatus fmStatus = command.parse(output);

		assertThat(fmStatus, is(equalTo(new FMStatus(106700))));
	}

	@Test
	public void parseMissingFrequencyInOutput() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_fm_rsq_status()");
		output.addStandard("0x818000c00001ae29df1f100c01006e00ffffffff");
		output.addStandard("SNR: 16 dB");
		output.addStandard("RSSI: 31 dBuV");
		output.addStandard("FREQOFF: -66");
		output.addStandard("READANTCAP: 1");

		FMStatusCommand command = new FMStatusCommand();

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(is(equalTo("Frequency missing in FM status output.")));
		command.parse(output);
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(FMStatusCommand.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}
}
