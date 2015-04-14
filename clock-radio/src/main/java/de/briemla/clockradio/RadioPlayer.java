package de.briemla.clockradio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import de.briemla.clockradio.dabpi.RadioController;
import de.briemla.clockradio.dabpi.ScanDirection;
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

	public ArrayList<Integer> scanFM() throws IOException {
		Integer startFrequency = currentFrequency();
		Integer lastFrequency = startFrequency;
		boolean search = true;
		boolean overflow = false;
		ArrayList<Integer> frequency = new ArrayList<>();
		controller.switchToFM();
		while (search) {
			controller.scanNextStation(ScanDirection.UP);
			Integer currentFrequency = currentFrequency();
			frequency.add(currentFrequency);
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

}
