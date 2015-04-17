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
public class RadioPlayer {

	private final RadioController controller;

	public RadioPlayer(RadioController controller) {
		super();
		this.controller = controller;
	}

	public ArrayList<FMStation> scanFM() throws IOException {
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
			search = !startFrequency.equals(currentFrequency) && !(overflow && currentFrequency > startFrequency)
					&& !(currentFrequency.equals(Integer.MIN_VALUE));
			lastFrequency = currentFrequency;
		}
		Collections.sort(frequency);
		return frequency;

	}

	private Integer currentFrequency() throws IOException {
		FMStatus fmStatus = controller.fmStatus();
		return fmStatus.getFrequency();
	}

	public List<DABStation> scanDAB(Region region) throws IOException {
		controller.switchToDAB();
		DABChannelList channels = controller.readFrequencyListFor(region);
		return channels.scanStations(controller);

	}

	public void play(Station station) throws IOException {
		station.tuneTo(controller);
	}

	public void stop() {
		// TODO Stop alsa redirection

	}

}
