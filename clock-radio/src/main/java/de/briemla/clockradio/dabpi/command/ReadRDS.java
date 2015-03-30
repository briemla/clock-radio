package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.RDSInfo;

public class ReadRDS extends BaseCommand<RDSInfo> {

	public ReadRDS() {
		super("m");
	}

	@Override
	protected RDSInfo parseSpecialized(Output output) {
		return null;
	}
}
