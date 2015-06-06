package de.briemla.clockradio.dabpi.command;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.FMStatus;

public class FMStatusCommand extends BaseCommand<FMStatus> {

    private static final Pattern FREQUENCY_PATTERN = Pattern.compile("^Frequency: ([0-9]+)kHz$");
    private static final String FREQUENCY_FILTER = "Frequency";

    public FMStatusCommand() {
        super("d");
    }

    @Override
    protected FMStatus parseSpecialized(Output output) {
        Optional<FMStatus> fmStatus = output.standardAsStream().filter(line -> line.startsWith(FREQUENCY_FILTER)).map(FMStatusCommand::convert).findFirst();
        if (fmStatus.isPresent()) {
            return fmStatus.get();
        }
        throw new IllegalArgumentException("Frequency missing in FM status output.");
    }

    private static FMStatus convert(String line) {
        Matcher matcher = FREQUENCY_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Frequency missing in FM status output.");
        }
        Integer frequency = Integer.parseInt(matcher.group(1));
        return new FMStatus(frequency);
    }
}
