package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABSubchannelInfo;

public class ReadDABSubchannelInfo extends BaseCommand<DABSubchannelInfo> {

	public ReadDABSubchannelInfo() {
		super("o");
	}

	@Override
	protected DABSubchannelInfo parseSpecialized(Output output) {
		return null;
	}

}
