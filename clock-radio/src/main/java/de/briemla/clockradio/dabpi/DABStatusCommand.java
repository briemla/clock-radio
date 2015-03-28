package de.briemla.clockradio.dabpi;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.command.BaseCommand;
import de.briemla.clockradio.dabpi.result.DABStatus;

public class DABStatusCommand extends BaseCommand<DABStatus> {

	public DABStatusCommand() {
		super("e");
	}

	@Override
	public DABStatus parse(Output output) {
		return null;
	}

}
