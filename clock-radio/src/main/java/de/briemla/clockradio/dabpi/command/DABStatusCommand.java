package de.briemla.clockradio.dabpi.command;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.DABStatus;

public class DABStatusCommand extends BaseCommand<DABStatus> {

	private static final Pattern FREQUENCY_PATTERN = Pattern.compile("^Tuned frequency ([0-9]+)kHz$");
	private static final String FREQUENCY_FILTER = "Tuned frequency";

	public DABStatusCommand() {
		super("e");
	}

	@Override
	protected DABStatus parseSpecialized(Output output) {
		Optional<DABStatus> status = output.standardAsStream().filter(line -> line.startsWith(FREQUENCY_FILTER))
		        .map(DABStatusCommand::convert).findFirst();
		if (!status.isPresent()) {
			throw new IllegalArgumentException("Frequency missing in DAB status output.");
		}

		return status.get();
	}

	private static DABStatus convert(String line) {
		Matcher matcher = FREQUENCY_PATTERN.matcher(line);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Frequency missing in DAB status output.");
		}
		Integer frequency = Integer.parseInt(matcher.group(1));
		return new DABStatus(frequency);
	}
}
