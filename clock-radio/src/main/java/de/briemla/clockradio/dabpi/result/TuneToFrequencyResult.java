package de.briemla.clockradio.dabpi.result;

import de.briemla.clockradio.dabpi.RadioResult;

public class TuneToFrequencyResult implements RadioResult {

	private final boolean successful;
	private final Integer frequency;

	public TuneToFrequencyResult(boolean successful, Integer frequency) {
		this.successful = successful;
		this.frequency = frequency;
	}

	@Override
	public boolean isSuccessful() {
		return successful;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + (successful ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TuneToFrequencyResult other = (TuneToFrequencyResult) obj;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		if (successful != other.successful)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TuneToFrequencyResult [successful=" + successful + ", frequency=" + frequency + "]";
	}

}
