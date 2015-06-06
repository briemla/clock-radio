package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.DABSubchannelInfo;

public class ReadDABSubchannelInfo extends BaseCommand<DABSubchannelInfo> {

    public ReadDABSubchannelInfo() {
        super("o");
    }

    @Override
    protected DABSubchannelInfo parseSpecialized(Output output) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

}
