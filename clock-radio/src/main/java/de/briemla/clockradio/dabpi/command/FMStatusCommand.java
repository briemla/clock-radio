package de.briemla.clockradio.dabpi.command;

import java.util.Optional;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.FMStatus;

public class FMStatusCommand extends BaseCommand<FMStatus> {

	private static final String FREQUENCY = "Frequency";
	private static final int UNIT_LENGTH = 3;
	private static final int START_FREQUENCY_INDEX = 11;

	public FMStatusCommand() {
		super("d");
	}

	@Override
	protected FMStatus parseSpecialized(Output output) {
		Optional<String> fmStatus = output.standardAsStream().filter(line -> line.startsWith(FREQUENCY))
		        .map(line -> line.substring(START_FREQUENCY_INDEX, line.length() - UNIT_LENGTH)).findFirst();
		if (!fmStatus.isPresent()) {
			throw new IllegalArgumentException("Frequency missing in status output.");
		}
		Integer frequency = Integer.parseInt(fmStatus.get());
		return new FMStatus(frequency);
	}
}
