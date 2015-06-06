package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.DABAudioInfo;

public class ReadDABAudioInfo extends BaseCommand<DABAudioInfo> {

    public ReadDABAudioInfo() {
        super("n");
    }

    @Override
    protected DABAudioInfo parseSpecialized(Output output) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

}
