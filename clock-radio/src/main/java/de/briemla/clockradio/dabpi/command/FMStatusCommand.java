package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.FMStatus;

public class FMStatusCommand extends BaseCommand<FMStatus> {

	public FMStatusCommand() {
		super("d");
	}

	@Override
	protected FMStatus parseSpecialized(Output output) {
		return null;
	}

}
