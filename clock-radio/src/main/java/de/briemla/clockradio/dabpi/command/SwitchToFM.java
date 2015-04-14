package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.dabpi.Output;

public class SwitchToFM extends BaseCommand<Void> {

	public SwitchToFM() {
		super("b");
	}

	@Override
	protected Void parseSpecialized(Output output) {
		return null;
	}

}
