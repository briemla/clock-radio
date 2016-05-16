package de.briemla.clockradio;

import de.briemla.clockradio.dabpi.AlsaController;
import de.briemla.clockradio.dabpi.CommandFactory;
import de.briemla.clockradio.dabpi.DabpiCommandFactory;
import de.briemla.clockradio.dabpi.DabpiController;
import de.briemla.clockradio.dabpi.DabpiExecutor;
import de.briemla.clockradio.dabpi.RadioExecutor;
import de.briemla.clockradio.player.DabpiRadioPlayer;
import de.briemla.clockradio.player.RadioPlayer;

public class Dabpi {
    private static final String programPath = "/home/pi/dabpi_ctl/dabpi_ctl";
    private static final String dabpiCtl = "sudo " + programPath;

    public static RadioPlayer createRadioPlayer() {
        RadioExecutor executor = new DabpiExecutor(dabpiCtl);
        CommandFactory factory = new DabpiCommandFactory();
        AlsaController alsaController = new AlsaController();
        return new DabpiRadioPlayer(new DabpiController(executor, factory, alsaController));
    }

}
