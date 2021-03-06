package de.briemla.clockradio.dabpi.command;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.DABService;

public class StartDABService extends BaseCommand<DABService> {

    private static final String FILTER = "Starting service";
    private static final int ID = 2;
    private static final int NAME = 1;
    private static final int ELEMENTS = 2;
    private static final Pattern PATTERN = Pattern.compile("^Starting service(.*) ([aAbBcCdDeEfF0-9]+) .*$");

    private final DABService service;

    public StartDABService(DABService service) {
        super("f");
        this.service = service;
    }

    @Override
    public String serialize() {
        return super.serialize() + " " + service.serialize();
    }

    @Override
    protected DABService parseSpecialized(Output output) {
        Optional<String> startedService = output.standardAsStream().filter(line -> line.startsWith(FILTER)).findFirst();
        check(startedService);
        return service;
    }

    private void check(Optional<String> startedService) {
        if (!startedService.isPresent()) {
            return;
        }
        String parsedService = startedService.get();
        if (parsedService.equals("Starting service 0 0")) {
            throw new IllegalArgumentException("Wrong service started: " + parsedService);
        }
        Matcher matcher = PATTERN.matcher(parsedService);
        if (!matcher.matches() || matcher.groupCount() < ELEMENTS) {
            throw new IllegalArgumentException("Not able to parse input.");
        }
        // TODO clean up checkId
        String name = matcher.group(NAME).trim();
        String id = matcher.group(ID).trim();
        if (!service.checkId(id) || !service.checkName(name)) {
            throw new IllegalArgumentException("Wrong service started: " + name + " " + id + " expected: " + service.toString());
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((service == null) ? 0 : service.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        StartDABService other = (StartDABService) obj;
        if (service == null) {
            if (other.service != null) {
                return false;
            }
        } else if (!service.equals(other.service)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StartDABService [service=" + service + "]";
    }

}
