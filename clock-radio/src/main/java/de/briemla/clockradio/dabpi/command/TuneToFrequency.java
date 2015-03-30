package de.briemla.clockradio.dabpi.command;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.TuneToFrequencyResult;

public class TuneToFrequency extends BaseCommand<TuneToFrequencyResult> {

	private final Integer frequency;

	public TuneToFrequency(Integer frequency) {
		super("c");
		this.frequency = frequency;
	}

	@Override
	protected TuneToFrequencyResult parseSpecialized(Output output) {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TuneToFrequency other = (TuneToFrequency) obj;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TuneToFrequency [frequency=" + frequency + "]";
	}

}
