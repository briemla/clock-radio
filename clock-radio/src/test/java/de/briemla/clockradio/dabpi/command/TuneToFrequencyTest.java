package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.TuneToFrequencyResult;

public class TuneToFrequencyTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void serialize() throws Exception {
		Integer frequency = 104800;
		TuneToFrequency command = new TuneToFrequency(frequency);

		String serialized = command.serialize();
		assertThat(serialized, is(equalTo(" -c 104800")));
	}

	@Test
	public void parseCorrectOutput() throws Exception {
		Integer frequency = 106700;
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_fm_tune_freq(" + frequency + ")");
		output.addStandard("0x808000c0");

		TuneToFrequency command = new TuneToFrequency(frequency);
		TuneToFrequencyResult tunedFrequency = command.parseSpecialized(output);

		assertThat(tunedFrequency, is(equalTo(new TuneToFrequencyResult(frequency))));
	}

	@Test
	public void parseIncorrectFrequencyInOutput() throws Exception {
		Integer frequency = 106700;
		Integer otherFrequency = 105500;
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_fm_tune_freq(" + otherFrequency + ")");
		output.addStandard("0x808000c0");

		TuneToFrequency command = new TuneToFrequency(frequency);

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(is("Tuned frequency differs from expected frequency: tuned: " + otherFrequency
				+ " expected: " + frequency));
		command.parseSpecialized(output);
	}

	@Test
	public void parseMissingFrequencyInOutput() throws Exception {
		Integer frequency = 106700;
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("0x808000c0");

		TuneToFrequency command = new TuneToFrequency(frequency);

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(is("Tuned frequency missing in output."));
		command.parseSpecialized(output);
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(TuneToFrequency.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}
}
