package de.briemla.clockradio.dabpi.command;

import java.util.Optional;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.TuneToFrequencyResult;

public class TuneToFrequency extends BaseCommand<TuneToFrequencyResult> {

	private static final String FREQUENCY_KEY_WORD = "si46xx_fm_tune_freq(";
	private final Integer frequency;

	public TuneToFrequency(Integer frequency) {
		super("c");
		this.frequency = frequency;
	}

	@Override
	protected TuneToFrequencyResult parseSpecialized(Output output) {
		Optional<String> tunedFrequency = output.standardAsStream().filter(line -> line.startsWith(FREQUENCY_KEY_WORD))
				.map(line -> line.substring(20, line.length() - 1)).findFirst();
		check(tunedFrequency);
		return new TuneToFrequencyResult(frequency);
	}

	private void check(Optional<String> tunedFrequency) {
		if (!tunedFrequency.isPresent()) {
			throw new IllegalArgumentException("Tuned frequency missing in output.");
		}
		Integer parsedFrequency = Integer.parseInt(tunedFrequency.get());
		if (!frequency.equals(parsedFrequency)) {
			throw new IllegalArgumentException("Tuned frequency differs from expected frequency: tuned: "
			        + parsedFrequency + " expected: " + frequency);
		}
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
