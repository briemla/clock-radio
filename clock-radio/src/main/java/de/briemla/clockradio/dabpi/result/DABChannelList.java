package de.briemla.clockradio.dabpi.result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.briemla.clockradio.DABStation;
import de.briemla.clockradio.dabpi.RadioController;
import de.briemla.clockradio.dabpi.command.Region;

public class DABChannelList {

	private final Region region;
	private final ArrayList<DABChannel> channels;

	public DABChannelList(Region region) {
		this.region = region;
		channels = new ArrayList<>();
	}

	public void add(DABChannel channel) {
		channels.add(channel);
	}

	public List<DABChannel> getChannelList() {
		return channels;
	}

	public List<DABStation> scanStations(RadioController controller) throws IOException {
		controller.selectDABRegion(region);
		ArrayList<DABStation> stations = new ArrayList<>();
		for (DABChannel channel : channels) {
			controller.selectDABChannel(channel);
			DABServiceList serviceList = controller.readDABServiceList();
			serviceList.stream().map(service -> new DABStation(region, service, channel)).forEach(stations::add);
		}
		return stations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channels == null) ? 0 : channels.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DABChannelList other = (DABChannelList) obj;
		if (channels == null) {
			if (other.channels != null) {
				return false;
			}
		} else if (!channels.equals(other.channels)) {
			return false;
		}
		if (region != other.region) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DABChannelList [region=" + region + ", channels=" + channels + "]";
	}

}
