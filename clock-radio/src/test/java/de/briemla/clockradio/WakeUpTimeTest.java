package de.briemla.clockradio;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class WakeUpTimeTest {

	@Test
	public void singleDigitHourAndMinuteToTimeString() throws Exception {
		WakeUpTime wakeUpTime = new WakeUpTime(1, 2);

		assertThat(wakeUpTime.toString(), is(equalTo("01:02")));
	}

	@Test
	public void singleDigitHourToTimeString() throws Exception {
		WakeUpTime wakeUpTime = new WakeUpTime(1, 45);

		assertThat(wakeUpTime.toString(), is(equalTo("01:45")));
	}

	@Test
	public void singleDigitMinuteToTimeString() throws Exception {
		WakeUpTime wakeUpTime = new WakeUpTime(23, 2);

		assertThat(wakeUpTime.toString(), is(equalTo("23:02")));
	}

	@Test
	public void doubleDigitHourAndMinuteToTimeString() throws Exception {
		WakeUpTime wakeUpTime = new WakeUpTime(15, 52);

		assertThat(wakeUpTime.toString(), is(equalTo("15:52")));
	}

	@Test
	public void equalsAndHashCode() throws Exception {
		EqualsVerifier.forClass(WakeUpTime.class).allFieldsShouldBeUsed().usingGetClass().verify();
	}
}
