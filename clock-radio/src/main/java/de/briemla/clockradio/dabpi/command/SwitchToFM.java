package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.SwitchToFMResult;

public class SwitchToFM extends BaseCommand<SwitchToFMResult> {

	public SwitchToFM() {
		super("b");
	}

	@Override
	protected SwitchToFMResult parseSpecialized(Output output) {
		return null;
	}

}
