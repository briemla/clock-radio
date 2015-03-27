package de.briemla.clockradio;

import java.util.ArrayList;

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

	public ArrayList<Integer> scan() {
		Integer startFrequency = currentFrequency();
		Integer lastFrequency = startFrequency;
		boolean search = true;
		boolean overflow = false;
		ArrayList<Integer> frequency = new ArrayList<>();
		while (search) {
			DAB_CONTROL.SCAN_UP.execute();
			Integer currentFrequency = currentFrequency();
			frequency.add(currentFrequency);
			overflow |= lastFrequency > currentFrequency;
			search = !startFrequency.equals(currentFrequency) && !(overflow && currentFrequency > startFrequency)
					&& !(currentFrequency.equals(Integer.MIN_VALUE));
			lastFrequency = currentFrequency;
		}
		return frequency;

	}

	private static Integer currentFrequency() {
		String status = DAB_CONTROL.FM_STATUS.execute().asString();
		if (status.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		String string = status.split(" ")[1];
		String frequency = string.substring(0, string.length() - 3);
		return Integer.parseInt(frequency);
	}

}
