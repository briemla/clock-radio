package de.briemla.clockradio.dabpi;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.command.BaseCommand;
import de.briemla.clockradio.dabpi.result.DABSubchannelInfo;

public class ReadDABSubchannelInfo extends BaseCommand<DABSubchannelInfo> {

	public ReadDABSubchannelInfo() {
		super("o");
	}

	@Override
	public DABSubchannelInfo parse(Output output) {
		return null;
	}

}
