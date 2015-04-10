package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABChannelList;

public class ReadFrequencyListTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void serialize() throws Exception {
		Region region = Region.BAYERN;
		ReadFrequencyList command = new ReadFrequencyList(region);

		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -k 1")));
	}

	@Test
	public void parse() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_set_freq_list(): 0x818000c0");
		output.addStandard("si46xx_dab_tune_freq(0): 0x818000c0");
		output.addStandard("si46xx_dab_digrad_status():");
		output.addStandard("Channel 0: ACQ: 1 RSSI: 42 SNR: -10 Name: DR Deutschland");
		output.addStandard("si46xx_dab_tune_freq(1): 0x818000c0");
		output.addStandard("si46xx_dab_digrad_status():");
		output.addStandard("Channel 1: ACQ: 0 RSSI: 29 SNR: 0 ");
		output.addStandard("si46xx_dab_tune_freq(2): 0x818000c0");
		output.addStandard("si46xx_dab_digrad_status():");
		output.addStandard("Channel 2: ACQ: 1 RSSI: 36 SNR: -10 Name: SWR BW N");
		output.addStandard("si46xx_dab_tune_freq(3): 0x818000c0");
		output.addStandard("si46xx_dab_digrad_status():");
		output.addStandard("Channel 3: ACQ: 0 RSSI: 40 SNR: -10 ");

		Region region = Region.BAYERN;
		DABChannelList expectedFrequencyList = new DABChannelList(region);
		expectedFrequencyList.add(new DABChannel(0));
		expectedFrequencyList.add(new DABChannel(1));
		expectedFrequencyList.add(new DABChannel(2));
		expectedFrequencyList.add(new DABChannel(3));

		ReadFrequencyList command = new ReadFrequencyList(region);

		DABChannelList frequencyList = command.parse(output);

		assertThat(frequencyList, is(equalTo(expectedFrequencyList)));
	}

	@Test
	public void parseCorruptedChannel() throws Exception {
		Output output = new Output();
		output.addStandard("dabpi_ctl version v0.01-29-g62f16f4");
		output.addStandard("si46xx_dab_set_freq_list(): 0x818000c0");
		output.addStandard("si46xx_dab_tune_freq(0): 0x818000c0");
		output.addStandard("si46xx_dab_digrad_status():");
		output.addStandard("Channel a: ACQ: 1 RSSI: 42 SNR: -10 Name: DR Deutschland");

		Region region = Region.BAYERN;
		ReadFrequencyList command = new ReadFrequencyList(region);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Could not parse channel info: Channel a: ACQ: 1 RSSI: 42 SNR: -10 Name: DR Deutschland");
		command.parse(output);
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(ReadFrequencyList.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}

}
