package de.briemla.clockradio.dabpi.command;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.TuneToFrequencyResult;

public class TuneToFrequency extends BaseCommand<TuneToFrequencyResult> {

	private static final String FILTER = "si46xx_fm_tune_freq";
	private static final Pattern PATTERN = Pattern.compile("^si46xx_fm_tune_freq\\(([0-9]+)\\)");
	private final Integer frequency;

	public TuneToFrequency(Integer frequency) {
		super("c");
		this.frequency = frequency;
	}

	@Override
	public String serialize() {
		return super.serialize() + " " + frequency;
	}

	@Override
	protected TuneToFrequencyResult parseSpecialized(Output output) {
		Optional<TuneToFrequencyResult> tunedFrequency = output.standardAsStream()
				.filter(line -> line.startsWith(FILTER)).map(this::convert).findFirst();
		if (tunedFrequency.isPresent()) {
			return tunedFrequency.get();
		}
		throw new IllegalArgumentException("Tuned frequency missing in output.");
	}

	private TuneToFrequencyResult convert(String line) {
		Matcher matcher = PATTERN.matcher(line);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Tuned frequency missing in output.");
		}
		Integer parsedFrequency = Integer.parseInt(matcher.group(1));
		check(parsedFrequency);
		return new TuneToFrequencyResult(parsedFrequency);
	}

	private void check(Integer parsedFrequency) {
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
