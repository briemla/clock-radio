package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABServiceList;

public class ReadDABServiceList extends BaseCommand<DABServiceList> {

	public ReadDABServiceList() {
		super("g");
	}

	@Override
	public DABServiceList parse(Output output) {
		return null;
	}

}
