package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.briemla.clockradio.Output;

public class SelectDABRegionTest {

	@Test
	public void serialize() throws Exception {
		SelectDABRegion command = new SelectDABRegion(Region.BAYERN);
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -j 1")));
	}

	@Test
	public void parseCorrectOutput() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_set_freq_list(): 0x808000c0");

		Region region = Region.BAYERN;
		SelectDABRegion command = new SelectDABRegion(region);

		Void parsed = command.parse(output);

		assertThat(parsed, is(nullValue()));
	}

}
