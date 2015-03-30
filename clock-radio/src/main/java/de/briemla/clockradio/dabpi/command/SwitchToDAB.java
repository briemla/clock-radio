package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;

public class SwitchToDAB extends BaseCommand<Void> {

	public SwitchToDAB() {
		super("a");
	}

	@Override
	protected Void parseSpecialized(Output output) {
		return null;
	}
}
