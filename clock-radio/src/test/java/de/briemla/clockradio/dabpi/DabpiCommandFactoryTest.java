package de.briemla.clockradio.dabpi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.briemla.clockradio.dabpi.command.SwitchToDAB;
import de.briemla.clockradio.dabpi.command.SwitchToFM;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABStatus;
import de.briemla.clockradio.dabpi.result.FMStatus;
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

	@Test
	public void fmStatus() {
		Command<FMStatus> command = factory.fmStatus();

		assertThat(command, is(equalTo(new FMStatusCommand())));
	}

	@Test
	public void dabStatus() {
		Command<DABStatus> command = factory.dabStatus();

		assertThat(command, is(equalTo(new DABStatusCommand())));
	}

	@Test
	public void startDABService() {
		Integer serviceId = 0;
		Command<DABService> command = factory.startDABService(serviceId);

		assertThat(command, is(equalTo(new StartDABService(serviceId))));
	}
}
