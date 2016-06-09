package de.briemla.clockradio.dabpi.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABServiceList;

public class ReadDABServiceList extends BaseCommand<DABServiceList> {

    private static final String startOfLine = "^";
    private static final String endOfLine = "$";
    private static final String separator = "[ ]+";
    private static final String serviceNumber = "Num:" + separator + "([0-9]+)";
    private static final String serviceId = "Service ID:" + separator + "([aAbBcCdDeEfF0-9]+)";
    private static final String serviceName = "Service Name:" + separator + "(.+)";
    private static final String componentId = "Component ID:" + separator + "([0-9]+)" + endOfLine;
    private static final int ATTRIBUTES = 4;
    private static final int NUMBER = 1;
    private static final int ID = 2;
    private static final int NAME = 3;
    private static final String FILTER = "Num:";
    private static final Pattern PATTERN = Pattern.compile(startOfLine + serviceNumber + separator
            + serviceId + separator + serviceName + separator + componentId);

    public ReadDABServiceList() {
        super("g");
    }

    @Override
    protected DABServiceList parseSpecialized(Output output) {
        DABServiceList dabServiceList = new DABServiceList();
        output.standardAsStream()
              .filter(line -> line.startsWith(FILTER))
              .map(line -> toService(line))
              .forEach(service -> dabServiceList.add(service));
        return dabServiceList;
    }

    private static DABService toService(String line) {
        Matcher matcher = match(line);

        Integer number = Integer.parseInt(matcher.group(NUMBER));
        String id = matcher.group(ID).trim();
        String name = matcher.group(NAME).trim();
        return new DABService(number, id, name);
    }

    private static Matcher match(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches() || matcher.groupCount() < ATTRIBUTES) {
            throw new IllegalArgumentException("Incorrect line: " + line);
        }
        return matcher;
    }

}
