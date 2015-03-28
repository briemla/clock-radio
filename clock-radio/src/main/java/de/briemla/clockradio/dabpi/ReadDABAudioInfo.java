package de.briemla.clockradio.dabpi;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.command.BaseCommand;
import de.briemla.clockradio.dabpi.result.DABAudioInfo;

public class ReadDABAudioInfo extends BaseCommand<DABAudioInfo> {

	public ReadDABAudioInfo() {
		super("n");
	}

	@Override
	public DABAudioInfo parse(Output output) {
		return null;
	}

}
