package de.briemla.clockradio.dabpi;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.command.BaseCommand;
import de.briemla.clockradio.dabpi.result.FMStatus;

public class FMStatusCommand extends BaseCommand<FMStatus> {

	public FMStatusCommand() {
		super("d");
	}

	@Override
	public FMStatus parse(Output output) {
		return null;
	}

}
