package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.RDSInfo;

public class ReadRDS extends BaseCommand<RDSInfo> {

	public ReadRDS() {
		super("m");
	}

	@Override
	protected RDSInfo parseSpecialized(Output output) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
