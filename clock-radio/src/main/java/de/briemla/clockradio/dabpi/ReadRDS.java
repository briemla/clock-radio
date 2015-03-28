package de.briemla.clockradio.dabpi;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.command.BaseCommand;
import de.briemla.clockradio.dabpi.result.RDSInfo;

public class ReadRDS extends BaseCommand<RDSInfo> {

	public ReadRDS() {
		super("m");
	}

	@Override
	public RDSInfo parse(Output output) {
		return null;
	}

}
