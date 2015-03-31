package de.briemla.clockradio.dabpi.command;

import java.util.Optional;

import de.briemla.clockradio.Output;
import de.briemla.clockradio.dabpi.result.DABStatus;

public class DABStatusCommand extends BaseCommand<DABStatus> {

	private static final String FREQUENCY = "Tuned frequency";
	private static final int UNIT_LENGTH = 3;
	private static final int START_FREQUENCY_INDEX = 16;

	public DABStatusCommand() {
		super("e");
	}

	@Override
	protected DABStatus parseSpecialized(Output output) {
		Optional<String> frequency = output.standardAsStream().filter(line -> line.startsWith(FREQUENCY))
				.map(line -> line.substring(START_FREQUENCY_INDEX, line.length() - UNIT_LENGTH)).findFirst();
		if (!frequency.isPresent()) {
			throw new IllegalArgumentException("Frequency missing in DAB status output.");
		}

		Integer parsedFrequency = Integer.parseInt(frequency.get());
		return new DABStatus(parsedFrequency);
	}
}
