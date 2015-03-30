package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABStatus;

public class DABStatusCommand extends BaseCommand<DABStatus> {

	public DABStatusCommand() {
		super("e");
	}

	@Override
	protected DABStatus parseSpecialized(Output output) {
		return null;
	}

}
