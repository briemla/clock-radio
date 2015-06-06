package de.briemla.clockradio.dabpi.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.DABService;
import de.briemla.clockradio.dabpi.result.DABServiceList;

public class ReadDABServiceList extends BaseCommand<DABServiceList> {

    private static final int NUMBER = 1;
    private static final int ID = 2;
    private static final int NAME = 3;
    private static final String FILTER = "Num:";
    private static final Pattern PATTERN = Pattern
            .compile("^Num:[ ]+([0-9]+)[ ]+Service ID:[ ]+([aAbBcCdDeEfF0-9]+)[ ]+Service Name:[ ]+(.+)Component ID:[ ]+([0-9]+)$");

    public ReadDABServiceList() {
        super("g");
    }

    @Override
    protected DABServiceList parseSpecialized(Output output) {
        DABServiceList dabServiceList = new DABServiceList();
        output.standardAsStream().filter(line -> line.startsWith(FILTER)).forEach(line -> dabServiceList.add(split(line)));
        return dabServiceList;
    }

    private static DABService split(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches() || matcher.groupCount() < 4) {
            throw new IllegalArgumentException("Incorrect line: " + line);
        }

        Integer number = Integer.parseInt(matcher.group(NUMBER));
        String id = matcher.group(ID).trim();
        String name = matcher.group(NAME).trim();
        return new DABService(number, id, name);
    }

}
