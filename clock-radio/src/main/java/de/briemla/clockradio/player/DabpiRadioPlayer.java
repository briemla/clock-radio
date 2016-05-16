package de.briemla.clockradio.player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.briemla.clockradio.dabpi.DABStation;
import de.briemla.clockradio.dabpi.FMStation;
import de.briemla.clockradio.dabpi.RadioController;
import de.briemla.clockradio.dabpi.ScanDirection;
import de.briemla.clockradio.dabpi.Station;
import de.briemla.clockradio.dabpi.command.Region;
import de.briemla.clockradio.dabpi.result.DABChannelList;
import de.briemla.clockradio.dabpi.result.FMStatus;

/**
 * Controls radio
 *
 * @author Lars
 *
 */
public class DabpiRadioPlayer implements RadioPlayer {

    private final RadioController controller;

    public DabpiRadioPlayer(RadioController controller) {
        super();
        this.controller = controller;
    }

    @Override
    public List<FMStation> scanFM() {
        try {
            Integer startFrequency = currentFrequency();
            Integer lastFrequency = startFrequency;
            boolean search = true;
            boolean overflow = false;
            ArrayList<FMStation> frequency = new ArrayList<>();
            controller.switchToFM();
            while (search) {
                controller.scanNextStation(ScanDirection.UP);
                Integer currentFrequency = currentFrequency();
                frequency.add(new FMStation(currentFrequency));
                overflow |= lastFrequency > currentFrequency;
                search = !startFrequency.equals(currentFrequency)
                        && !(overflow && currentFrequency > startFrequency)
                        && !(currentFrequency.equals(Integer.MIN_VALUE));
                lastFrequency = currentFrequency;
            }
            Collections.sort(frequency);
            return frequency;
        } catch (IOException | IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        return new ArrayList<>();

    }

    private Integer currentFrequency() throws IOException {
        FMStatus fmStatus = controller.fmStatus();
        return fmStatus.getFrequency();
    }

    @Override
    public List<DABStation> scanDAB(Region region) {
        try {
            controller.switchToDAB();
            DABChannelList channels = controller.readFrequencyListFor(region);
            return channels.scanStations(controller);
        } catch (IOException | IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        return new ArrayList<>();

    }

    @Override
    public void play(Station station) throws IOException {
        station.tuneTo(controller);
        controller.playAudio();
    }

    @Override
    public void stop() {
        controller.stopAudio();
    }

}
