package de.briemla.clockradio.dabpi.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.briemla.clockradio.dabpi.Output;
import de.briemla.clockradio.dabpi.result.DABChannel;
import de.briemla.clockradio.dabpi.result.DABChannelList;

public class ReadFrequencyList extends BaseCommand<DABChannelList> {

    private static final Pattern PATTERN = Pattern.compile("^Channel ([0-9]+): ACQ: ([0-9]+) RSSI: ([0-9]+) SNR: ([\\-0-9]+) (?:Name: ([a-zA-Z ]+)$|$)");
    private final Region region;

    public ReadFrequencyList(Region region) {
        super("k");
        this.region = region;
    }

    @Override
    public String serialize() {
        return super.serialize() + " " + region.serialize();
    }

    @Override
    protected DABChannelList parseSpecialized(Output output) {
        DABChannelList channelList = new DABChannelList(region);
        output.standardAsStream().filter(line -> line.startsWith("Channel ")).map(ReadFrequencyList::convert).forEach(channel -> channelList.add(channel));
        return channelList;
    }

    private static DABChannel convert(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (matcher.matches()) {
            Integer id = Integer.parseInt(matcher.group(1));
            return new DABChannel(id);
        }
        throw new IllegalArgumentException("Could not parse channel info: " + line);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((region == null) ? 0 : region.hashCode());
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
        ReadFrequencyList other = (ReadFrequencyList) obj;
        if (region != other.region)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ReadFrequencyList [region=" + region + "]";
    }

}
