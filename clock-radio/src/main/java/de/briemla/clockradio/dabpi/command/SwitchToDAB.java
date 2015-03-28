package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.SwitchToDABResult;

public class SwitchToDAB extends BaseCommand<SwitchToDABResult> {

	public SwitchToDAB() {
		super("a");
	}

	@Override
	public SwitchToDABResult parse(Output output) {
		return null;
	}

}
