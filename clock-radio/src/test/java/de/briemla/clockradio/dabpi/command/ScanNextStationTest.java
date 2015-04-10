package de.briemla.clockradio.dabpi.command;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.ScanDirection;

public class ScanNextStationTest {

	@Test
	public void serializeScanUp() throws Exception {
		ScanDirection direction = ScanDirection.UP;

		ScanNextStation command = new ScanNextStation(direction);
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -l up")));
	}

	@Test
	public void serializeScanDown() throws Exception {
		ScanDirection direction = ScanDirection.DOWN;

		ScanNextStation command = new ScanNextStation(direction);
		String serialized = command.serialize();

		assertThat(serialized, is(equalTo(" -l down")));
	}

	@Test
	public void parse() throws Exception {
		Output output = new Output();
		output.addStandard("");
		ScanDirection direction = ScanDirection.UP;

		ScanNextStation command = new ScanNextStation(direction);
		Void result = command.parse(output);

		assertThat(result, is(nullValue()));
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(ScanNextStation.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}

}
