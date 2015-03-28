package de.briemla.clockradio.dabpi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.dabpi.command.SwitchToDAB;
import de.briemla.clockradio.dabpi.command.SwitchToFM;
import de.briemla.clockradio.dabpi.result.SwitchToDABResult;
import de.briemla.clockradio.dabpi.result.SwitchToFMResult;
import de.briemla.clockradio.dabpi.result.TuneToFrequencyResult;

public class DabpiCommandFactoryTest {

	private DabpiCommandFactory factory;

	@Before
	public void initializeFactory() {
		factory = new DabpiCommandFactory();
	}

	@Test
	public void switchToDAB() {
		Command<SwitchToDABResult> switchToDAB = factory.switchToDAB();

		assertThat(switchToDAB, is(equalTo(new SwitchToDAB())));
	}

	@Test
	public void switchToFM() {
		Command<SwitchToFMResult> switchToDAB = factory.switchToFM();

		assertThat(switchToDAB, is(equalTo(new SwitchToFM())));
	}

	@Test
	public void tuneToFrequency() {
		Integer frequency = 106700;
		Command<TuneToFrequencyResult> command = factory.tuneTo(frequency);

		assertThat(command, is(equalTo(new TuneToFrequency(frequency))));
	}

}
