package de.briemla.clockradio;

import java.io.IOException;

import de.briemla.clockradio.dabpi.RadioController;

public class FMStation implements Comparable<FMStation> {

	private final Integer frequency;

	public FMStation(Integer frequency) {
		this.frequency = frequency;
	}

	public void tuneTo(RadioController controller) throws IOException {
		controller.switchToFM();
		controller.tuneTo(frequency);
	}

	@Override
	public int compareTo(FMStation other) {
		return frequency.compareTo(other.frequency);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FMStation other = (FMStation) obj;
		if (frequency == null) {
			if (other.frequency != null) {
				return false;
			}
		} else if (!frequency.equals(other.frequency)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "FMStation [frequency=" + frequency + "]";
	}

}
